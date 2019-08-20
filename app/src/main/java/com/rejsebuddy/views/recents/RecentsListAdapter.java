package com.rejsebuddy.views.recents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rejsebuddy.R;
import com.rejsebuddy.storage.recent.Recent;

import java.util.List;

class RecentsListAdapter extends RecyclerView.Adapter<RecentsListHolder> {

    /**
     * The list of found recents.
     */
    final private List<Recent> recents;

    /**
     * Initiates the class instance with the connections.
     *
     * @param recents The recents list to be shown.
     */
    public RecentsListAdapter(List<Recent> recents) {
        this.recents = recents;
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
    public RecentsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        // Inflate the connection row view.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_recent, parent, false);

        // Return new view holder instance.
        return new RecentsListHolder(view);
    }

    /**
     * Populates the row once fully bound.
     *
     * @param holder The view holder.
     * @param index The index of the current view.
     */
    @Override
    public void onBindViewHolder(@NonNull RecentsListHolder holder, int index) {
        holder.setRecent(this.recents.get(index));
    }

    /**
     * Returns the amount of list items.
     *
     * @return The amount of items.
     */
    @Override
    public int getItemCount() {
        return this.recents.size();
    }

}
