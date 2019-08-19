package com.rejsebuddy.api.models;

import java.util.List;

@SuppressWarnings("unused")
public class Connection {

    /**
     * The cancelled status of the connection.
     */
    private boolean cancelled;

    /**
     * The list of connection steps.
     */
    private List<ConnectionStep> steps;

    /**
     * Initiates the class with the data.
     *
     * @param steps The list of connection steps.
     * @param cancelled The connection cancelled status.
     */
    public Connection(List<ConnectionStep> steps, boolean cancelled) {
        this.steps = steps;
        this.cancelled = cancelled;
    }

    /**
     * Returns the is cancelled status.
     *
     * @return The is cancelled status.
     */
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Sets the is cancelled status.
     *
     * @param cancelled The cancelled status.
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Returns the list of connection steps.
     *
     * @return The list of connection steps.
     */
    public List<ConnectionStep> getSteps() {
        return this.steps;
    }

    /**
     * Sets the list of connection steps.
     *
     * @param steps The list of connection steps.
     */
    public void setSteps(List<ConnectionStep> steps) {
        this.steps = steps;
    }

}
