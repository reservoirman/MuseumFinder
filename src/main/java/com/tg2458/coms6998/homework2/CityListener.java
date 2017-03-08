package com.tg2458.coms6998.homework2;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LanKyoungHong on 3/5/17.
 */

public class CityListener implements PlaceSelectionListener {

    public static final String TAG = "CityListener";
    private RequestQueue queue;
    private GoogleApiClient client;

    public CityListener(GoogleApiClient client, RequestQueue queue){
        this.client = client;
        this.queue = queue;
    }


    @Override
    public void onPlaceSelected(Place place) {
        // Get info about the selected place.
        System.out.println(TAG +  ".OnPlaceSelectedListener Place: " + place.getName());
        System.out.println(TAG +  "Place Selected: " + place.getName());
        System.out.println(TAG +  "Place Address: " + place.getAddress());
        System.out.println(TAG +  "Place Phone Number: " + place.getPhoneNumber());
        System.out.println(TAG +  "Place Coordinates: " + place.getLatLng().toString());
        System.out.println(TAG +  "Place Viewport: " + place.getViewport().toString());

        String url = "https://maps.googleapis.com/maps/api/place/radarsearch/json?location=51.503186,-0.126446&radius=5000&type=museum&key=AIzaSyAxJ2BBo0BmkgvuER58fpsGdsyTjWV9nOk";


        //get list via JSON request
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("We got a response!!!");
                System.out.println("CityListener categories: " + response.names());



                try {
                    //System.out.println("CityListener dump: " + response.toString(4));
                    JSONArray arrayOfMuseums = response.getJSONArray("results");

                    System.out.println("London has " + arrayOfMuseums.length() + " museums");

                    for (int i = 0; i < arrayOfMuseums.length(); i++)
                    {
                        System.out.println("museum id = " + ((JSONObject)arrayOfMuseums.get(i)).getString("id"));
                    }

                    while (response.has("id"))
                    {
                        String id = response.getString("id");
                        System.out.println("id = " + id);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                System.out.println("Damn there was an error: " + error.getMessage());
            }
        });
        queue.add(req);


        // Get predicted list programmatically
        AutocompleteFilter filter = new AutocompleteFilter.Builder().setTypeFilter(AutocompleteFilter.TYPE_FILTER_ESTABLISHMENT).build();

        PendingResult<AutocompletePredictionBuffer> result =
                Places.GeoDataApi.getAutocompletePredictions(client, "Museum",
                        place.getViewport(), filter);


        result.setResultCallback(
                new ResultCallback<AutocompletePredictionBuffer>() {
                    @Override
                    public void onResult(@NonNull AutocompletePredictionBuffer autocompletePredictions) {

                        System.out.println(TAG + " number of predictions: " + autocompletePredictions.getCount());
                        for (AutocompletePrediction p : autocompletePredictions)
                        {
                            System.out.println(TAG + " " + p.getFullText(null).toString());
                        }

                    }
                });



        //store it into the museum list

        //invest into
    }

    @Override
    public void onError(Status status) {
        // TODO: Handle the error.
        System.out.println(TAG + ".OnPlaceSelectedListener An error occurred: " + status);
    }
}
