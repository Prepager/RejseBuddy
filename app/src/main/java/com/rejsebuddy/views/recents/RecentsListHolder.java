package com.rejsebuddy.views.recents;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rejsebuddy.R;
import com.rejsebuddy.storage.recent.Recent;
import com.rejsebuddy.views.connections.ConnectionsListActivity;

class RecentsListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    /**
     * The recent instance.
     */
    private Recent recent;

    /**
     * The recent origin name text view.
     */
    final private TextView origin_name;

    /**
     * The recent destination name text view.
     */
    final private TextView destination_name;

    /**
     * Populates the layout element holders.
     *
     * @param view The recent row view.
     */
    RecentsListHolder(View view) {
        // Call view holder super.
        super(view);

        // Bind the view elements.
        this.origin_name = view.findViewById(R.id.origin_name);
        this.destination_name = view.findViewById(R.id.destination_name);

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

        // Start activity with to and from address.
        Intent intent = new Intent(ctx, ConnectionsListActivity.class);
        intent.putExtra("RECENT", true);
        intent.putExtra("TO_ADDRESS", this.recent.getDestAddressInstance());
        intent.putExtra("FROM_ADDRESS", this.recent.getOrigAddressInstance());
        ctx.startActivity(intent);
    }

    /**
     * Sets the recent details on the view.
     *
     * @param recent The list row recent.
     */
    public void setRecent(Recent recent) {
        // Save the recent instance.
        this.recent = recent;

        // Set address text on row view.
        this.origin_name.setText(recent.getOrigAddress());
        this.destination_name.setText(recent.getDestAddress());
    }
}
