package com.tg2458.coms6998.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;


public class CityPickerActivity extends AppCompatActivity {

    EditText textFieldEntry;
    private Button button;
    private TableLayout table;
    MuseumList ml;

    private static final String TAG = "CityPickerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ml = ((MainApplication)getApplication()).museumList;

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
                    startActivity(new Intent(CityPickerActivity.this, MapsActivity.class));
                }
            }
        });

        /*
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });*/




    }


}
