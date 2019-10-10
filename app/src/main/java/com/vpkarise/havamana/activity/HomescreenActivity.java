package com.vpkarise.havamana.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.vpkarise.havamana.Constants;
import com.vpkarise.havamana.R;
import com.vpkarise.havamana.util.CLog;
import com.vpkarise.havamana.util.General;

/**
 * Home screen activity where we search the cities
 */
public class HomescreenActivity extends AppCompatActivity {

    EditText et_city=null;
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
        et_city = findViewById(R.id.et_homescreen_city);
    }

    //configure views
    private void configureViews(){
        //edit text (Search city)
        //Onclick press of search in edit text keypad, start searching for the weather of that particular city
        et_city.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if  (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    //close the keypad and clear the focus
                    et_city.clearFocus();
                    CLog.debug(tag,"Search key pressed"+url);
                    General.closeKeyPad(HomescreenActivity.this,et_city.getWindowToken());
                    return false;
                }
                return true;
            }
        });
    }






}
