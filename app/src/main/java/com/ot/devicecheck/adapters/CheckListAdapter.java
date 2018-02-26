package com.ot.devicecheck.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ot.devicecheck.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pranjal Gupta on 31-01-2018.
 */

public class CheckListAdapter extends BaseAdapter {

    ArrayList<String> list, listClass, result;
    String name;
    CheckBox chbx;
    Context context;

    public CheckListAdapter(Context context, ArrayList<String> list, ArrayList<String> listClass) {
        this.context = context;
        this.list = list;
        this.listClass = listClass;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        // cv=convertView;
        //final String name = getItem(position);

      /*  items = Arrays.asList("Vibration", *//*"Check Version Info", "SIM Card",*//* "Proximity Sensor",
                "Flash", "Touch Sensor", "Display", "Light Sensor", "Pressure Sensor"
                , "Phone Buttons", "Speaker Test", "Gravity sensor", "Magnetic Sensor", "Headphone",
                "Gyroscope", "GPS Location", "Battery Indicator", "Accelarometer");*/


        //name = new String();
        //name = null;
        result = new ArrayList<>();
        result.clear();
        // Check if an existing view is being reused, otherwise inflate the view
        // if (convertView == null) {

        convertView = LayoutInflater.from(context).inflate(
                R.layout.adapter_autotest, parent, false);

        // Lookup view for data population
        TextView tvname = (TextView) convertView.findViewById(R.id.namefunc);
        //TextView tvdate = (TextView) convertView.findViewById(R.id.vacdate);
        // Populate the data into the template view using the data object

        tvname.setText(list.get(position));
        name = listClass.get(position);

        // /tvdate.setText(user.date);

        chbx = (CheckBox) convertView.findViewById(R.id.checkBox1);
        chbx.setChecked(false);

        chbx.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (chbx.isChecked()) {
                    Log.v("tag", "checkbox");

                    result.add(name);


                } else if (chbx.isChecked() == false) {
                    Log.v("tag", "uncheckbox");

                    result.remove(name);

                }

            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

    public void setCheckBox(Boolean value) {
        if (value) {
            chbx.setChecked(true);

        } else {
            chbx.setChecked(false);
        }
    }

    public boolean getCheckBox() {
        return chbx.isChecked();
    }

    public ArrayList<String> check() {
        return result;
    }
}
