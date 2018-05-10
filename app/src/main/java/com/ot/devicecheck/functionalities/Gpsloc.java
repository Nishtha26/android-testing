package com.ot.devicecheck.functionalities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ot.devicecheck.R;
import com.ot.devicecheck.dataObject.SQLElement;
import com.ot.devicecheck.database.DatabaseHandler;


public class Gpsloc extends Activity {

    TextView tr, ts;
    double latitude, longitude;
    SQLElement element;
    DatabaseHandler db;
    DialogInterface.OnClickListener dialogClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsloc);
        Gpslocate gps = new Gpslocate(this);
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();
        tr = (TextView) findViewById(R.id.textView60);
        ts = (TextView) findViewById(R.id.textView61);

        db = new DatabaseHandler(this);

        dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Log.i("Positive", "Clicked");
                        element = new SQLElement();
                        element.setElement("GPS");
                        element.setVerdict("Working");
                        element.setTimeStamp("" + System.currentTimeMillis());
                        db.addElement(element);
                        db.updateElement(element);
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Log.i("Negative", "Clicked");
                        element = new SQLElement();
                        element.setElement("GPS");
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
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Gpsloc.this);
        builder.setMessage("Is it functionality working properly?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
    }

    @Override
    protected void onStart() {
        tr.setText("latitude " + String.valueOf(latitude));
        ts.setText("longitude " + String.valueOf(longitude));
        super.onStart();
    }

}