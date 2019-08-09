package com.rejsebuddy.storage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rejsebuddy.storage.contact.Contact;
import com.rejsebuddy.storage.contact.ContactDao;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * TODO
     */
    private static AppDatabase instance;

    /**
     * TODO
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

        // Create example contacts.
        this.contacts().insert(new Contact(1, "Hjem", "Købmagergade 52A, 1150 København"));
        this.contacts().insert(new Contact(2, "DTU Lyngby", "Anker Engelunds Vej 1, 2800 Kgs. Lyngby"));
        this.contacts().insert(new Contact(3, "DTU Ballerup", "Lautrupvang 15, 2750 Ballerup"));
        this.contacts().insert(new Contact(4, "Roskilde", "Rådhusbuen 1, 4000 Roskilde"));
    }

    /**
     * TODO
     */
    public static AppDatabase getInstance(Context ctx) {
        // TODO
        if (AppDatabase.instance == null) {
            // TODO
            AppDatabase.instance = Room.databaseBuilder(ctx, AppDatabase.class,"rejsebuddy")
                .enableMultiInstanceInvalidation()
                .build();
        }

        //
        return AppDatabase.instance;
    }

}
