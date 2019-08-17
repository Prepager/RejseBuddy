package com.rejsebuddy.api;

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

}
