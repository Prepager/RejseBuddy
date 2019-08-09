package com.rejsebuddy.storage.contact;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    /**
     * The id of the contact
     */
    @PrimaryKey
    private int id;

    /**
     * The name of the contact.
     */
    @ColumnInfo(name = "name")
    private String name;

    /**
     * The address of the contact.
     */
    @ColumnInfo(name = "address")
    private String address;

    /**
     * Initiates the model instance.
     *
     * @param name The name of the contact
     * @param address The address of the contact
     */
    public Contact(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    /**
     * Returns the contact id.
     *
     * @return The id of the contact.
     */
    public int getId() {
        return this.id;
    }


    /**
     * Returns the contact name.
     *
     * @return The name of the contact.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the contact address.
     *
     * @return The address of the contact.
     */
    public String getAddress() {
        return this.address;
    }

}
