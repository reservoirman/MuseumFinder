package com.tg2458.coms6998.homework2;

import android.support.annotation.NonNull;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

/**
 * Created by LanKyoungHong on 3/5/17.
 */

public class CityListener implements PlaceSelectionListener {

    public static final String TAG = "CityListener";

    private GoogleApiClient client;

    public CityListener(GoogleApiClient client){
        this.client = client;
    }


    @Override
    public void onPlaceSelected(Place place) {
        // Get info about the selected place.
        System.out.println(TAG +  ".OnPlaceSelectedListener Place: " + place.getName());
        System.out.println(TAG +  "Place Selected: " + place.getName());
        System.out.println(TAG +  "Place Address: " + place.getAddress());
        System.out.println(TAG +  "Place Phone Number: " + place.getPhoneNumber());
        System.out.println(TAG +  "Place Coordinates: " + place.getLatLng().toString());

        // Get predicted list programmatically
        AutocompleteFilter filter = new AutocompleteFilter.Builder().setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS).build();

        PendingResult<AutocompletePredictionBuffer> result =
                Places.GeoDataApi.getAutocompletePredictions(client, place.getName().toString(),
                        place.getViewport(), filter);

        AutocompletePredictionBuffer results = result.setResultCallback(
                new ResultCallback<AutocompletePredictionBuffer>() {
                    @Override
                    public void onResult(@NonNull AutocompletePredictionBuffer autocompletePredictions) {

                    }
                });
        System.out.println(TAG + " " + results.getCount());


        //store it into the museum list

        //invest into
    }

    @Override
    public void onError(Status status) {
        // TODO: Handle the error.
        System.out.println(TAG + ".OnPlaceSelectedListener An error occurred: " + status);
    }
}
