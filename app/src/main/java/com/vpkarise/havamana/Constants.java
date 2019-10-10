package com.vpkarise.havamana;

public class Constants {
    //Open weather base urls
    // In future this shall be moved to a server
    static public class Apis{
        private String openWeatherBaseUrl="api.openweathermap.org/data/2.5/weather?q=";
        private String apiKey="&APPID=c4780c1c6745b3d81abc35974fa827c2";

        public String getGeneralWeatherByCity(String city){
            StringBuilder builder = new StringBuilder(openWeatherBaseUrl);
            builder.append(city);
            builder.append(apiKey);
            return  builder.toString();
        }
    }
}
