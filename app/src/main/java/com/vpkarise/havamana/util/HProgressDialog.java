package com.vpkarise.havamana.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * This class provides methods to create and dismiss progress dialogs
 */

public class HProgressDialog {
    private static ProgressDialog progressDialog = null;

    /**
     * Craete and show the progress dialog
     * @param title Tittle
     * @param msg Message
     * @param context Context
     */
    public static void create(String title,String msg, Context context){
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        progressDialog = new ProgressDialog(context, ProgressDialog.THEME_HOLO_DARK);
        progressDialog.setTitle(title);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    /**
     * Dismiss the showing progress dialog
     */
    public static void dismiss(){
        progressDialog.dismiss();
    }

}
