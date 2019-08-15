package com.rejsebuddy.helpers;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public abstract class AsyncWrapper<Params, Result> extends AsyncTask<Params, Void, Result> {

    /**
     * Weak referenced context to prevent leak.
     * Async may run longer than activity making it not get garbage collected.
     */
    protected WeakReference<Context> ctx;

    /**
     * Save weak referenced context.
     *
     * @param ctx The application context.
     */
    public AsyncWrapper(Context ctx) {
        this.ctx = new WeakReference<>(ctx);
    }

    /**
     * Gathers context from weak reference.
     *
     * @return The application context.
     */
    protected Context getContext() {
        return ctx.get();
    }

}
