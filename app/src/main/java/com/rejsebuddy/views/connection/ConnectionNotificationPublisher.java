package com.rejsebuddy.views.connection;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import com.rejsebuddy.R;
import com.rejsebuddy.api.models.ConnectionStep;

@SuppressWarnings("FieldCanBeLocal")
public class ConnectionNotificationPublisher extends BroadcastReceiver {

    /**
     * The notification channel id.
     */
    private final String channelID = "updates";

    /**
     * Handle the incoming broadcast receiver event.
     *
     * @param ctx The application context.
     * @param intent The activated pending intent.
     */
    @Override
    public void onReceive(Context ctx, Intent intent) {
        // Get passed bundle and extract connection step.
        Bundle bundle = intent.getBundleExtra("BUNDLE");
        ConnectionStep step = (ConnectionStep) bundle.getSerializable("STEP");
        if (step == null) return;

        // Get notification manager from system services.
        NotificationManager manager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager == null) return;

        // Register notification channel if above Android 8.0.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Define constants for notification channel.
            String name = ctx.getString(R.string.channel_name);
            String description = ctx.getString(R.string.channel_description);

            // Define importance of incoming notifications.
            int importance = NotificationManager.IMPORTANCE_HIGH;

            // Create new channel and set description.
            NotificationChannel channel = new NotificationChannel(this.channelID, name, importance);
            channel.setDescription(description);

            // Create channel on notifier.
            manager.createNotificationChannel(channel);
        }

        // Generate notification title.
        String title = ctx.getString(R.string.change) + ": "
            + step.getName() + ", "
            + step.getOrigin().getName();

        // Generate notification text.
        String text = step.getNotes().replace(";", " ");

        // Make new notification builder.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx, this.channelID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(title)
            .setContentText(text);

        // Send out the notification.
        manager.notify(1, builder.build());
    }

}
