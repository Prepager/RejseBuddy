package com.rejsebuddy.contacts;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rejsebuddy.R;
import com.rejsebuddy.storage.AppDatabase;
import com.rejsebuddy.storage.contact.Contact;

public class ContactsEditorActivity extends AppCompatActivity {

    /**
     * TODO
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

        // Get passed contact ID from intent.
        final int contactID = getIntent().getIntExtra("CONTACT_ID", -1);
        if (contactID != -1) {
            // Fetch the passed contact.
            new Thread(new Runnable() {
                @Override
                public void run() {
                    contact = AppDatabase.getInstance(getBaseContext()).contacts().find(contactID);
                    getSupportActionBar().setTitle(contact.getName());
                }
            }).start();
        }

        // Bind save contact floating action button.
        FloatingActionButton save = findViewById(R.id.save_contacts_fab);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO
            }

        });
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

}
