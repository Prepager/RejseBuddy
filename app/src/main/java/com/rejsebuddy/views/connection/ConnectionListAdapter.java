package com.rejsebuddy.views.connection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rejsebuddy.R;
import com.rejsebuddy.api.models.ConnectionStep;

import java.util.List;

class ConnectionListAdapter extends RecyclerView.Adapter<ConnectionListHolder> {

    /**
     * The list of found connections steps.
     */
    final private List<ConnectionStep> steps;

    /**
     * Initiates the class instance with the connections.
     *
     * @param steps The connection steps list to be shown.
     */
    ConnectionListAdapter(List<ConnectionStep> steps) {
        this.steps = steps;
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
    public ConnectionListHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        // Inflate the connection row view.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_connection_step, parent, false);

        // Return new view holder instance.
        return new ConnectionListHolder(view);
    }

    /**
     * Populates the row once fully bound.
     *
     * @param holder The view holder.
     * @param index The index of the current view.
     */
    @Override
    public void onBindViewHolder(@NonNull ConnectionListHolder holder, int index) {
        holder.setConnectionStep(this.steps.get(index));
    }

    /**
     * Returns the amount of list items.
     *
     * @return The amount of items.
     */
    @Override
    public int getItemCount() {
        return this.steps.size();
    }

}
