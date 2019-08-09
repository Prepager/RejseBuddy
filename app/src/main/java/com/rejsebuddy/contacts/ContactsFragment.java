package com.rejsebuddy.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rejsebuddy.R;
import com.rejsebuddy.storage.AppDatabase;
import com.rejsebuddy.storage.contact.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    /**
     * The list of app contacts.
     */
    private List<Contact> contacts = new ArrayList<>();

    /**
     * Inflates the contacts fragment.
     *
     * @param inflater The layout inflater instance.
     * @param container The group view container.
     * @param state The saved state of the view.
     * @return The inflated fragment view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    /**
     * Connects the contacts adapter to the view.
     *
     * @param view The contacts row view.
     * @param state The previous view state.
     */
    @Override
    public void onViewCreated(View view, Bundle state) {
        // Call super class.
        super.onViewCreated(view, state);

        // Create new contacts adapter.
        ContactsAdapter adapter = new ContactsAdapter(contacts);

        // Find recycler view and set adapter.
        RecyclerView list = view.findViewById(R.id.list);
        list.setAdapter(adapter);

        // Set recycler view layout manager.
        list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        // Populate the contacts list.
        new ContactsFetcher(this.contacts).start();
    }

    /**
     * Contacts data fetcher class.
     */
    class ContactsFetcher extends Thread {

        /**
         * Reference to the contacts list.
         */
        List<Contact> contacts;

        /**
         * Constructor to retrieve the contacts list.
         *
         * @param contacts The contacts list reference.
         */
        public ContactsFetcher(List<Contact> contacts) {
            this.contacts = contacts;
        }

        /**
         * Retrieve the contacts from the database and insert.
         */
        public void run() {
            this.contacts.addAll(AppDatabase.getInstance(getContext()).contacts().all());
        }

    }

}
