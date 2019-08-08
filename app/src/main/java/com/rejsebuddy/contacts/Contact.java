package com.rejsebuddy.contacts;

public class Contact {

    /**
     * The name of the contact.
     *
     * @var String
     */
    private String name;

    /**
     * The address of the contact.
     *
     * @var String
     */
    private String address;

    /**
     * Initiates the model instance.
     *
     * @param name The name of the contact
     * @param address The address of the contact
     */
    public Contact(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * Returns the contact name.
     *
     * @return The name of the contact.
     */
    public String getName() {
        return this.name;
    }

}
