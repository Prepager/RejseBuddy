package com.rejsebuddy.storage;

import android.content.Context;

import androidx.room.Room;

public class Database {

    /**
     * Holds the singleton instance.
     */
    private static AppDatabase instance;

    /**
     * Returns the single instance.
     *
     * @return The application database.
     */
    public static AppDatabase getInstance()
    {
        return Database.instance;
    }

    /**
     * Set the singleton instance.
     *
     * @param ctx The activity context.
     */
    public static void setInstance(Context ctx)
    {
        Database.instance = Room.databaseBuilder(ctx, AppDatabase.class, "rejsebuddy").build();
    }

}
