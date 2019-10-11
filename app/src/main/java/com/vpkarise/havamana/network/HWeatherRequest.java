package com.vpkarise.havamana.network;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.Request;
import com.vpkarise.havamana.HConstants;

/**
 * This class provides all the api calls which returns the weather data
 */
public class HWeatherRequest {
    private static final HWeatherRequest ourInstance = new HWeatherRequest();

    /**
     * Get the singleton instance of this class
     *
     * @return HWeatherRequest
     */
    public static HWeatherRequest getInstance() {
        return ourInstance;
    }

    private HWeatherRequest() {
    }

    public void searchWeatherByCity(String cityname) {
        StringBuilder builder = new StringBuilder(HConstants.openWeatherBaseUrl);
        builder.append(HConstants.getWeatherByCity);
        builder.append(cityname);
        HRequest.getInstance().JsonRequest(Request.Method.GET, builder.toString());
    }
}
