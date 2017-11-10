package com.ot.devicecheck;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Date;

public class Vibration extends Activity {
Button b1;
    SQLElement element;
    DatabaseHandler db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vibration);
		ImageView vib = (ImageView)findViewById(R.id.vib);
        ImageView yes = (ImageView) findViewById(R.id.yes);
        ImageView no = (ImageView) findViewById(R.id.no);

        db = new DatabaseHandler(this);

        /*b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				v1.vibrate(400);
				Toast.makeText(getApplicationContext(), "Your Vibration Working Very Well",Toast.LENGTH_SHORT).show();
			}
		});*/

		vib.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				v1.vibrate(400);
			}
		});

        yes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                element = new SQLElement();
                element.setElement("Vibration");
                element.setVerdict("Working");
                element.setTimeStamp(""+System.currentTimeMillis());
                Log.i("yes","yes");
                db.addElement(element);
                finish();
            }
        });

        no.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                element = new SQLElement();
                element.setElement("Vibration");
                element.setVerdict("Not Working");
                element.setTimeStamp(""+System.currentTimeMillis());
                Log.i("no","no");
                db.addElement(element);
                finish();
            }
        });
	}
}
