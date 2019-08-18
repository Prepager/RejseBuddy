package com.rejsebuddy.api.tasks;

import android.content.Context;

import com.rejsebuddy.api.Consumer;
import com.rejsebuddy.api.models.Address;
import com.rejsebuddy.helpers.AsyncWrapper;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

abstract public class SearchLocationsTask<Instance> extends AsyncWrapper<String, List<Address>, Instance> {

    /**
     * Call parent super constructor.
     *
     * @param ctx The application context.
     * @param instance The current class instance.
     */
    public SearchLocationsTask(Context ctx, Instance instance) {
        super(ctx, instance);
    }

    /**
     * Fetches and returns locations for passed search.
     *
     * @param searches The search to be fetched.
     * @return The fetched locations.
     */
    @Override
    public List<Address> doInBackground(String... searches) {
        // Create new results holder list.
        List<Address> result = new ArrayList<>();

        // Attempt to fetch locations for search.
        try {
            // Request locations for search and skip root tag.
            XmlPullParser parser = new Consumer().searchLocations(searches[0]);
            parser.next();

            // Get the current tag type.
            int type = parser.getEventType();

            // Continue loop while not at document end.
            while (type != XmlPullParser.END_DOCUMENT) {
                // Check if XML starting tag.
                if (type == XmlPullParser.START_TAG) {
                    // Create and add new address with tag attributes.
                    result.add(new Address(
                        parser.getAttributeValue(null, "name"),
                        Integer.parseInt(parser.getAttributeValue(null, "x")),
                        Integer.parseInt(parser.getAttributeValue(null, "y"))
                    ));
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