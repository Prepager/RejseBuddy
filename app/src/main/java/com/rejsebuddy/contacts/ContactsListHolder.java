package com.rejsebuddy.contacts;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rejsebuddy.R;
import com.rejsebuddy.storage.contact.Contact;

class ContactsListHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    /**
     * The contact model id.
     */
    private int id;

    /**
     * The contact name text element.
     */
    final private TextView name;

    /**
     * The contact address text element.
     */
    final private TextView address;

    /**
     * Populates the layout element holders.
     *
     * @param view The contact row view.
     */
    ContactsListHolder(View view) {
        // Call view holder super.
        super(view);

        // Bind the view elements.
        this.name = view.findViewById(R.id.name);
        this.address = view.findViewById(R.id.address);

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
        // TODO
        System.out.println("Single tap " + getAdapterPosition());
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
        this.name.setText(contact.getName());
        this.address.setText(contact.getAddress());
    }
}
