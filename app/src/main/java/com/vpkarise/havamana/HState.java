package com.vpkarise.havamana;

import com.vpkarise.havamana.model.HWeatherModel;

/**
 * This class holds the application state
 */
public class HState {
    private static final HState ourInstance = new HState();

    /**
     * Get the singleton instance of state
     * @return HState
     */
    public static HState getInstance() {
        return ourInstance;
    }

    private HState() {
    }

    /**
     * Current weather data
     */
    public HWeatherModel weatherData=null;
}
