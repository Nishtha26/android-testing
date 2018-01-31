package com.ot.devicecheck;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pranjal Gupta on 31-01-2018.
 */

public class CheckListAdapter extends ArrayAdapter<String> {

    ArrayList<String> list;

    public CheckListAdapter(Context context, List<String> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        // cv=convertView;
        final String name = getItem(position);
        //name = new String();
        //name = null;
        list = new ArrayList<String>();
        list.clear();
        // Check if an existing view is being reused, otherwise inflate the view
        // if (convertView == null) {

        convertView = LayoutInflater.from(getContext()).inflate(
                R.layout.adapter_autotest, parent, false);

        // Lookup view for data population
        TextView tvname = (TextView) convertView.findViewById(R.id.namefunc);
        //TextView tvdate = (TextView) convertView.findViewById(R.id.vacdate);
        // Populate the data into the template view using the data object

        tvname.setText(name);

        // /tvdate.setText(user.date);

        final CheckBox chbx = (CheckBox) convertView.findViewById(R.id.checkBox1);
        chbx.setChecked(false);
        chbx.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (chbx.isChecked()) {
                    Log.v("tag", "checkbox");

                    list.add(name);


                } else if (chbx.isChecked() == false) {
                    Log.v("tag", "uncheckbox");

                    list.remove(name);

                }

            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

    public ArrayList<String> check() {
        return list;
    }
}
