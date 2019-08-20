package com.rejsebuddy.storage.recent.tasks;

import android.content.Context;

import com.rejsebuddy.helpers.AsyncWrapper;
import com.rejsebuddy.storage.AppDatabase;
import com.rejsebuddy.storage.recent.Recent;

public class CreateRecentTask<Instance> extends AsyncWrapper<Recent, Void, Instance> {

    /**
     * Call parent super constructor.
     *
     * @param ctx The application context.
     * @param instance The current class instance.
     */
    public CreateRecentTask(Context ctx, Instance instance) {
        super(ctx, instance);
    }

    /**
     * Create the passed recent.
     *
     * @param recents The recent to be processed.
     */
    @Override
    public Void doInBackground(Recent... recents) {
        AppDatabase.getInstance(this.getContext()).recents().insert(recents[0]);
        return null;
    }

}
