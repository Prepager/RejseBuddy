package com.rejsebuddy.storage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rejsebuddy.storage.contact.Contact;
import com.rejsebuddy.storage.contact.ContactDao;
import com.rejsebuddy.storage.contact.ContactsSeeder;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Holds the singleton instance.
     */
    private static AppDatabase instance;

    /**
     * Makes the contact data available.
     */
    public abstract ContactDao contacts();

    /**
     * Resets the entire database.
     */
    public void reset() {
        this.contacts().truncate();
    }

    /**
     * Populate the database with test data.
     */
    public void populate() {
        // Start by resetting db.
        this.reset();

        // Seed database example data.
        ContactsSeeder.seed(this);
    }

    /**
     * Creates and returns a singleton instance.
     *
     * @param ctx The current application context.
     * @return The singleton instance.
     */
    public static AppDatabase getInstance(Context ctx) {
        // Check if instance is not already set.
        if (AppDatabase.instance == null) {
            // Create new database instance.
            AppDatabase.instance = Room.databaseBuilder(ctx, AppDatabase.class,"rejsebuddy")
                .enableMultiInstanceInvalidation()
                .build();
        }

        // Return the singleton instance.
        return AppDatabase.instance;
    }

}
