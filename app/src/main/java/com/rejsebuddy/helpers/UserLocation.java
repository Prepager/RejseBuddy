package com.rejsebuddy.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.rejsebuddy.R;
import com.rejsebuddy.api.models.Address;

public class UserLocation {

    /**
     * Returns the last know location or requests permission.
     */
    public Address request(Context ctx, Activity activity) throws Exception {
        // Prepare permission related constants.
        int granted = PackageManager.PERMISSION_GRANTED;
        String permission = Manifest.permission.ACCESS_FINE_LOCATION;

        // Check if permission is not already granted.
        if (ContextCompat.checkSelfPermission(ctx, permission) != granted) {
            // Attempt to request location permission from user.
            ActivityCompat.requestPermissions(activity, new String[]{
                permission,
            }, 0);

            // Throw new exception.
            throw new Exception("Location permission not already granted.");
        }

        // Create location manager and get last known location.
        LocationManager manager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        // Convert positions to WGS84 format.
        int x = (int) (location.getLatitude() * 1000000);
        int y = (int) (location.getLongitude() * 1000000);

        // Return new point with x and y.
        return new Address(activity.getString(R.string.my_location), x, y);
    }

}
