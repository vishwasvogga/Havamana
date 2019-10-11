package com.vpkarise.havamana.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.vpkarise.havamana.R;
import com.vpkarise.havamana.util.CLog;
import com.vpkarise.havamana.util.General;

/**
 * Home screen activity where we search the cities
 */
public class HomescreenActivity extends AppCompatActivity {

    SearchView sv_city =null;
    String tag="'HomescreenActivity'";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the view
        setContentView(R.layout.activity_homescreen);
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

                }else{
                    //search the weather of that city

                }
            }
        });
    }






}
