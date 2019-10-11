package com.vpkarise.havamana.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.vpkarise.havamana.R;
import com.vpkarise.havamana.network.HRequest;
import com.vpkarise.havamana.util.HCToast;

/**
 * Home screen activity where we search the cities
 */
public class HHomescreenActivity extends AppCompatActivity {

    private SearchView sv_city =null;
    private String tag="'HHomescreenActivity'";
    private Context context=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the view
        setContentView(R.layout.activity_homescreen);
        //initialise the system
        intialiseSystem();
        //hide the toolbar
        hideTheToolBar();
        //get view references
        getViewReferences();
        //configure the views
        configureViews();
    }


    //hide the action bar
    private void hideTheToolBar(){
        getSupportActionBar().hide(); //hide the title bar
    }

    // Get the view references
    private void getViewReferences(){
        sv_city = findViewById(R.id.et_homescreen_city);
    }

    //configure views
    private void configureViews(){

        //Onclick of search detection
        sv_city.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence queryText = sv_city.getQuery();
                if(queryText== null || queryText.equals("")){
                    //invalid search  query
                    HCToast.showToast(context,getString(R.string.hs_search_enter_valid));
                }else{
                    //search the weather of that city
                    searchForCityWeather(queryText.toString());
                }
            }
        });
    }


    public void intialiseSystem(){
        //get base context
        context = getBaseContext();
        //initialise network class
        HRequest.initialise(context);
    }


    public void searchForCityWeather(String cityname){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
