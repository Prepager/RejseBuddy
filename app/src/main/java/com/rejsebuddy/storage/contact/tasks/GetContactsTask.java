package com.rejsebuddy.storage.contact.tasks;

import android.content.Context;

import com.rejsebuddy.helpers.AsyncWrapper;
import com.rejsebuddy.storage.AppDatabase;
import com.rejsebuddy.storage.contact.Contact;

import java.util.List;

abstract public class GetContactsTask<Instance> extends AsyncWrapper<Void, List<Contact>, Instance> {

    /**
     * Call parent super constructor.
     *
     * @param ctx The application context.
     * @param instance The current class instance.
     */
    public GetContactsTask(Context ctx, Instance instance) {
        super(ctx, instance);
    }

    /**
     * Fetches and returns all contacts from database.
     *
     * @return List of fetched contacts.
     */
    @Override
    public List<Contact> doInBackground(Void... voids) {
        return AppDatabase.getInstance(this.getContext()).contacts().all();
    }

}
