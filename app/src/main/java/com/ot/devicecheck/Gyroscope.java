package com.ot.devicecheck;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Gyroscope extends Activity implements SensorEventListener {
    TextView tvx, tvy, tvz;
    SensorManager sensmgr;
    Sensor gyrosensor;
    float[] sensorvalues;
    SQLElement element;
    DatabaseHandler db;
    DialogInterface.OnClickListener dialogClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        tvx = (TextView) findViewById(R.id.textView8);
        tvy = (TextView) findViewById(R.id.textView9);
        tvz = (TextView) findViewById(R.id.textView10);

        sensmgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyrosensor = sensmgr.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        db = new DatabaseHandler(this);

        dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Log.i("Positive", "Clicked");
                        element = new SQLElement();
                        element.setElement("Gyroscope");
                        element.setVerdict("Working");
                        element.setTimeStamp("" + System.currentTimeMillis());
                        db.addElement(element);
                        db.updateElement(element);
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Log.i("Negative", "Clicked");
                        element = new SQLElement();
                        element.setElement("Gyroscope");
                        element.setVerdict("Not Working");
                        element.setTimeStamp("" + System.currentTimeMillis());
                        db.addElement(element);
                        db.updateElement(element);
                        finish();
                        break;
                }
            }
        };
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gyroscope, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        sensorvalues = event.values;
        float x = sensorvalues[0];
        float y = sensorvalues[1];
        float z = sensorvalues[2];
        tvx.setText("x " + x + " rad/sec");
        tvy.setText("y " + y + " rad/sec");
        tvz.setText("z " + z + " rad/sec");


    }


    @Override
    protected void onResume() {
        sensmgr.registerListener(this, gyrosensor, SensorManager.SENSOR_DELAY_NORMAL);

        super.onResume();
    }


    @Override
    protected void onPause() {
        sensmgr.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Gyroscope.this);
        builder.setMessage("Is it functionality working properly?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
    }
}
