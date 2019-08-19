package com.rejsebuddy.storage.contact.tasks;

import android.content.Context;

import com.rejsebuddy.helpers.AsyncWrapper;
import com.rejsebuddy.storage.AppDatabase;
import com.rejsebuddy.storage.contact.Contact;

public class FindContactTask<Instance> extends AsyncWrapper<Integer, Contact, Instance> {

    /**
     * Call parent super constructor.
     *
     * @param ctx The application context.
     * @param instance The current class instance.
     */
    protected FindContactTask(Context ctx, Instance instance) {
        super(ctx, instance);
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
