package com.rejsebuddy.views.connections;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rejsebuddy.R;
import com.rejsebuddy.api.models.Connection;

import java.util.List;

class ConnectionsListAdapter extends RecyclerView.Adapter<ConnectionsListHolder> {

    /**
     * The list of found connections.
     */
    final private List<Connection> connections;

    /**
     * Initiates the class instance with the connections.
     *
     * @param connections The connections list to be shown.
     */
    public ConnectionsListAdapter(List<Connection> connections) {
        this.connections = connections;
    }

    /**
     * Inflates and returns a new connection row instance.
     *
     * @param parent The parent element context.
     * @param type The view type of the new View.
     * @return View holder of the connection row.
     */
    @NonNull
    @Override
    public ConnectionsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        // Inflate the connection row view.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_connection, parent, false);

        // Return new view holder instance.
        return new ConnectionsListHolder(view);
    }

    /**
     * Populates the row once fully bound.
     *
     * @param holder The view holder.
     * @param index The index of the current view.
     */
    @Override
    public void onBindViewHolder(@NonNull ConnectionsListHolder holder, int index) {
        holder.setConnection(this.connections.get(index));
    }

    /**
     * Returns the amount of list items.
     *
     * @return The amount of items.
     */
    @Override
    public int getItemCount() {
        return this.connections.size();
    }

}
