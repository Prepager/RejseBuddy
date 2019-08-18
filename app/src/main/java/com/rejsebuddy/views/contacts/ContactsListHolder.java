package com.rejsebuddy.views.contacts;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rejsebuddy.R;
import com.rejsebuddy.api.models.Address;
import com.rejsebuddy.storage.contact.Contact;
import com.rejsebuddy.views.connections.ConnectionsListActivity;

class ContactsListHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    /**
     * The contact model id.
     */
    private int id;

    /**
     * The contact addresse.
     */
    private Address address;

    /**
     * The contact name text element.
     */
    final private TextView nameText;

    /**
     * The contact address text element.
     */
    final private TextView addressText;

    /**
     * Populates the layout element holders.
     *
     * @param view The contact row view.
     */
    ContactsListHolder(View view) {
        // Call view holder super.
        super(view);

        // Bind the view elements.
        this.nameText = view.findViewById(R.id.name);
        this.addressText = view.findViewById(R.id.address);

        // Bind single and long click listeners.
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
    }

    /**
     * Bind the row single click event.
     *
     * @param view The current view.
     */
    public void onClick(View view) {
        // Get context from view.
        Context ctx = view.getContext();

        // Start activity with to address.
        Intent intent = new Intent(ctx, ConnectionsListActivity.class);
        intent.putExtra("TO_ADDRESS", this.address);
        ctx.startActivity(intent);
    }

    /**
     * Bind the row long click event.
     *
     * @param view The current view.
     */
    public boolean onLongClick(View view) {
        // Get context from view.
        Context ctx = view.getContext();

        // Start new intent and include contact id.
        Intent intent = new Intent(ctx, ContactsEditorActivity.class);
        intent.putExtra("CONTACT_ID", id);
        ctx.startActivity(intent);

        // Mark action as handled.
        return true;
    }

    /**
     * Sets the contact details on the view.
     *
     * @param contact The list row contact.
     */
    public void setContact(Contact contact) {
        this.id = contact.getId();
        this.address = contact.getAddressIntance();

        this.nameText.setText(contact.getName());
        this.addressText.setText(contact.getAddress());
    }
}
