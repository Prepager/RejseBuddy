package com.rejsebuddy.storage.contact;

import com.rejsebuddy.storage.AppDatabase;

@SuppressWarnings("SpellCheckingInspection")
public class ContactsSeeder {

    /**
     * Seeds the database with the example contacts.
     */
    public static void seed(AppDatabase database) {

        ContactDao dao = database.contacts();

        dao.insert(new Contact(
            1,
            "Hjem",
            "Købmagergade 52A, 1150 København",
            12575711, 55681279
        ));

        dao.insert(new Contact(
            2,
            "DTU Lyngby",
            "Anker Engelunds Vej 101A, 2800 Kgs. Lyngby",
            12523277, 55785878
        ));

        dao.insert(new Contact(
            3,
            "DTU Ballerup",
            "Lautrupvang 15, 2750 Ballerup",
            12396430, 55731197
        ));

        dao.insert(new Contact(
            4,
            "Roskilde",
            "Rådhusbuen 1, 4000 Roskilde",
            12089197, 55633816
        ));

    }
}
