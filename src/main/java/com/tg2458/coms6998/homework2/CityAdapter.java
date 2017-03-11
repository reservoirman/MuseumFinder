package com.tg2458.coms6998.homework2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LanKyoungHong on 3/11/17.
 */

public class CityAdapter extends ArrayAdapter<Museum>
{

    public CityAdapter(Context context, int resource, List<Museum> objects) {

        super(context, resource, objects);
        System.out.println("CONSTRUCTOR!");
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Museum user = getItem(position);
        System.out.println(user.latitude + " and " + user.longitude);
        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.text1);

        if (tvName == null)
        {
            System.out.println("DOH!");
        }

        else {
            // Populate the data into the template view using the data object
            tvName.setText(user.name + ": " + user.address);
            System.out.println(tvName.getText());
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
