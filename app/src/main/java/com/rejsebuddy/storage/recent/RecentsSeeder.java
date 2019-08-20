package com.rejsebuddy.storage.recent;

import com.rejsebuddy.storage.AppDatabase;
import com.rejsebuddy.storage.contact.Contact;
import com.rejsebuddy.storage.contact.ContactDao;

@SuppressWarnings("SpellCheckingInspection")
public class RecentsSeeder {

    /**
     * Seeds the database with the example contacts.
     */
    public static void seed(AppDatabase database) {

        RecentDao dao = database.recents();

        dao.insert(new Recent(
            1,
            "Anker Engelunds Vej 101A, 2800 Kgs. Lyngby",
            12523277, 55785878,
            "Lautrupvang 15, 2750 Ballerup",
            12396430, 55731197
        ));

        dao.insert(new Recent(
            2,
            "Lautrupvang 15, 2750 Ballerup",
            12396430, 55731197,
            "Anker Engelunds Vej 101A, 2800 Kgs. Lyngby",
            12523277, 55785878
        ));

        dao.insert(new Recent(
            3,
            "Anker Engelunds Vej 101A, 2800 Kgs. Lyngby",
            12523277, 55785878,
            "Rådhusbuen 1, 4000 Roskilde",
            12089197, 55633816
        ));

    }
}
