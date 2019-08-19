package com.rejsebuddy.api.models;

import java.io.Serializable;

@SuppressWarnings("unused")
public class ConnectionStep implements Serializable {

    /**
     * The step name.
     */
    private String name;

    /**
     * The step notes.
     */
    private String notes;

    /**
     * The origin connection step point.
     */
    private ConnectionStepPoint origin;

    /**
     * The destination connection step point.
     */
    private ConnectionStepPoint destination;

    /**
     * Initiates the class with the data.
     *
     * @param origin The connection step origin.
     * @param destination The connection step destination.
     */
    public ConnectionStep(String name, String notes, ConnectionStepPoint origin, ConnectionStepPoint destination) {
        this.name = name;
        this.notes = notes;
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * Returns the step name.
     *
     * @return The step name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the step name.
     *
     * @param name The step name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the step notes.
     *
     * @return The step notes.
     */
    public String getNotes() {
        return this.notes;
    }

    /**
     * Sets the step notes.
     *
     * @param notes The step notes.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Returns the step origin.
     *
     * @return The step origin.
     */
    public ConnectionStepPoint getOrigin() {
        return this.origin;
    }

    /**
     * Sets the step origin.
     *
     * @param origin The step origin.
     */
    public void setOrigin(ConnectionStepPoint origin) {
        this.origin = origin;
    }

    /**
     * Returns the step destination.
     *
     * @return The step destination.
     */
    public ConnectionStepPoint getDestination() {
        return this.destination;
    }

    /**
     * Sets the step destination.
     *
     * @param destination The step destination.
     */
    public void setDestination(ConnectionStepPoint destination) {
        this.destination = destination;
    }

}
