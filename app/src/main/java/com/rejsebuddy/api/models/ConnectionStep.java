package com.rejsebuddy.api.models;

public class ConnectionStep {

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
     * @param origin The connection step destination.
     */
    public ConnectionStep(ConnectionStepPoint origin, ConnectionStepPoint destination) {
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * Returns the step origin.
     *
     * @return The step origin.
     */
    public ConnectionStepPoint getOrigin() {
        return origin;
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
        return destination;
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
