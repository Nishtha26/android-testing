package com.ot.devicecheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ot.devicecheck.adapters.ManualTest_Adapter;
import com.ot.devicecheck.functionalities.Accelarometer;
import com.ot.devicecheck.functionalities.Batteryindicator;
import com.ot.devicecheck.functionalities.Buttontesting;
import com.ot.devicecheck.functionalities.Display;
import com.ot.devicecheck.functionalities.Flash;
import com.ot.devicecheck.functionalities.Gpsloc;
import com.ot.devicecheck.functionalities.Gravitysensor;
import com.ot.devicecheck.functionalities.Gyroscope;
import com.ot.devicecheck.functionalities.Headphone;
import com.ot.devicecheck.functionalities.Lightsensor;
import com.ot.devicecheck.functionalities.Magneticsensor;
import com.ot.devicecheck.functionalities.Mictesting;
import com.ot.devicecheck.functionalities.Pressure;
import com.ot.devicecheck.functionalities.ProximitySensor;
import com.ot.devicecheck.functionalities.TouchSensor;
import com.ot.devicecheck.functionalities.Vibration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Pranjal Gupta on 09-10-2017.
 */

public class Manual_Test extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Intent intent;

    Integer[] icons = {R.drawable.vibration, /*R.drawable.versioncheck2, R.drawable.sim,*/ R.drawable.proximity,
            R.drawable.flash, R.drawable.touch, R.drawable.display, R.drawable.light, R.drawable.pressure, R.drawable.phonebut, R.drawable.speaker,
            R.drawable.gravity, R.drawable.magnetic, R.drawable.headphone,
            R.drawable.gyroscope, R.drawable.gps, R.drawable.battery2, R.drawable.accelerometer};

    List<String> items;
    //AutoCompleteTextView act;
    ListView lv;
    //Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manualtest);

        items = Arrays.asList("Vibration Test", /*"Check Version Info", "SIM Card",*/ "Proximity Sensor",
                "Flash Light", "Touch Sensor", "Display", "Light Sensor", "Pressure Sensor"
                , "Phone Buttons", "Speaker Test", "Gravity sensor", "Magnetic Sensor", "Headphone",
                "Gyroscope", "GPS Location", "Battery Indicator", "Accelarometer");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("OT Device Check");

        lv = (ListView) findViewById(R.id.listView1);

        ManualTest_Adapter adap = new ManualTest_Adapter(getApplicationContext(), items, icons);
        //ArrayAdapter<String> adapt=new ArrayAdapter<String>(this,R.layout.extraa,items);
        lv.setAdapter(adap);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        // TODO Auto-generated method stub
        switch (pos) {
            case 0:
                //not working
                intent = new Intent(this, Vibration.class);
                startActivity(intent);
                break;
/*            case 2:
                intent = new Intent(this, Tele.class);
                startActivity(intent);
                break;
            case 1:
                //not whole info
                intent = new Intent(getApplicationContext(), Systeminfo.class);
                startActivity(intent);
                break;*/
            case 1:

                intent = new Intent(getApplicationContext(), ProximitySensor.class);
                startActivity(intent);
                break;

            case 2:
                //not working
                intent = new Intent(getApplicationContext(), Flash.class);
                startActivity(intent);
                break;

            case 3:

                intent = new Intent(getApplicationContext(), TouchSensor.class);
                startActivity(intent);
                break;
            case 4:
                //display multiple lighting
                intent = new Intent(getApplicationContext(), Display.class);
                startActivity(intent);
                break;
            case 5:
                //light sensor display of dac values
                intent = new Intent(getApplicationContext(), Lightsensor.class);
                startActivity(intent);
                break;
            case 6:

                intent = new Intent(getApplicationContext(), Pressure.class);
                startActivity(intent);
                break;

            case 7:
                //wrong the volume buttons,home,menu etc has to be checked here
                intent = new Intent(getApplicationContext(), Buttontesting.class);
                startActivity(intent);
                break;
            case 8:
                //wrong should have done that while taking calling position we here our voice
                intent = new Intent(getApplicationContext(), Mictesting.class);
                startActivity(intent);
                break;
/*            case 9:

                intent = new Intent(getApplicationContext(), Wifiaddress.class);
                startActivity(intent);
                break;
            case 10:

                intent = new Intent(getApplicationContext(), Blueadd.class);
                startActivity(intent);
                break;*/
            case 9:

                intent = new Intent(getApplicationContext(), Gravitysensor.class);
                startActivity(intent);
                break;

            case 10:
                intent = new Intent(getApplicationContext(), Magneticsensor.class);
                startActivity(intent);
                break;
            case 11:
                //not working
                intent = new Intent(getApplicationContext(), Headphone.class);
                startActivity(intent);
                break;
            case 12:
                intent = new Intent(getApplicationContext(), Gyroscope.class);
                startActivity(intent);
                break;
            case 13:
                //test again
                intent = new Intent(getApplicationContext(), Gpsloc.class);
                startActivity(intent);
                break;
            case 14:
                intent = new Intent(getApplicationContext(), Batteryindicator.class);
                startActivity(intent);
                break;
            case 15:
                intent = new Intent(getApplicationContext(), Accelarometer.class);
                startActivity(intent);
                break;
        }


    }
}
