package com.rejsebuddy.contacts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rejsebuddy.R;
import com.rejsebuddy.storage.contact.Contact;
import com.rejsebuddy.storage.contact.tasks.FindContactTask;

public class ContactsEditorActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * The current contact instance.
     */
    private Contact contact;

    /**
     * Initiate the activity.
     *
     * @param state The previous state.
     */
    @Override
    protected void onCreate(Bundle state) {
        // Initiate the content view.
        super.onCreate(state);
        setContentView(R.layout.activity_contacts_editor);

        // Enable the return button.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get passed contact ID from intent and fetch.
        int contactID = getIntent().getIntExtra("CONTACT_ID", -1);
        if (contactID != -1) {
            new FindContact(this, this).execute(contactID);
        }

        // Bind save contact floating action button.
        FloatingActionButton save = findViewById(R.id.save_contacts_fab);
        save.setOnClickListener(this);
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
     * Saves the contacts changes.
     *
     * @param view The activity view.
     */
    public void onClick(View view) {
        // TODO: Save changes
    }

    /**
     * Fetches contact for the passed id.
     */
    private static class FindContact extends FindContactTask<ContactsEditorActivity> {

        /**
         * Call parent super constructor.
         *
         * @param ctx The application context.
         */
        FindContact(Context ctx, ContactsEditorActivity instance) {
            super(ctx, instance);
        }

        /**
         * Saves the result and updates action bar title.
         *
         * @param result The fetched contact.
         */
        protected void onPostExecute(Contact result) {
            // Save the contact result.
            this.getInstance().contact = result;

            // Update the action bar title with name.
            this.getInstance().getSupportActionBar().setTitle(result.getName());
        }

    }

}
