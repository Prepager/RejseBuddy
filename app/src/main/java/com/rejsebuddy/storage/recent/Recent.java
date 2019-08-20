package com.rejsebuddy.storage.recent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.rejsebuddy.api.models.Address;

@SuppressWarnings("WeakerAccess")
@Entity
public class Recent {

    /**
     * The id of the contact
     */
    @PrimaryKey(autoGenerate = true)
    private int id;

    /**
     * The address of the contact.
     */
    @ColumnInfo(name = "orig_address")
    private String origAddress;

    /**
     * The address x position.
     */
    @ColumnInfo(name = "orig_addrx")
    private int origAddrx;

    /**
     * The address y position.
     */
    @ColumnInfo(name = "orig_addry")
    private int origAddry;

    /**
     * The address of the contact.
     */
    @ColumnInfo(name = "dest_address")
    private String destAddress;

    /**
     * The address x position.
     */
    @ColumnInfo(name = "dest_addrx")
    private int destAddrx;

    /**
     * The address y position.
     */
    @ColumnInfo(name = "dest_addry")
    private int destAddry;

    /**
     * Initiates the model instance.
     *
     * @param id The id of the trip.
     * @param origAddress The address of the origin.
     * @param origAddrx The origin x position.
     * @param origAddry The origin y position.
     * @param destAddress The address of the destination.
     * @param destAddrx The destination x position.
     * @param destAddry The destination y position.
     */
    public Recent(int id,
        String origAddress, int origAddrx, int origAddry,
        String destAddress, int destAddrx, int destAddry
    ) {
        this.id = id;

        this.origAddress = origAddress;
        this.origAddrx = origAddrx;
        this.origAddry = origAddry;

        this.destAddress = destAddress;
        this.destAddrx = destAddrx;
        this.destAddry = destAddry;
    }

    /**
     * Initiates the model instance without id.
     *
     * @param origAddress The origin address instance.
     * @param destAddress The destination address instance.
     */
    public Recent(Address origAddress, Address destAddress) {
        this.setOrigAddressInstance(origAddress);
        this.setDestAddressInstance(destAddress);
    }

    /**
     * Returns the recent trip id.
     *
     * @return The id of the recent trip.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the origin address.
     *
     * @return The address of the origin.
     */
    public String getOrigAddress() {
        return this.origAddress;
    }

    /**
     * Returns the origin address x position.
     *
     * @return The origin address x position.
     */
    public int getOrigAddrx() {
        return this.origAddrx;
    }

    /**
     * Returns the origin address y position.
     *
     * @return The origin address y position.
     */
    public int getOrigAddry() {
        return this.origAddry;
    }

    /**
     * Returns the destination address.
     *
     * @return The address of the destination.
     */
    public String getDestAddress() {
        return this.destAddress;
    }

    /**
     * Returns the destination address x position.
     *
     * @return The destination address x position.
     */
    public int getDestAddrx() {
        return this.destAddrx;
    }

    /**
     * Returns the destination address y position.
     *
     * @return The destination address y position.
     */
    public int getDestAddry() {
        return this.destAddry;
    }

    /**
     * Returns new origin address instance.
     */
    public Address getOrigAddressInstance() {
        return new Address(this.origAddress, this.origAddrx, this.origAddry);
    }

    /**
     * Returns new destination address instance.
     */
    public Address getDestAddressInstance() {
        return new Address(this.destAddress, this.destAddrx, this.destAddry);
    }

    /**
     * Sets the origin address instance.
     *
     * @param address The origin address instance.
     */
    public void setOrigAddressInstance(Address address) {
        this.origAddress = address.getAddress();
        this.origAddrx = address.getAddrx();
        this.origAddry = address.getAddry();
    }

    /**
     * Sets the destination address instance.
     *
     * @param address The destination address instance.
     */
    public void setDestAddressInstance(Address address) {
        this.destAddress = address.getAddress();
        this.destAddrx = address.getAddrx();
        this.destAddry = address.getAddry();
    }

}
