package com.vpkarise.havamana.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import static com.vpkarise.havamana.HConstants.apiKey;

/**
 * This class has methods which will hit the server to get the data
 */
public class HRequest {
    private static HRequest ourInstance = null;
    private RequestQueue queue = null;

    /**
     * Initialise the HRequest library
     *
     * @param context Base context for request que
     * @return
     */
    public static void initialise(Context context) {
        if (ourInstance == null) {
            ourInstance = new HRequest(context);
        }
    }

    /**
     * Get the singleton instance of this class after its initialisation
     * if it was not initialised before will throw an error
     *
     * @return HRequest
     */
    public static HRequest getInstance() {
        if (ourInstance == null) {
            throw new Error("Hrequest framework is not initalised");
        }
        return ourInstance;
    }

    private HRequest(Context context) {
        queue = Volley.newRequestQueue(context);
    }


    /**
     * A method to make a json request
     *
     * @param method Type of method , ex  Request.Method.GET
     * @param url    endpoint
     */
    public void JsonRequest(int method, String url) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //  textView.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
    }


    /**
     * this method attaches api key with url
     *
     * @param url raw url
     * @return String url
     */
    private String attachApiKey(String url) {   //
        StringBuilder builder = new StringBuilder(url);
        builder.append(apiKey);
        return builder.toString();
    }
}
