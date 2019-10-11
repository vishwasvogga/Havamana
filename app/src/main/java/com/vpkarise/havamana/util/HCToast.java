package com.vpkarise.havamana.util;

import android.content.Context;
import android.widget.Toast;

/**
 * This class provides custom toast methods
 */
public class HCToast {

    /**
     * Show toast with default duration (Long)
     * @param context context
     * @param text text to toast
     */
    public static void showToast(Context context, String text){
        Toast toast = Toast.makeText(context,text,Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Show toast with custom duration
     * @param context context
     * @param text text to toast
     * @param duration duration of the tosat
     */
    public static void showToast(Context context, String text,int duration){
        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }

}

