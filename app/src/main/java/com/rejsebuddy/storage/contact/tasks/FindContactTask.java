package com.rejsebuddy.storage.contact.tasks;

import android.content.Context;

import com.rejsebuddy.helpers.AsyncWrapper;
import com.rejsebuddy.storage.AppDatabase;
import com.rejsebuddy.storage.contact.Contact;

public class FindContactTask extends AsyncWrapper<Integer, Contact> {

    /**
     * Call parent super constructor.
     *
     * @param ctx The application context.
     */
    public FindContactTask(Context ctx) {
        super(ctx);
    }

    /**
     * Fetches and returns contact for passed id.
     *
     * @param ids The id to be fetched.
     * @return The fetched contact.
     */
    @Override
    public Contact doInBackground(Integer... ids) {
        return AppDatabase.getInstance(this.getContext()).contacts().find(ids[0]);
    }

}
