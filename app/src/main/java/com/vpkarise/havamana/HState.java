package com.vpkarise.havamana;

/**
 * This class holds the application state
 */
public class HState {
    private static final HState ourInstance = new HState();

    public static HState getInstance() {
        return ourInstance;
    }

    private HState() {
    }
}
