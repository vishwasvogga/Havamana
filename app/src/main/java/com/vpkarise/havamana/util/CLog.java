package com.vpkarise.havamana.util;

import android.util.Log;

import com.vpkarise.havamana.Config;

public class CLog {

    public void debug(String tag, String msg){
        if(Config.getInstance().LOG_LEVEL==2){
            Log.d(tag,msg);
        }
    }

    public void error(String tag, String msg){
        if(Config.getInstance().LOG_LEVEL>0){
            Log.e(tag,msg);
        }
    }
}
