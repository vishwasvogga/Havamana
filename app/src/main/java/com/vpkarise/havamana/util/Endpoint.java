package com.vpkarise.havamana.util;

import static com.vpkarise.havamana.Constants.apiKey;
import static com.vpkarise.havamana.Constants.openWeatherBaseUrl;

/**
 * This class has methods which will hit the server to get the data
 */
public class Endpoint {
    private static final Endpoint ourInstance = new Endpoint();

    /**
     * Get the singleton instance of this class
     * @return
     */
    public static Endpoint getInstance() {
        return ourInstance;
    }

    private Endpoint() {
    }


    /**
     * this method forms the url with base url and app key
     * @param city city name
     * @return String url
     */
    public String getGeneralWeatherByCity(String city){
        StringBuilder builder = new StringBuilder(openWeatherBaseUrl);
        builder.append(city);
        builder.append(apiKey);
        return  builder.toString();
    }
}
