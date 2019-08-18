package com.rejsebuddy.api.models;

import java.io.Serializable;

public class Address implements Serializable {

    /**
     * The address name.
     */
    private String address;

    /**
     * The address x position.
     */
    private int addrx;

    /**
     * The address y position.
     */
    private int addry;

    /**
     * Constructor to save the details.
     *
     * @param address The address name.
     * @param addrx The address x position.
     * @param addry The address y position.
     */
    public Address(String address, int addrx, int addry) {
        this.address = address;
        this.addrx = addrx;
        this.addry = addry;
    }

    /**
     * Returns the address name.
     *
     * @return The address name.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Saves the address name.
     *
     * @param address The address name.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the address x position.
     *
     * @return The address x position.
     */
    public int getAddrx() {
        return addrx;
    }

    /**
     * Saves the address x position.
     *
     * @param addrx The address x position.
     */
    public void setAddrx(int addrx) {
        this.addrx = addrx;
    }

    /**
     * Returns the address y position.
     *
     * @return The address y position.
     */
    public int getAddry() {
        return addry;
    }

    /**
     * Saves the address y position.
     *
     * @param addry The address y position.
     */
    public void setAddry(int addry) {
        this.addry = addry;
    }

    /**
     * Returns the address preview.
     *
     * @return The address preview.
     */
    @Override
    public String toString() {
        return this.getAddress();
    }
}
