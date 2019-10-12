package com.vpkarise.havamana.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class holds all the Weather details
 */
public class HWeatherModel {
    public boolean isSuccess = false;
    public int errorCode = 404;
    //basic weather
    public String weatherTypeMain = "";
    public String weatherTypeSub = "";
    //place
    public String city = "";
    public double logitude=0.0;
    public double latitude=0.0;
    //mail weather params
    public double temp= 0.0;
    public double pressure= 0.0;
    public double humidity= 0.0;
    public double temp_min= 0.0;
    public double temp_max= 0.0;
    //wind
    public double wind_speed = 0;
    public double wind_direction = 0;


    /**
     * Parse the json object to this model object
     * @param object jsonObject containin =g weather details
     */
    public void parseJson(JSONObject object){
        try{
            isSuccess = object.getBoolean("success");
            errorCode = object.getInt("errorcode");
            //basic weather
            weatherTypeMain =object.getJSONArray("weather").getJSONObject(0).getString("main");
            weatherTypeSub =  object.getJSONArray("weather").getJSONObject(0).getString("description");
            //place
            city = object.getString("name");
            logitude=object.getJSONObject("coord").getDouble("lon");
            latitude=object.getJSONObject("coord").getDouble("lat");
            //mail weather params
            temp= object.getJSONObject("main").getDouble("temp");
            pressure= object.getJSONObject("main").getDouble("pressure");
            humidity= object.getJSONObject("main").getDouble("humidity");
            temp_min= object.getJSONObject("main").getDouble("temp_min");
            temp_max= object.getJSONObject("main").getDouble("temp_max");
            //wind
            wind_speed = object.getJSONObject("wind").getDouble("speed");
            wind_direction = object.getJSONObject("wind").getDouble("deg");
        }catch (JSONException e){
            isSuccess= false;
        }
    }

    @Override
    /**
     * Display object as string
     */
    public String toString() {
        return "HWeatherModel{" +
                "isSuccess=" + isSuccess +
                ", errorCode=" + errorCode +
                ", weatherTypeMain='" + weatherTypeMain + '\'' +
                ", weatherTypeSub='" + weatherTypeSub + '\'' +
                ", city='" + city + '\'' +
                ", logitude=" + logitude +
                ", latitude=" + latitude +
                ", temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", wind_speed=" + wind_speed +
                ", wind_direction=" + wind_direction +
                '}';
    }
}
