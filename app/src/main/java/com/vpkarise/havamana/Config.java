package com.vpkarise.havamana;

public class Config {
    private static Config ourInstance = new Config();

    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {
    }

    public void clearInstance(){
        ourInstance = null;
    }

    public final int LOG_LEVEL=2; // 0- No logs , 1-Error , 2-Error & Debug
}
