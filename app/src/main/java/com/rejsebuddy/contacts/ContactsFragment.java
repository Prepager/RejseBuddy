package com.rejsebuddy.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rejsebuddy.R;
import com.rejsebuddy.storage.AppDatabase;
import com.rejsebuddy.storage.contact.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    /**
     * The contacts data adapter.
     */
    private ContactsAdapter adapter;

    /**
     * The swipe to refresh element.
     */
    private SwipeRefreshLayout refresher;

    /**
     * The list of app contacts.
     */
    final private List<Contact> contacts = new ArrayList<>();

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
        this.adapter = new ContactsAdapter(contacts);

        // Find recycler view and set adapter.
        RecyclerView list = view.findViewById(R.id.list);
        list.setAdapter(this.adapter);

        // Set recycler view layout manager.
        list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        // Bind swipe up refresh action and enable loading.
        this.refresher = view.findViewById(R.id.swipe_refresh);
        this.refresher.setRefreshing(true);
        this.refresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchContacts();
            }
        });

        // Populate the contacts list.
        this.fetchContacts();
    }

    /**
     * Dispatch event to fetch the contacts from storage.
     */
    private void fetchContacts() {
        new ContactsFetcher(this.adapter, this.refresher, this.contacts).start();
    }

    /**
     * Contacts data fetcher class.
     */
    class ContactsFetcher extends Thread {

        /**
         * Reference to the adapter.
         */
        final ContactsAdapter adapter;

        /**
         * Reference to the refresher.
         */
        final SwipeRefreshLayout refresher;

        /**
         * Reference to the contacts list.
         */
        final List<Contact> contacts;

        /**
         * Constructor to retrieve the contacts details.
         *
         * @param contacts The contacts list reference.
         */
        ContactsFetcher(ContactsAdapter adapter, SwipeRefreshLayout refresher, List<Contact> contacts) {
            this.adapter = adapter;
            this.refresher = refresher;
            this.contacts = contacts;
        }

        /**
         * Retrieve the contacts from the database and insert.
         */
        public void run() {
            // Clear contacts and add new from database.
            this.contacts.clear();
            this.contacts.addAll(AppDatabase.getInstance(getContext()).contacts().all());

            // Notify of change and stop refreshing.
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                    refresher.setRefreshing(false);
                }
            });
        }

    }

}
