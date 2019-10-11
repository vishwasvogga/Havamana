package com.vpkarise.havamana.network;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.Request;
import com.vpkarise.havamana.HConstants;

import org.json.JSONObject;

import io.reactivex.Single;

import static com.vpkarise.havamana.HConfig.WEATHER_API_TAG;
import static com.vpkarise.havamana.HConstants.apiKey;

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

    /**
     * Get the weather by passing the city name
     * @param cityname cityname
     * @return Single<JSONObject>
     */
    public Single<JSONObject> searchWeatherByCity(String cityname) {
        StringBuilder builder = new StringBuilder(HConstants.openWeatherBaseUrl);
        builder.append(HConstants.getWeatherByCity);
        builder.append(cityname);
        return HRequest.getInstance().JsonRequest(Request.Method.GET, attachApiKey(builder.toString()),WEATHER_API_TAG);
    }


    /**
     * Cancel all the requests related to weather api
     */
    public void cancelAllRequests(){
        HRequest.getInstance().cancelAllRequests(WEATHER_API_TAG);
    }


    /**
     * this method attaches api key with url
     *
     * @param url raw url
     * @return String url
     */
    private String attachApiKey(String url) {   //
        StringBuilder builder = new StringBuilder(url);
        builder.append(apiKey);
        return builder.toString();
    }
}
