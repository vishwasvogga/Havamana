package com.vpkarise.havamana.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.vpkarise.havamana.HState;
import com.vpkarise.havamana.R;
import com.vpkarise.havamana.util.HGeneral;

/**
 * This activity displays the weather details on the screen
 */
public class HWeatherDetailsScreenActivity extends AppCompatActivity {

    //city details
    private TextView tv_City;
    private TextView tv_CityLat;
    private TextView tv_CityLong;
    //Weather details
    private TextView tv_WeatherMain;
    private TextView tv_WeatherSub;
    //temparature
    private TextView tv_Temparature;
    private TextView tv_TemparatureMax;
    private TextView tv_TemparatureMin;
    private TextView tv_Humidity;
    private TextView tv_Pressure;
    //Wind
    private TextView tv_WindSpeed;
    private TextView tv_WindDir;
    //Button back
    private AppCompatButton btn_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout
        setContentView(R.layout.activity_weather_detail);
        //get view refrences
        getViewRefrences();
        //hide the toolbar
        hideTheToolBar();
        //configure the views
        configureViews();
    }

    /**
     * Hide the toolbar
     */
    private void hideTheToolBar() {
        getSupportActionBar().hide(); //hide the title bar
    }

    /**
     * Get view refrences
     */
    public void getViewRefrences(){
        //city details
        tv_City = findViewById(R.id.tv_wd_city);
        tv_CityLat= findViewById(R.id.tv_wd_lat);
        tv_CityLong= findViewById(R.id.tv_wd_long);
        //Weather details
        tv_WeatherMain= findViewById(R.id.tv_wd_main);
        tv_WeatherSub= findViewById(R.id.tv_wd_sub);
        //temparature
        tv_Temparature= findViewById(R.id.tv_wd_temp);
        tv_TemparatureMax= findViewById(R.id.tv_wd_temp_max);
        tv_TemparatureMin= findViewById(R.id.tv_wd_temp_min);
        tv_Humidity= findViewById(R.id.tv_wd_humidity);
        tv_Pressure= findViewById(R.id.tv_wd_pressure);
        //Wind
        tv_WindSpeed = findViewById(R.id.tv_wd_wind_speed);
        tv_WindDir= findViewById(R.id.tv_wd_wind_direction);
        //back button
        btn_back = findViewById(R.id.btn_wd_back);
    }

    /**
     * Configure the uI elements
     */
    public void configureViews(){
        //lets update all the UI elements by state
        HState hState = HState.getInstance();
        //city details
        tv_City.setText(hState.weatherData.city);
        tv_CityLat.setText(hState.weatherData.logitude+"");
        tv_CityLong.setText(hState.weatherData.latitude+"");
        //Weather details
        tv_WeatherMain.setText(hState.weatherData.weatherTypeMain);
        tv_WeatherSub.setText(hState.weatherData.weatherTypeSub);
        //temparature
        tv_Temparature.setText(HGeneral.setDecimalPlaces(hState.weatherData.temp-274,2)+"'C");
        tv_TemparatureMax.setText(HGeneral.setDecimalPlaces(hState.weatherData.temp_max-274,2)+"'C");
        tv_TemparatureMin.setText(HGeneral.setDecimalPlaces(hState.weatherData.temp_min-274,2)+"'C");
        tv_Humidity.setText((hState.weatherData.humidity)+"%");
        tv_Pressure.setText((hState.weatherData.pressure)+"P");
        //Wind
        tv_WindSpeed .setText((hState.weatherData.wind_speed)+"Km/H");
        tv_WindDir.setText((hState.weatherData.wind_direction)+"'");
        //back button
        btn_back = findViewById(R.id.btn_wd_back);
        //on back button press finish the activity
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
