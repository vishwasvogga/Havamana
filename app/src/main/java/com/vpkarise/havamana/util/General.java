package com.vpkarise.havamana.util;

import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

public class General {

    public static void closeKeyPad(Context context, IBinder ibinder){
        InputMethodManager in = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(ibinder, 0);
    }
}
