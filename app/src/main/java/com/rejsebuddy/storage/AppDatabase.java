package com.rejsebuddy.storage;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.rejsebuddy.storage.contact.Contact;
import com.rejsebuddy.storage.contact.ContactDao;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ContactDao contacts();

}
