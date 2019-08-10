package com.rejsebuddy;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.rejsebuddy.storage.AppDatabase;

public class SettingsActivity extends AppCompatActivity {

    /**
     * Initiate the activity.
     *
     * @param state The previous state.
     */
    @Override
    protected void onCreate(Bundle state) {
        // Initiate the content view.
        super.onCreate(state);
        setContentView(R.layout.activity_settings);

        // Enable the return button.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind the settings fragment to the view.
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.settings, new SettingsFragment())
            .commit();
    }

    /**
     * Return back to previous view on click.
     *
     * @return Whether or not an action was performed.
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Handles the settings preferences.
     */
    static class SettingsFragment extends PreferenceFragmentCompat {

        /**
         * Sets the preferences resource fragment.
         *
         * @param state The saved instance state.
         * @param rootKey The root view key.
         */
        @Override
        public void onCreatePreferences(Bundle state, String rootKey) {
            setPreferencesFromResource(R.xml.fragment_settings, rootKey);
        }

        /**
         * Initiate the various preferences.
         *
         * @param state The previous view state.
         */
        @Override
        public void onCreate(Bundle state) {
            // Call super class.
            super.onCreate(state);

            // Setup the data category preferences.
            setupDataCategory();
        }

        /**
         * Setup the preference in the data category.
         */
        private void setupDataCategory() {
            // Find the delete and import data buttons.
            Preference importData = getPreferenceManager().findPreference("import_data");
            Preference deleteData = getPreferenceManager().findPreference("delete_data");

            // Bind the import test data action.
            importData.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    // Show loading dialog.
                    final ProgressDialog dialog = new ProgressDialog(getContext());
                    dialog.setMessage(getResources().getString(R.string.settings_importing_data));
                    dialog.show();

                    // Prepare new database thread.
                    Thread thread = new Thread() {
                        public void run() {
                            // Populate database with test data.
                            AppDatabase.getInstance(getContext()).populate();
                        }
                    };

                    // Start and wait for thread.
                    try {
                        thread.start();
                        thread.join();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    // Dismiss loading dialog with some delay.
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 1500);

                    // Return click handled.
                    return true;
                }
            });

            // Bind the delete data action.
            deleteData.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    // Show loading dialog.
                    final ProgressDialog dialog = new ProgressDialog(getContext());
                    dialog.setMessage(getResources().getString(R.string.settings_deleting_data));
                    dialog.show();

                    // Prepare new database thread.
                    Thread thread = new Thread() {
                        public void run() {
                            // Reset the entire database.
                            AppDatabase.getInstance(getContext()).reset();
                        }
                    };

                    // Start and wait for thread.
                    try {
                        thread.start();
                        thread.join();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    // Dismiss loading dialog with some delay.
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 1500);

                    // Return click handled.
                    return true;
                }
            });
        }

    }

}
