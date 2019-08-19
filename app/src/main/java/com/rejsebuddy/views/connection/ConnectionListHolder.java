package com.rejsebuddy.views.connection;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rejsebuddy.R;
import com.rejsebuddy.api.models.ConnectionStep;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class ConnectionListHolder extends RecyclerView.ViewHolder {

    /**
     * The step name text view.
     */
    final private TextView name;

    /**
     * The step notes text view.
     */
    final private TextView notes;

    /**
     * The step origin time text view.
     */
    final private TextView origin_time;

    /**
     * The step origin name text view.
     */
    final private TextView origin_name;

    /**
     * The step destination time text view.
     */
    final private TextView destination_time;

    /**
     * The step destination name text view.
     */
    final private TextView destination_name;

    /**
     * Populates the layout element holders.
     *
     * @param view The contact row view.
     */
    ConnectionListHolder(View view) {
        // Call view holder super.
        super(view);

        // Bind the view elements.
        this.name = view.findViewById(R.id.step_name);
        this.notes = view.findViewById(R.id.step_notes);
        this.origin_time = view.findViewById(R.id.origin_time);
        this.origin_name = view.findViewById(R.id.origin_name);
        this.destination_time = view.findViewById(R.id.destination_time);
        this.destination_name = view.findViewById(R.id.destination_name);
    }

    /**
     * Sets the contact details on the view.
     *
     * @param step The list row connection step.
     */
    void setConnectionStep(ConnectionStep step) {
        // Prepare date hour and minute formatter.
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.UK);

        // Set view text element text.
        this.name.setText(step.getName());
        this.notes.setText(step.getNotes().replace(";", " "));
        this.origin_name.setText(step.getOrigin().getName());
        this.origin_time.setText(formatter.format(step.getOrigin().getDate()));
        this.destination_name.setText(step.getDestination().getName());
        this.destination_time.setText(formatter.format(step.getDestination().getDate()));
    }
}
