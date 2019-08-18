package com.rejsebuddy.views.contacts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.rejsebuddy.R;
import com.rejsebuddy.api.models.Address;
import com.rejsebuddy.storage.contact.Contact;
import com.rejsebuddy.storage.contact.tasks.CreateOrUpdateContactTask;
import com.rejsebuddy.storage.contact.tasks.FindContactTask;
import com.rejsebuddy.views.address.AddressInputFragment;

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
     * The address input holder.
     */
    private Address address;

    /**
     * The activity save button.
     */
    private Button save;

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

        // Bind save contact button.
        this.save = findViewById(R.id.save);
        this.save.setOnClickListener(this);
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
        // Get the name input text.
        String name = this.nameInput.getText().toString();
        if (name.equals("")) {
            name = this.getString(R.string.missing_name);
        }

        // Update contact details or create new.
        if (this.contact != null) {
            // Set new contact name.
            this.contact.setName(name);

            // Set address instance if has selection.
            if (this.address != null) {
                this.contact.setAddressInstance(this.address);
            }
        } else {
            // Create new contact instance.
            this.contact = new Contact(name, this.address);
        }

        // Create or update the passed contact.
        new CreateOrUpdateContact(this, this).execute(this.contact);
    }

    /**
     * Saves the address changes.
     *
     * @param view The activated view.
     * @param address The selected address.
     */
    @Override
    public void onAddressChanged(View view, Address address) {
        // Save address on instance.
        this.address = address;

        // Enable the save button.
        this.save.setEnabled(true);
    }

    /**
     * Either creates or updates the passed contact.
     */
    private static class CreateOrUpdateContact extends CreateOrUpdateContactTask<ContactsEditorActivity> {

        /**
         * Call parent super constructor.
         *
         * @param ctx The application context.
         */
        CreateOrUpdateContact(Context ctx, ContactsEditorActivity instance) {
            super(ctx, instance);
        }

        /**
         * Return back to the contacts view.
         *
         * @param result The void result.
         */
        protected void onPostExecute(Void result) {
            this.getInstance().onSupportNavigateUp();
        }

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

            // Set text on inputs and enable save.
            instance.nameInput.setText(result.getName());
            instance.addressInput.setAddress(result.getAddressIntance());
            instance.save.setEnabled(true);
        }

    }

}
