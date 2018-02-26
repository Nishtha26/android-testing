package com.ot.devicecheck.functionalities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ot.devicecheck.R;
import com.ot.devicecheck.dataObject.SQLElement;
import com.ot.devicecheck.database.DatabaseHandler;

public class ProximitySensor extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    TextView tVProximity;
    SQLElement element;
    DatabaseHandler db;
    DialogInterface.OnClickListener dialogClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);


        tVProximity = (TextView) findViewById(R.id.tVProximity);
        //create instance of sensor manager and get system service to interact with Sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (proximitySensor == null) {
            Toast.makeText(ProximitySensor.this, "No Proximity Sensor Found! ", Toast.LENGTH_LONG).show();
        }
        db = new DatabaseHandler(this);

        dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case DialogInterface.BUTTON_POSITIVE:
                        Log.i("Positive","Clicked");
                        element = new SQLElement();
                        element.setElement("Proximity");
                        element.setVerdict("Working");
                        element.setTimeStamp(""+System.currentTimeMillis());
                        db.addElement(element);
                        db.updateElement(element);
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Log.i("Negative","Clicked");
                        element = new SQLElement();
                        element.setElement("Proximity");
                        element.setVerdict("Not Working");
                        element.setTimeStamp(""+System.currentTimeMillis());
                        db.addElement(element);
                        db.updateElement(element);
                        finish();
                        break;
                }
            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the Proximity Sensor
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    // called when sensor value have changed
    @Override
    public void onSensorChanged(SensorEvent event) {
        // The Proximity sensor returns a single value either 0 or 5(also 1 depends on Sensor manufacturer).
        // 0 for near and 5 for far
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] == 0) {
                tVProximity.setText("You are Near: " + String.valueOf(event.values[0]));
            } else {
                tVProximity.setText("You are Far: " + String.valueOf(event.values[0]));
            }

        }
    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(ProximitySensor.this);
        builder.setMessage("Is it functionality working properly?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();


    }
}
