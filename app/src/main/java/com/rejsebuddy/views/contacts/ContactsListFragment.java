package com.rejsebuddy.views.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rejsebuddy.R;
import com.rejsebuddy.storage.contact.Contact;
import com.rejsebuddy.storage.contact.tasks.GetContactsTask;

import java.util.ArrayList;
import java.util.List;

public class ContactsListFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    /**
     * The contacts data adapter.
     */
    private ContactsListAdapter adapter;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle state) {
        return inflater.inflate(R.layout.fragment_contacts_list, container, false);
    }

    /**
     * Connects the contacts adapter to the view.
     *
     * @param view The contacts row view.
     * @param state The previous view state.
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle state) {
        // Call super class.
        super.onViewCreated(view, state);

        // Create new contacts adapter.
        this.adapter = new ContactsListAdapter(contacts);

        // Find recycler view and set adapter.
        RecyclerView list = view.findViewById(R.id.contacts_list);
        list.setAdapter(this.adapter);

        // Set recycler view layout manager.
        list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        // Bind swipe up refresh action and enable loading.
        this.refresher = view.findViewById(R.id.contacts_swipe_refresh);
        this.refresher.setOnRefreshListener(this);
        this.refresher.setRefreshing(true);

        // Bind add contacts floating action button.
        FloatingActionButton add = view.findViewById(R.id.add_contacts_fab);
        add.setOnClickListener(this);

        // Populate the contacts list.
        this.onRefresh();
    }

    /**
     * Populate the contacts list on refresh.
     */
    public void onRefresh() {
        new GetContacts(getContext(), this).execute();
    }

    /**
     * Open contacts editor activity on floating click.
     *
     * @param view The current view.
     */
    public void onClick(View view) {
        startActivity(new Intent(getContext(), ContactsEditorActivity.class));
    }

    /**
     * Refresh the data on fragment resume.
     */
    @Override
    public void onResume() {
        super.onResume();
        this.onRefresh();
    }

    /**
     * Fetch all contacts from database.
     */
    private static class GetContacts extends GetContactsTask<ContactsListFragment> {

        /**
         * Call parent super constructor.
         *
         * @param ctx The application context.
         * @param instance The current class instance.
         */
        GetContacts(Context ctx, ContactsListFragment instance) {
            super(ctx, instance);
        }

        /**
         * Save result and disable refreshing state.
         *
         * @param result The query list result.
         */
        protected void onPostExecute(List<Contact> result) {
            // Get the parent instance.
            ContactsListFragment instance = this.getInstance();

            // Clear list and add new.
            instance.contacts.clear();
            instance.contacts.addAll(result);

            // Notify of change and stop loading.
            instance.adapter.notifyDataSetChanged();
            instance.refresher.setRefreshing(false);
        }

    }

}
