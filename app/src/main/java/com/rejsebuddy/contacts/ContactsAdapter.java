package com.rejsebuddy.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rejsebuddy.R;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    /**
     * The list of passed contacts.
     *
     * @var List<Contact>
     */
    private List<Contact> contacts;

    /**
     * Initiates the class instance with the contacts.
     *
     * @param contacts The contacts list to be shown.
     */
    public ContactsAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Inflates and returns a new contact row instance.
     *
     * @param parent The parent element context.
     * @param type The view type of the new View.
     * @return View holder of the contact row.
     */
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        // Inflate the contact row view.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_contact, parent, false);

        // Return new view holder instance.
        return new ContactViewHolder(view);
    }

    /**
     * Populates the row once fully binded.
     *
     * @param holder The view holder.
     * @param index The index of the current view.
     */
    @Override
    public void onBindViewHolder(ContactViewHolder holder, int index) {
        holder.name.setText(contacts.get(index).getName());
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

    /**
     * Wrapper class to hold contact view elements.
     */
    public class ContactViewHolder extends RecyclerView.ViewHolder {

        /**
         * The contact name text element.
         *
         * @var TextView
         */
        public TextView name;

        /**
         * The contact address text element.
         *
         * @var TextView
         */
        public TextView address;

        /**
         * Populates the layout element holders.
         *
         * @param view The contact row view.
         */
        public ContactViewHolder(View view) {
            super(view);

            this.name = view.findViewById(R.id.name);
            this.address = view.findViewById(R.id.address);
        }

    }

}
