package com.tg2458.coms6998.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;



public class CityPickerActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    EditText textFieldEntry;
    private Button button;
    private TableLayout table;
    MuseumList ml;

    private static final String TAG = "CityPickerActivity";

    GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ml = ((MainApplication)getApplication()).museumList;

        client = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addApi(Places.PLACE_DETECTION_API)
                .addApi(Places.GEO_DATA_API)
                .build();

        setContentView(R.layout.activity_city_picker);

        textFieldEntry = (EditText)findViewById(R.id.editText);

        textFieldEntry.setText("");

        textFieldEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        table = (TableLayout)findViewById(R.id.table);



        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textFieldEntry.getText().toString().toLowerCase().contains("map"))
                {
                    ml.addMuseum(-34, 151, "Sydney Opera House", "123 White Way Sydney AUS");
                    ml.addMuseum(-33, 149, "Sydney TZG", "Territories Sydney AUS");
                    startActivity(new Intent(CityPickerActivity.this, MapsActivity.class));
                }
            }
        });


        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        /*
        autocompleteFragment.setBoundsBias(new LatLngBounds(
                new LatLng(-33.880490, 151.184363),
                new LatLng(-33.858754, 151.229596)));
        */
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();

        autocompleteFragment.setFilter(typeFilter);

        autocompleteFragment.setOnPlaceSelectedListener(new CityListener(client));
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
