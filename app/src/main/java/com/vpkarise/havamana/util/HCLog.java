package com.vpkarise.havamana.util;

import android.util.Log;

import com.vpkarise.havamana.HConfig;

/**
 * This class provides custom logging methods
 */
public class HCLog {

    /**
     * Log debug messages
     * @param tag Tag of the log
     * @param msg Message of the log
     */
    public static void debug(String tag, String msg){
        if(HConfig.LOG_LEVEL==2){
            Log.d(tag,msg);
        }
    }
    /**
     * Log error messages
     * @param tag Tag of the log
     * @param msg Message of the log
     */
    public static void error(String tag, String msg){
        if(HConfig.LOG_LEVEL>0){
            Log.e(tag,msg);
        }
    }
}
