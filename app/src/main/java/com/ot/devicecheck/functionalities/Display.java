package com.ot.devicecheck.functionalities;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ot.devicecheck.R;
import com.ot.devicecheck.dataObject.SQLElement;
import com.ot.devicecheck.database.DatabaseHandler;

public class Display extends Activity {
    int[] a;
    RelativeLayout r;
    TextView tv;

    SQLElement element;
    DatabaseHandler db;
    DialogInterface.OnClickListener dialogClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        tv = (TextView) findViewById(R.id.textView13);
        r = (RelativeLayout) findViewById(R.id.rr);
        a = new int[10];
        a[0] = Color.parseColor("#f4c2c6");
        a[1] = Color.parseColor("#c7afce");
        a[2] = Color.parseColor("#fbcab3");
        a[3] = Color.parseColor("#ffe8f4");
        a[4] = Color.parseColor("#525252");
        a[5] = Color.parseColor("#bcdbbe");
        a[6] = Color.parseColor("#1ca589");
        a[7] = Color.parseColor("#c1073f");
        a[8] = Color.parseColor("#edcdc3");
        a[9] = Color.parseColor("#ecffef");

        db = new DatabaseHandler(this);

        dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Log.i("Positive", "Clicked");
                        element = new SQLElement();
                        element.setElement("Display");
                        element.setVerdict("Working");
                        element.setTimeStamp("" + System.currentTimeMillis());
                        db.addElement(element);
                        db.updateElement(element);
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Log.i("Negative", "Clicked");
                        element = new SQLElement();
                        element.setElement("Display");
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

    public boolean onTouchEvent(android.view.MotionEvent event) {
        tv.setVisibility(View.INVISIBLE);
        int x = (int) (Math.random() * 10);
        r.setBackgroundColor(a[x]);
        return false;
    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(Display.this);
        builder.setMessage("Is it functionality working properly?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();


    }

}
