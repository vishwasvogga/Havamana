package com.vpkarise.havamana.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;

import com.vpkarise.havamana.HState;
import com.vpkarise.havamana.R;
import com.vpkarise.havamana.model.HWeatherModel;
import com.vpkarise.havamana.network.HDisposableManager;
import com.vpkarise.havamana.network.HRequest;
import com.vpkarise.havamana.network.HWeatherRequest;
import com.vpkarise.havamana.util.HCLog;
import com.vpkarise.havamana.util.HCToast;
import com.vpkarise.havamana.util.HProgressDialog;

import org.json.JSONObject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Home screen activity where we search the cities
 */
public class HHomescreenActivity extends AppCompatActivity {

    private SearchView sv_city = null;
    private String tag = "HHomescreenActivity";
    private Context baseContext = null;


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



    /**
     * Hide the toolbar
     */
    private void hideTheToolBar() {
        getSupportActionBar().hide(); //hide the title bar
    }

    /**
     * Get view refrences
     */
    private void getViewReferences() {
        sv_city = findViewById(R.id.et_homescreen_city);
    }

    /**
     * Configure views
     */
    private void configureViews() {

        //Onclick of search detection
        sv_city.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CharSequence queryText = sv_city.getQuery();
                if (queryText == null || queryText.equals("")) {
                    //invalid search  query
                    HCToast.showToast(baseContext, getString(R.string.hs_search_enter_valid));
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


    /**
     * Initialise system
     */
    public void intialiseSystem() {
        //get base baseContext
        baseContext = getBaseContext();
        //initialise network class
        HRequest.initialise(baseContext);
    }


    /**
     * Search city weather
     * @param cityname cityname
     */
    public void searchForCityWeather(String cityname) {
        //replace all spaces by %
        cityname = cityname.replaceAll(" ","%");
        HProgressDialog.create(getString(R.string.please_await),getString(R.string.hs_loading_weather),this);
        HWeatherRequest.getInstance().searchWeatherByCity(cityname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                HCLog.debug(tag, jsonObject.toString());
                HWeatherModel weatherModel = new HWeatherModel();
                //parse the json data to weather model
                weatherModel.parseJson(jsonObject);
                HCLog.debug(tag, weatherModel.toString());
                HProgressDialog.dismiss();


                //check the response
                //Incase of error show alert dialog
                // Incase of success call weather detail activity
                if(weatherModel.isSuccess==true){
                    //go to weather details page
                    HState.getInstance().weatherData = weatherModel;
                    Intent intent = new Intent(HHomescreenActivity.this,HWeatherDetailsScreenActivity.class);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            new ContextThemeWrapper(HHomescreenActivity.this, R.style.AlertDialogCustom));
                    //city not found
                    if(weatherModel.errorCode==404){
                       alertDialogBuilder.setMessage(R.string.not_found_city);
                    //API limit exceeded
                    }else if(weatherModel.errorCode==429){
                        alertDialogBuilder.setMessage(R.string.exceeded_limit);
                    }
                    alertDialogBuilder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //clear search string
                            sv_city.setQuery("",false);
                        }
                    });
                    alertDialogBuilder.show();
                }
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
        //destroy any existing disposable
        HDisposableManager.dispose();
        //cancel any ongoing request
        HWeatherRequest.getInstance().cancelAllRequests();
    }



}
