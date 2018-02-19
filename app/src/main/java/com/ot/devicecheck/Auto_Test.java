package com.ot.devicecheck;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pranjal Gupta on 29-01-2018.
 */

public class Auto_Test extends AppCompatActivity {

    List<String> items, itemsClass;
    CheckListAdapter cla;
    String name;
    List<String> selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatedtest);

        selected = new ArrayList<String>();

        items = Arrays.asList("Vibration", /*"Check Version Info", "SIM Card",*/ "Proximity Sensor",
                "Flash", "Touch Sensor", "Display", "Light Sensor", "Pressure Sensor"
                , "Phone Buttons", "Speaker Test", "Gravity sensor", "Magnetic Sensor", "Headphone",
                "Gyroscope", "GPS Location", "Battery Indicator", "Accelarometer");

        itemsClass = Arrays.asList("Vibration", "ProximitySensor",
                "Flash", "TouchSensor", "Display", "Lightsensor", "Pressure",
                "Buttontesting", "Mictesting", "Gravitysensor", "Magneticsensor", "Headphone",
                "Gyroscope", "Gpsloc", "Batteryindicator", "Accelarometer");

        Button cont = (Button) findViewById(R.id.buttonCont);

        ListView lv = (ListView) findViewById(R.id.listcase);

        cla = new CheckListAdapter(getApplicationContext(), items, itemsClass);


        lv.setAdapter(cla);

        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        name = "Vibration";
                        break;

                    case 1:

                        name = "ProximitySensor";
                        break;

                    case 2:
                        name = "Flash";
                        break;

                    case 3:

                        name = "TouchSensor";
                        break;
                    case 4:
                        //display multiple lighting
                        name = "Display";
                        break;
                    case 5:
                        //light sensor display of dac values
                        name = "Lightsensor";
                        break;
                    case 6:

                        name = "Pressure";
                        break;

                    case 7:
                        name = "Buttontesting";
                        break;
                    case 8:
                        name = "Mictesting";
                        break;

                    case 9:

                        name = "Gravitysensor";
                        break;

                    case 10:
                        name = "Magneticsensor";
                        break;
                    case 11:
                        name = "Headphone";
                        break;
                    case 12:
                        name = "Gyroscope";
                        break;
                    case 13:
                        name = "Gpsloc";
                        break;
                    case 14:
                        name = "Batteryindicator";
                        break;
                    case 15:
                        name = "Accelarometer";
                        break;
                }

                Log.i("Checkbox", "" + name);

                String abc = cla.getItem(i);

                if (cla.getCheckBox()) {
                    cla.setCheckBox(false);
                    selected.remove(name);
                } else {
                    cla.setCheckBox(true);
                    selected.add(name);


                }


            }
        });*/


        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> checked_items = cla.check();
                Iterator itr = checked_items.iterator();
                while (itr.hasNext())
                {
                    Log.i("Iterator", ""+itr.next().toString());
                }

                if (checked_items.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please select an item", Toast.LENGTH_SHORT);
                } else {
                    String name = checked_items.get(0);
                    try {
                        Intent intent = new Intent(getApplication(), Class.forName("com.ot.devicecheck." + name));
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        /*FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.framecontainer, new CheckListFragment());
        ft.commit();*/
    }


}
