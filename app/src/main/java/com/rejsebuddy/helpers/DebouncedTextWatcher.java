package com.rejsebuddy.helpers;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.Timer;
import java.util.TimerTask;

public abstract class DebouncedTextWatcher implements TextWatcher {

    /**
     * The debounce timer instance.
     */
    private Timer timer;

    /**
     * Called before the text sequence is changed.
     *
     * @param sequence The current char sequence.
     * @param before The chars before.
     * @param start The start position.
     * @param count The amount of characters.
     */
    @Override
    public void beforeTextChanged(CharSequence sequence, int start, int count, int before) {
        // Left blank intentionally.
    }

    /**
     * Cancels the current countdown timer on change.
     *
     * @param sequence The current char sequence.
     * @param start The start position.
     * @param before The chars before.
     * @param count The amount of characters.
     */
    @Override
    public void onTextChanged(CharSequence sequence, int start, int before, int count) {
        // Cancel if available.
        if (this.timer != null) {
            timer.cancel();
        }
    }

    /**
     * Start timer after text was changed.
     *
     * @param editable The final editable text.
     */
    @Override
    public void afterTextChanged(final Editable editable) {
        // Create new timer instance.
        this.timer = new Timer();

        // Schedule new task with delay.
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Call text changed function.
                textChanged(editable.toString());
            }
        }, 250);
    }

    /**
     * The debounced text change listeners.
     *
     * @param str The final text string.
     */
    protected abstract void textChanged(String str);
}
