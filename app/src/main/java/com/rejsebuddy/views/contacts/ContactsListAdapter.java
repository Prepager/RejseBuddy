package com.rejsebuddy.views.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rejsebuddy.R;
import com.rejsebuddy.storage.contact.Contact;

import java.util.List;

class ContactsListAdapter extends RecyclerView.Adapter<ContactsListHolder> {

    /**
     * The list of passed contacts.
     */
    final private List<Contact> contacts;

    /**
     * Initiates the class instance with the contacts.
     *
     * @param contacts The contacts list to be shown.
     */
    public ContactsListAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Inflates and returns a new contact row instance.
     *
     * @param parent The parent element context.
     * @param type The view type of the new View.
     * @return View holder of the contact row.
     */
    @NonNull
    @Override
    public ContactsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        // Inflate the contact row view.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_contact, parent, false);

        // Return new view holder instance.
        return new ContactsListHolder(view);
    }

    /**
     * Populates the row once fully bound.
     *
     * @param holder The view holder.
     * @param index The index of the current view.
     */
    @Override
    public void onBindViewHolder(@NonNull ContactsListHolder holder, int index) {
        holder.setContact(contacts.get(index));
    }

    /**
     * Returns the amount of list items.
     *
     * @return The amount of items.
     */
    @Override
    public int getItemCount() {
        return contacts.size();
    }

}
