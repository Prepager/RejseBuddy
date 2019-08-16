package com.rejsebuddy.contacts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rejsebuddy.R;
import com.rejsebuddy.address.Address;
import com.rejsebuddy.address.AddressInputFragment;
import com.rejsebuddy.storage.contact.Contact;
import com.rejsebuddy.storage.contact.tasks.FindContactTask;

public class ContactsEditorActivity extends AppCompatActivity implements View.OnClickListener, AddressInputFragment.OnAddressChangeListener {

    /**
     * The current contact instance.
     */
    private Contact contact;

    /**
     * The address name input.
     */
    private EditText nameInput;

    /**
     * The address input fragment.
     */
    private AddressInputFragment addressInput;

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

        // Save the name input.
        this.nameInput = findViewById(R.id.name);

        // Save the address input fragment.
        FragmentManager manager = getSupportFragmentManager();
        this.addressInput = (AddressInputFragment) manager.findFragmentById(R.id.address);

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
     * Saves the address changes.
     *
     * @param address The selected address.
     */
    @Override
    public void onAddressChanged(Address address) {
        // TODO: Save address.
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
            // Get the parent instance.
            ContactsEditorActivity instance = this.getInstance();

            // Save the contact result.
            instance.contact = result;

            // Update the action bar title with name.
            instance.getSupportActionBar().setTitle(result.getName());

            // Set text on inputs.
            instance.nameInput.setText(result.getName());
            instance.addressInput.setAddress(result.getAddressIntance());
        }

    }

}
