package com.rejsebuddy.views.recents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rejsebuddy.R;
import com.rejsebuddy.storage.contact.Contact;
import com.rejsebuddy.storage.contact.tasks.GetContactsTask;
import com.rejsebuddy.storage.recent.Recent;
import com.rejsebuddy.storage.recent.tasks.GetRecentsTask;

import java.util.ArrayList;
import java.util.List;

public class RecentsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    /**
     * The contacts data adapter.
     */
    private RecentsListAdapter adapter;

    /**
     * The swipe to refresh element.
     */
    private SwipeRefreshLayout refresher;

    /**
     * The list of app recents.
     */
    final private List<Recent> recents = new ArrayList<>();

    /**
     * Inflates the recents fragment.
     *
     * @param inflater The layout inflater instance.
     * @param container The group view container.
     * @param state The saved state of the view.
     * @return The inflated fragment view.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle state) {
        return inflater.inflate(R.layout.fragment_recents_list, container, false);
    }

    /**
     * Connects the recents adapter to the view.
     *
     * @param view The contacts row view.
     * @param state The previous view state.
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle state) {
        // Call super class.
        super.onViewCreated(view, state);

        // Create new contacts adapter.
        this.adapter = new RecentsListAdapter(recents);

        // Find recycler view and set adapter.
        RecyclerView list = view.findViewById(R.id.recents_list);
        list.setAdapter(this.adapter);

        // Set recycler view layout manager.
        list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        // Bind swipe up refresh action and enable loading.
        this.refresher = view.findViewById(R.id.recents_swipe_refresh);
        this.refresher.setOnRefreshListener(this);
        this.refresher.setRefreshing(true);

        // Populate the recents list.
        this.onRefresh();
    }

    /**
     * Populate the recents list on refresh.
     */
    public void onRefresh() {
        new GetRecents(getContext(), this).execute();
    }

    /**
     * Refresh the data on fragment resume.
     */
    @Override
    public void onResume() {
        super.onResume();
        this.onRefresh();
    }

    /**
     * Fetch all contacts from database.
     */
    private static class GetRecents extends GetRecentsTask<RecentsListFragment> {

        /**
         * Call parent super constructor.
         *
         * @param ctx The application context.
         * @param instance The current class instance.
         */
        GetRecents(Context ctx, RecentsListFragment instance) {
            super(ctx, instance);
        }

        /**
         * Save result and disable refreshing state.
         *
         * @param result The query list result.
         */
        protected void onPostExecute(List<Recent> result) {
            // Get the parent instance.
            RecentsListFragment instance = this.getInstance();

            // Clear list and add new.
            instance.recents.clear();
            instance.recents.addAll(result);

            // Notify of change and stop loading.
            instance.adapter.notifyDataSetChanged();
            instance.refresher.setRefreshing(false);
        }

    }

}
