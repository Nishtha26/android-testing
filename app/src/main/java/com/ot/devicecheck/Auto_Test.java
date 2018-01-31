package com.ot.devicecheck;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pranjal Gupta on 29-01-2018.
 */

public class Auto_Test extends AppCompatActivity {

    List<String> items;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatedtest);

        items = Arrays.asList("Vibration Test", /*"Check Version Info", "SIM Card",*/ "Proximity Sensor",
                "Flash Light", "Touch Sensor", "Display", "Light Sensor", "Pressure Sensor"
                , "Phone Buttons", "Speaker Test", "Gravity sensor", "Magnetic Sensor", "Headphone",
                "Gyroscope", "GPS Location", "Battery Indicator", "Accelarometer");

        ListView lv = (ListView)findViewById(R.id.listcase);

        CheckListAdapter cla = new CheckListAdapter(getApplicationContext(), items );

        lv.setAdapter(cla );

        /*FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.framecontainer, new CheckListFragment());
        ft.commit();*/
    }


}
