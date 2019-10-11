package com.vpkarise.havamana.util;

import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * This class provides general utilities
 */
public class HGeneral {

    /**
     * Close the keypad programmatically
     * @param context context
     * @param ibinder ibinder
     */
    public static void closeKeyPad(Context context, IBinder ibinder){
        InputMethodManager in = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(in != null){
            in.hideSoftInputFromWindow(ibinder, 0);
        }
    }
}
