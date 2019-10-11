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
import com.vpkarise.havamana.network.HDisposableManager;
import com.vpkarise.havamana.network.HRequest;
import com.vpkarise.havamana.network.HWeatherRequest;
import com.vpkarise.havamana.util.HCLog;
import com.vpkarise.havamana.util.HCToast;

import org.json.JSONObject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Home screen activity where we search the cities
 */
public class HHomescreenActivity extends AppCompatActivity {

    private SearchView sv_city = null;
    private String tag = "HHomescreenActivity";
    private Context context = null;


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
    private void hideTheToolBar() {
        getSupportActionBar().hide(); //hide the title bar
    }

    // Get the view references
    private void getViewReferences() {
        sv_city = findViewById(R.id.et_homescreen_city);
    }

    //configure views
    private void configureViews() {

        //Onclick of search detection
        sv_city.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CharSequence queryText = sv_city.getQuery();
                if (queryText == null || queryText.equals("")) {
                    //invalid search  query
                    HCToast.showToast(context, getString(R.string.hs_search_enter_valid));
                } else {
                    //search the weather of that city
                    searchForCityWeather(queryText.toString());
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    public void intialiseSystem() {
        //get base context
        context = getBaseContext();
        //initialise network class
        HRequest.initialise(context);
    }


    public void searchForCityWeather(String cityname) {
        HWeatherRequest.getInstance().searchWeatherByCity(cityname).subscribe(new SingleObserver<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                HCLog.debug(tag, jsonObject.toString());
            }

            @Override
            public void onError(Throwable e) {
                //Not used
                // we handle error in on success callback but with a error code
            }

            @Override
            public void onSubscribe(Disposable d) {
                HDisposableManager.add(d);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HDisposableManager.dispose();
        HWeatherRequest.getInstance().cancelAllRequests();
    }
}
