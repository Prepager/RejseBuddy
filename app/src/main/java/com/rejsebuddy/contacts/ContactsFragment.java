package com.rejsebuddy.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rejsebuddy.R;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    /**
     * The list of app contacts.
     *
     * @var List<Contact>
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
        loadContacts();
    }

    /**
     * Populate the contacts list.
     */
    private void loadContacts() {
        // TODO: Adds example contacts to list.
        contacts.add(new Contact("Kontakt 1", "Adresse 1"));
        contacts.add(new Contact("Kontakt 2", "Adresse 2"));
        contacts.add(new Contact("Kontakt 3", "Adresse 3"));
        contacts.add(new Contact("Kontakt 4", "Adresse 4"));
        contacts.add(new Contact("Kontakt 5", "Adresse 5"));
        contacts.add(new Contact("Kontakt 6", "Adresse 6"));
        contacts.add(new Contact("Kontakt 7", "Adresse 7"));
        contacts.add(new Contact("Kontakt 8", "Adresse 8"));
        contacts.add(new Contact("Kontakt 9", "Adresse 9"));
        contacts.add(new Contact("Kontakt 10", "Adresse 10"));
    }

}
