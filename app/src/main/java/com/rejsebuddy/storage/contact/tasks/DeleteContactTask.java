package com.rejsebuddy.storage.contact.tasks;

import android.content.Context;

import com.rejsebuddy.helpers.AsyncWrapper;
import com.rejsebuddy.storage.AppDatabase;
import com.rejsebuddy.storage.contact.Contact;

public class DeleteContactTask<Instance> extends AsyncWrapper<Contact, Void, Instance> {

    /**
     * Call parent super constructor.
     *
     * @param ctx The application context.
     * @param instance The current class instance.
     */
    public DeleteContactTask(Context ctx, Instance instance) {
        super(ctx, instance);
    }

    /**
     * Deletes contact for passed instance.
     *
     * @param contacts The contact to be deleted.
     */
    @Override
    public Void doInBackground(Contact... contacts) {
        AppDatabase.getInstance(this.getContext()).contacts().delete(contacts[0]);
        return null;
    }

}
