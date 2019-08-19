package com.rejsebuddy.views.connections;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rejsebuddy.R;
import com.rejsebuddy.api.models.Connection;
import com.rejsebuddy.api.models.ConnectionStep;
import com.rejsebuddy.api.models.ConnectionStepPoint;
import com.rejsebuddy.views.connection.ConnectionListActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

class ConnectionsListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    /**
     * The connection instance.
     */
    private Connection connection;

    /**
     * The connection end time text view.
     */
    final private TextView end_time;

    /**
     * The connection start time text view.
     */
    final private TextView start_time;

    /**
     * The connection duration text view.
     */
    final private TextView duration;

    /**
     * The connection changes text view.
     */
    final private TextView changes;

    /**
     * Populates the layout element holders.
     *
     * @param view The contact row view.
     */
    ConnectionsListHolder(View view) {
        // Call view holder super.
        super(view);

        // Bind the view elements.
        this.changes = view.findViewById(R.id.changes);
        this.duration = view.findViewById(R.id.duration);
        this.end_time = view.findViewById(R.id.end_time);
        this.start_time = view.findViewById(R.id.start_time);

        // Bind single click listeners.
        view.setOnClickListener(this);
    }

    /**
     * Bind the row single click event.
     *
     * @param view The current view.
     */
    public void onClick(View view) {
        // Get context from view.
        Context ctx = view.getContext();

        // Start activity with to address.
        Intent intent = new Intent(ctx, ConnectionListActivity.class);
        intent.putExtra("CONNECTION", this.connection);
        ctx.startActivity(intent);
    }

    /**
     * Sets the contact details on the view.
     *
     * @param connection The list row connection.
     */
    public void setConnection(Connection connection) {
        // Save the connection instance.
        this.connection = connection;

        // Skip if steps is empty.
        List<ConnectionStep> steps = connection.getSteps();
        if (steps.isEmpty()) return;

        // Get start and end step.
        ConnectionStepPoint start = steps.get(0).getOrigin();
        ConnectionStepPoint end = steps.get(steps.size() - 1).getDestination();

        // Prepare date hour and minute formatter.
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.UK);

        // Get the current view context.
        Context ctx = this.itemView.getContext();

        // Find difference in hours and minutes.
        long diff = end.getDate().getTime() - start.getDate().getTime();
        long hours = diff / (1000 * 60 * 60) % 24;
        diff -=  hours * (1000 * 60 * 60);
        long minutes = diff / (1000 * 60) % 60;

        // Format hours and minutes as time.
        String duration = String.format(Locale.UK, "%02d:%02d", hours, minutes);

        // Set view text element text.
        this.end_time.setText(formatter.format(end.getDate()));
        this.start_time.setText(formatter.format(start.getDate()));
        this.changes.setText(ctx.getString(R.string.amount_changes, steps.size() - 1));
        this.duration.setText(ctx.getString(R.string.amount_duration, duration));
    }
}
