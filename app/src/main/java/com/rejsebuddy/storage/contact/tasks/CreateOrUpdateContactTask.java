package com.rejsebuddy.storage.contact.tasks;

import android.content.Context;

import com.rejsebuddy.helpers.AsyncWrapper;
import com.rejsebuddy.storage.AppDatabase;
import com.rejsebuddy.storage.contact.Contact;

public class CreateOrUpdateContactTask<Instance> extends AsyncWrapper<Contact, Void, Instance> {

    /**
     * Call parent super constructor.
     *
     * @param ctx The application context.
     * @param instance The current class instance.
     */
    protected CreateOrUpdateContactTask(Context ctx, Instance instance) {
        super(ctx, instance);
    }

    /**
     * Create or update the passed contact.
     *
     * @param contacts The contact to be processed.
     */
    @Override
    public Void doInBackground(Contact... contacts) {
        // Get application database instance.
        AppDatabase db = AppDatabase.getInstance(this.getContext());

        // Get contact from parameters.
        Contact contact = contacts[0];

        // Update if has id or insert.
        if (contact.getId() != 0) {
            db.contacts().update(contact);
        } else {
            db.contacts().insert(contact);
        }

        // Return void.
        return null;
    }

}
