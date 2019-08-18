package com.rejsebuddy.views.connections;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rejsebuddy.R;
import com.rejsebuddy.api.models.Address;
import com.rejsebuddy.api.models.Connection;
import com.rejsebuddy.api.tasks.GetConnectionsTask;

import java.util.ArrayList;
import java.util.List;

public class ConnectionsListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    /**
     * The selected to address.
     */
    private Address to;

    /**
     * The selected from address.
     */
    private Address from;

    /**
     * The list of found connections.
     */
    private List<Connection> connections = new ArrayList<>();

    /**
     * The connections data adapter.
     */
    private ConnectionsListAdapter adapter;

    /**
     * The swipe to refresh element.
     */
    private SwipeRefreshLayout refresher;

    /**
     * Initiate the activity.
     *
     * @param state The previous state.
     */
    @Override
    protected void onCreate(Bundle state) {
        // Initiate the content view.
        super.onCreate(state);
        setContentView(R.layout.activity_connections);

        // Enable the return button.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get to/from addresses from intent.
        this.to = (Address) getIntent().getSerializableExtra("TO_ADDRESS");
        this.from = (Address) getIntent().getSerializableExtra("FROM_ADDRESS");

        // Create new connections adapter.
        this.adapter = new ConnectionsListAdapter(this.connections);

        // Find recycler view and set adapter.
        RecyclerView list = findViewById(R.id.connections_list);
        list.setAdapter(this.adapter);

        // Set recycler view layout manager.
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // Bind swipe up refresh action and enable loading.
        this.refresher = findViewById(R.id.connections_swipe_refresh);
        this.refresher.setOnRefreshListener(this);
        this.refresher.setRefreshing(true);

        // Populate the connections list.
        this.onRefresh();
    }

    /**
     * Return back to previous view on click.
     *
     * @return Whether or not an action was performed.
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Populate the connections list on refresh.
     */
    public void onRefresh() {
        Pair<Address, Address> pair = new Pair<>(this.from, this.to);
        new GetConnections(this, this).execute(pair);
    }

    /**
     * Fetch all possible connections from API.
     */
    private static class GetConnections extends GetConnectionsTask<ConnectionsListActivity> {

        /**
         * Call parent super constructor.
         *
         * @param ctx The application context.
         * @param instance The current class instance.
         */
        GetConnections(Context ctx, ConnectionsListActivity instance) {
            super(ctx, instance);
        }

        /**
         * Save found async connections results.
         *
         * @param result The query list result.
         */
        protected void onPostExecute(List<Connection> result) {
            // Get the parent instance.
            ConnectionsListActivity instance = this.getInstance();

            // Clear list and add new.
            instance.connections.clear();
            instance.connections.addAll(result);

            // Notify of change and stop loading.
            instance.adapter.notifyDataSetChanged();
            instance.refresher.setRefreshing(false);
        }

    }

}
