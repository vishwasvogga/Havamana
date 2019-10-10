package com.vpkarise.havamana;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Home screen activity where we search the cities
 */
public class HomescreenActivity extends AppCompatActivity {

    EditText et_city=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        hideTheToolBar();
        getViewReferences();
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
                    Log.d("tag",String.valueOf(actionId));
                    et_city.clearFocus();
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(et_city.getWindowToken(), 0);
                    return false;
                }
                return true;
            }
        });
    }






}
