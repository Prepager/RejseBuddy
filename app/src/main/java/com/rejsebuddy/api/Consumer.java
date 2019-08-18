package com.rejsebuddy.api;

import com.rejsebuddy.api.models.Address;

import org.xmlpull.v1.XmlPullParser;

import java.net.URLEncoder;

public class Consumer extends Client {

    /**
     * Requests location endpoint for input.
     *
     * @param input The query input string.
     * @return The XML parser instance.
     */
    public XmlPullParser searchLocations(String input) throws Exception {
        return this.request("/location?input=" + URLEncoder.encode(input, "UTF-8"));
    }

    /**
     * Requests connections between points.
     *
     * @param from The from address.
     * @param to The to address.
     * @return The XML parser instance.
     */
    public XmlPullParser getConnections(Address from, Address to) throws Exception {
        // Create params for "from" address.
        String fromParams = "originCoordName={name}&originCoordX={xPos}&originCoordY={yPos}"
            .replace("{name}", URLEncoder.encode(from.getAddress(), "UTF-8"))
            .replace("{xPos}", URLEncoder.encode(Integer.toString(from.getAddrx()), "UTF-8"))
            .replace("{yPos}", URLEncoder.encode(Integer.toString(from.getAddry()), "UTF-8"));

        // Create params for "to" address.
        String toParams = "destCoordName={name}&destCoordX={xPos}&destCoordY={yPos}"
                .replace("{name}", URLEncoder.encode(to.getAddress(), "UTF-8"))
                .replace("{xPos}", URLEncoder.encode(Integer.toString(to.getAddrx()), "UTF-8"))
                .replace("{yPos}", URLEncoder.encode(Integer.toString(to.getAddry()), "UTF-8"));

        // Request the generated address.
        return this.request("/trip?" + fromParams + "&" + toParams);
    }

}
