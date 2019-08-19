package com.rejsebuddy.api;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("FieldCanBeLocal")
class Client {

    /**
     * The base url of the api interface.
     */
    private final String baseURL = "http://xmlopen.rejseplanen.dk/bin/rest.exe";

    /**
     * Fetch a passed URL address.
     *
     * @param address The address to be fetched.
     * @return The fetched input stream.
     */
    private InputStream fetch(String address) throws IOException {
        // Create new request URL address.
        URL url = new URL(this.baseURL + address);

        // Create new connection and connect.
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        // Return the connection input stream.
        return connection.getInputStream();
    }

    /**
     * Parse an incoming XML input stream.
     *
     * @param stream The http input stream.
     * @return The XML parser instance.
     */
    private XmlPullParser parse(InputStream stream) throws XmlPullParserException, IOException {
        // Create new parser and set input stream.
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(stream, null);
        parser.nextTag();

        // Return the created parser.
        return parser;
    }

    /**
     * Fetch and parse the requested URL address.
     *
     * @param address The URL address to be parsed.
     * @return The XML parser instance.
     */
    XmlPullParser request(String address) throws XmlPullParserException, IOException {
        return this.parse(this.fetch(address));
    }

}
