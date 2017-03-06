package com.tg2458.coms6998.homework2;

//import android.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Welcome");
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        android.app.ActionBar ab = getActionBar();


        if (ab != null) {
            System.out.println("ACTION BAR OBTAINED!");
            ab.setDisplayHomeAsUpEnabled(true);
        }
        else {
            System.out.println("No action bar obtained!");
        }
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        MuseumList ml = ((MainApplication)getApplication()).museumList;
        // Add a marker in Sydney and move the camera
        for (int i = 0; i < ml.count(); i++)
        {
            Museum m = ml.getMuseum(i);
            LatLng latlng = new LatLng(m.latitude, m.longitude);
            mMap.addMarker(new MarkerOptions().position(latlng).title(m.name + ": " + m.address));
        }
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(ml.avgLatitude(), ml.avgLongitude()), 8));

        System.out.println("We made it all the way here!");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:

                NavUtils.navigateUpFromSameTask(this);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("THE REQUEST CODE IS:  " + requestCode);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                System.out.println("MapsActivity.onActivityResult: Place: " + place.getName());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                System.out.println("MapsActivity.onActivityResult: " + status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

}
