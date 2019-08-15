package com.rejsebuddy.helpers;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public abstract class AsyncWrapper<Params, Result, Instance> extends AsyncTask<Params, Void, Result> {

    /**
     * Weak referenced context to prevent leak.
     * Async may run longer than activity making it not get garbage collected.
     */
    protected WeakReference<Context> ctx;

    /**
     * Weak reference for the current class.
     */
    protected WeakReference<Instance> instance;

    /**
     * Save weak referenced context.
     *
     * @param ctx The application context.
     * @param instance The current class instance.
     */
    public AsyncWrapper(Context ctx, Instance instance) {
        this.ctx = new WeakReference<>(ctx);
        this.instance = new WeakReference<>(instance);
    }

    /**
     * Gathers context from weak reference.
     *
     * @return The application context.
     */
    protected Context getContext() {
        return this.ctx.get();
    }

    /**
     * Gathers instance from weak reference.
     *
     * @return The current class.
     */
    protected Instance getInstance() {
        return this.instance.get();
    }

}
