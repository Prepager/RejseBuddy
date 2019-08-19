package com.rejsebuddy.api.models;

import java.util.Date;

@SuppressWarnings("unused")
public class ConnectionStepPoint {

    /**
     * The name of the point.
     */
    private String name;

    /**
     * The date of the point.
     */
    private Date date;

    /**
     * Initiates the class with the data.
     *
     * @param name The name of the step point.
     * @param date The date of the step point.
     */
    public ConnectionStepPoint(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    /**
     * Returns the step point name.
     *
     * @return The step point name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the step point name.
     *
     * @param name The step point name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the step point date.
     *
     * @return The step point date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the step point date.
     *
     * @param date The step point date.
     */
    public void setDate(Date date) {
        this.date = date;
    }

}
