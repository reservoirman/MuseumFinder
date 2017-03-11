package com.tg2458.coms6998.homework2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;

//import android.app.LoaderManager;


public class CityPickerActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener, LoaderManager.LoaderCallbacks<Cursor>  {

    EditText textFieldEntry;
    private Button button;
    private ListView listView;
    MuseumList ml;

    private static final String TAG = "CityPickerActivity";

    GoogleApiClient client;
    SimpleCursorAdapter mAdapter;
    CityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication main = ((MainApplication)getApplication());
        ml = main.museumList;

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



        //listView = (ListView)findViewById(R.id.listView);


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

        adapter = new CityAdapter(this,
                android.R.layout.simple_list_item_1, ml.getMuseumArrayList());

        //String [] holla = {"welcome", "to", "the", "jungle"};
        //ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, holla);

        ListView list = (ListView)findViewById(R.id.listVIew);
        list.setAdapter(adapter);

        autocompleteFragment.setOnPlaceSelectedListener(new CityListener(client, main.getQueue(), ml, list, adapter));


        //listView.setAdapter(adapter);
        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getSupportLoaderManager().initLoader(0, null, this);
        /*listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TSG set intent to MapActivity
            }
        });*/
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        return null;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //mAdapter.swapCursor(data);
    }


}
