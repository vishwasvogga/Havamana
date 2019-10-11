package com.vpkarise.havamana.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vpkarise.havamana.util.HCLog;

import org.json.JSONObject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;

import static com.vpkarise.havamana.HConstants.apiKey;

/**
 * This class has methods which will hit the server to get the data
 */
public class HRequest {
    private static HRequest ourInstance = null;
    private RequestQueue queue = null;

    private HRequest(Context context) {
        queue = Volley.newRequestQueue(context);
    }

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

    /**
     * A method to make a json request
     *
     * @param method Type of method , ex  Request.Method.GET
     * @param url    endpoint
     */
    public Single<JSONObject> JsonRequest(final int method, final String url,final String tag) {
        /**
         * Create the single observer to return result or error
         */
        Single<JSONObject> singleObserver = Single.create(new SingleOnSubscribe<JSONObject>() {
            @Override
            public void subscribe(final SingleEmitter<JSONObject> emitter)  {
                /**
                 * Make the json request and pass the event in Single emitter
                 * We pass the error or success event on the on success call back it self but with a
                 * custom json data.
                 */
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (method, url, null, new Response.Listener<JSONObject>() {

                            /**
                             * On success responce
                             * @param response JSONObject
                             */
                            @Override
                            public void onResponse(JSONObject response) {
                                emitter.onSuccess(getCustomResponse(response,true,200));
                            }
                        }, new Response.ErrorListener() {

                            /**
                             * On error responce
                             * @param error VolleyError
                             */
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                int errorCode = 404;
                                if(error != null && error.networkResponse!=null){
                                   errorCode = error.networkResponse.statusCode;
                                }
                                emitter.onSuccess(getCustomResponse(null,false,errorCode));
                            }
                        });
                jsonObjectRequest.setTag(tag);
                queue.add(jsonObjectRequest);
            }
        });
        return singleObserver;
    }

    /**
     * This method is called to cancel all the requests
     * @param tag - tag of the requests
     */
    public void cancelAllRequests(String tag){
        if(queue!=null){
            queue.cancelAll(tag);
        }
    }



    /**
     * Build Custom json object return data
     * @param jsonObject json object data || null
     * @param isSuccess true or false
     * @param errorCode network code 200 || otherwise
     * @return JSONObject
     */
    private JSONObject getCustomResponse(JSONObject jsonObject,boolean isSuccess,int errorCode){
        if(jsonObject==null){
            jsonObject = new JSONObject();
        }
        try{
            jsonObject.put("success",isSuccess);
            if(isSuccess==false){
                jsonObject.put("errorcode",errorCode);
            }
            return jsonObject;
        }catch (Exception e){
            return new JSONObject();
        }
    }
}
