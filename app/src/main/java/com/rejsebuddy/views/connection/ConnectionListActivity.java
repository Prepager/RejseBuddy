package com.rejsebuddy.views.connection;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
     * The connections data adapter.
     */
    private ConnectionListAdapter adapter;

    /**
     * The list of found connections steps.
     */
    private final List<ConnectionStep> steps = new ArrayList<>();

    /**
     * The list of pending intent alarms.
     */
    private final List<PendingIntent> intents = new ArrayList<>();

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
        this.unregisterAlarms();
        this.onBackPressed();
        return true;
    }

    /**
     * Unregister alarms on destroy.
     */
    public void onDestroy() {
        super.onDestroy();
        this.unregisterAlarms();
    }

    /**
     * Populate the connections list on refresh.
     */
    private void onRefresh() {
        // Unregister current alarms.
        this.unregisterAlarms();

        // Clear list and add new.
        this.steps.clear();
        this.steps.addAll(this.connection.getSteps());

        // Register the step alarms.
        this.registerAlarms();

        // Notify adapter of change.
        this.adapter.notifyDataSetChanged();
    }

    /**
     * Register alarms for the current connection.
     */
    private void registerAlarms() {
        // Get the system alarm manager instance.
        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        // Loop through all the connection steps.
        for(int i = 0; i < this.steps.size(); i++) {
            // Get the current loop step.
            ConnectionStep step = this.steps.get(i);

            // Create new intent for step notification publisher.
            Intent intent = new Intent(this, ConnectionNotificationPublisher.class);

            // Wrap serializable in bundle to prevent loss of extra.
            Bundle bundle = new Bundle();
            bundle.putSerializable("STEP", step);
            intent.putExtra("BUNDLE", bundle);

            // Convert intent to pending intent.
            PendingIntent pending = PendingIntent.getBroadcast(
                this, i, intent, PendingIntent.FLAG_UPDATE_CURRENT
            );

            // Find the alarm offset in milliseconds (1 minutes in advance).
            long offset = step.getOrigin().getDate().getTime() - 60000;
            // TESTING: (new Date()).getTime() + ((i + 1) * 2500);

            // Attempt to set wall-clock alarm for intent.
            try {
                manager.setExact(AlarmManager.RTC_WAKEUP, offset, pending);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Add the pending intent to the list.
            this.intents.add(pending);
        }
    }

    /**
     * Unregister alarms for the current connection.
     */
    private void unregisterAlarms() {
        // Get the system alarm manager instance.
        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        // Loop through all the pending intents.
        for (PendingIntent intent : this.intents) {
            try {
                // Cancel the found intent.
                manager.cancel(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Clear the pending intents list.
        this.intents.clear();
    }

}
