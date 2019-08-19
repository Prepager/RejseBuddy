package com.rejsebuddy.api.tasks;

import android.content.Context;

import com.rejsebuddy.api.Consumer;
import com.rejsebuddy.api.models.Address;
import com.rejsebuddy.api.models.Connection;
import com.rejsebuddy.api.models.ConnectionStep;
import com.rejsebuddy.api.models.ConnectionStepPoint;
import com.rejsebuddy.helpers.AsyncWrapper;

import org.xmlpull.v1.XmlPullParser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GetConnectionsTask<Instance> extends AsyncWrapper<Address, List<Connection>, Instance> {

    /**
     * Call parent super constructor.
     *
     * @param ctx The application context.
     * @param instance The current class instance.
     */
    protected GetConnectionsTask(Context ctx, Instance instance) {
        super(ctx, instance);
    }

    /**
     * Fetches and returns locations for passed search.
     *
     * @param addresses The addresses to be fetched.
     * @return The fetched locations.
     */
    @Override
    public List<Connection> doInBackground(Address... addresses) {
        // Create new results holder list.
        List<Connection> result = new ArrayList<>();

        // Attempt to fetch connections for pairs.
        try {
            // Request connections for address pair.
            XmlPullParser parser = new Consumer().getConnections(addresses[0], addresses[1]);
            parser.next();

            // Get the current tag type.
            int type = parser.getEventType();

            // Prepare temporary variables.
            boolean cancelled = false;
            List<ConnectionStep> steps = new ArrayList<>();
            ConnectionStepPoint origin = null;
            ConnectionStepPoint destination = null;

            // Continue loop while not at document end.
            while (type != XmlPullParser.END_DOCUMENT) {
                // Check if starting or ending tag.
                if (type == XmlPullParser.START_TAG && parser.getName() != null) {
                    // Switch the parser tag name.
                    switch (parser.getName()) {
                        // Parse the trip tag.
                        case "Trip":
                            String value = parser.getAttributeValue(null, "cancelled");
                            cancelled = value != null && value.equals("true");
                            break;

                        // Parse origin and destination tag.
                        case "Origin":
                        case "Destination":
                            // Create date string by combining date and time.
                            String dateStr = parser.getAttributeValue(null, "date") + " " +
                                parser.getAttributeValue(null, "time");

                            // Create new date parser for format.
                            SimpleDateFormat dateParser = new SimpleDateFormat(
                                "dd.MM.yy HH:mm", Locale.UK
                            );

                            // Create new connection step point.
                            ConnectionStepPoint point = new ConnectionStepPoint(
                                parser.getAttributeValue(null, "name"),
                                dateParser.parse(dateStr)
                            );

                            // Check what variable to be updated.
                            if (parser.getName().equals("Origin")) {
                                origin = point;
                            } else {
                                destination = point;
                            }

                            break;
                    }
                } else if (parser.getName() != null) {
                    // Switch the parser tag name.
                    switch (parser.getName()) {
                        // Parse leg tag.
                        case "Leg":
                            steps.add(new ConnectionStep(origin, destination));
                            break;

                        // Parse trip tag.
                        case "Trip":
                            result.add(new Connection(steps, cancelled));
                            steps = new ArrayList<>();
                            break;
                    }
                }

                // Continue to next tag.
                type = parser.next();
            }
        } catch (Exception e) {
            // Print stack trace.
            e.printStackTrace();
        }

        // Return the found results.
        return result;
    }

}