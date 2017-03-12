package com.tg2458.coms6998.homework2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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



        //listView = (ListView)findViewById(R.id.listView);


        /*
        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textFieldEntry.getText().toString().toLowerCase().contains("map"))
                {
                    startActivity(new Intent(CityPickerActivity.this, MapsActivity.class));
                }
            }
        });
        */

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
                android.R.layout.simple_list_item_2, ml.getMuseumArrayList());

        //String [] holla = {"welcome", "to", "the", "jungle"};
        //ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, holla);

        listView = (ListView)findViewById(R.id.listVIew);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Museum m = ml.getMuseum(position);

                Intent intent = new Intent(CityPickerActivity.this, MapsActivity.class);
                intent.putExtra("MuseumLocation", m);

                startActivity(intent);
            }
        });

        TextView tv = (TextView)findViewById(R.id.textView);
        autocompleteFragment.setOnPlaceSelectedListener(new CityListener(client, main.getQueue(), ml, adapter, tv));


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
