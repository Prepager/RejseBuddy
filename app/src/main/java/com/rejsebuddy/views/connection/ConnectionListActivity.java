package com.rejsebuddy.views.connection;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rejsebuddy.R;
import com.rejsebuddy.api.models.Connection;
import com.rejsebuddy.api.models.ConnectionStep;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConnectionListActivity extends AppCompatActivity {

    /**
     * The connection instance.
     */
    private Connection connection;

    /**
     * The list of found connections steps.
     */
    private final List<ConnectionStep> steps = new ArrayList<>();

    /**
     * The connections data adapter.
     */
    private ConnectionListAdapter adapter;

    /**
     * Initiate the activity.
     *
     * @param state The previous state.
     */
    @Override
    protected void onCreate(Bundle state) {
        // Initiate the content view.
        super.onCreate(state);
        setContentView(R.layout.activity_connection);

        // Enable the return button.
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Get to/from addresses from intent.
        this.connection = (Connection) getIntent().getSerializableExtra("CONNECTION");

        // Create new connections adapter.
        this.adapter = new ConnectionListAdapter(this.steps);

        // Find recycler view and set adapter.
        RecyclerView list = findViewById(R.id.connection_list);
        list.setAdapter(this.adapter);

        // Set recycler view layout manager.
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

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
        // Clear list and add new.
        this.steps.clear();
        this.steps.addAll(this.connection.getSteps());

        // Notify adapter of change.
        this.adapter.notifyDataSetChanged();
    }

}
