package com.rejsebuddy.storage.contact.tasks;

import android.content.Context;

import com.rejsebuddy.helpers.AsyncWrapper;
import com.rejsebuddy.storage.AppDatabase;
import com.rejsebuddy.storage.contact.Contact;

import java.util.List;

public class GetContactsTask extends AsyncWrapper<Void, List<Contact>> {

    /**
     * Call parent super constructor.
     *
     * @param ctx The application context.
     */
    public GetContactsTask(Context ctx) {
        super(ctx);
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
