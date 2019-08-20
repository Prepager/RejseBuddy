package com.rejsebuddy.storage.recent.tasks;

import android.content.Context;

import com.rejsebuddy.helpers.AsyncWrapper;
import com.rejsebuddy.storage.AppDatabase;
import com.rejsebuddy.storage.recent.Recent;

import java.util.List;

public class GetRecentsTask<Instance> extends AsyncWrapper<Void, List<Recent>, Instance> {

    /**
     * Call parent super constructor.
     *
     * @param ctx The application context.
     * @param instance The current class instance.
     */
    protected GetRecentsTask(Context ctx, Instance instance) {
        super(ctx, instance);
    }

    /**
     * Fetches and returns all recents from database.
     *
     * @return List of fetched recents.
     */
    @Override
    public List<Recent> doInBackground(Void... voids) {
        return AppDatabase.getInstance(this.getContext()).recents().all();
    }

}
