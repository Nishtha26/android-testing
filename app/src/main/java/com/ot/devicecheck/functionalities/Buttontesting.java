package com.ot.devicecheck.functionalities;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import com.ot.devicecheck.R;
import com.ot.devicecheck.dataObject.SQLElement;
import com.ot.devicecheck.database.DatabaseHandler;

public class Buttontesting extends Activity implements OnTouchListener {
    private static final String TAG = "Touch";
    SQLElement element;
    DatabaseHandler db;
    DialogInterface.OnClickListener dialogClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttontesting);

        db = new DatabaseHandler(this);

        dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Log.i("Positive", "Clicked");
                        element = new SQLElement();
                        element.setElement("Phone Buttons");
                        element.setVerdict("Working");
                        element.setTimeStamp("" + System.currentTimeMillis());
                        db.addElement(element);
                        db.updateElement(element);
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Log.i("Negative", "Clicked");
                        element = new SQLElement();
                        element.setElement("Phone Buttons");
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

    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_UP) {
                    Toast.makeText(getApplicationContext(), "PRESSED VOLUME UP BUTTON", Toast.LENGTH_SHORT).show();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    Toast.makeText(getApplicationContext(), "PRESSED VOLUME DOWN BUTTON", Toast.LENGTH_SHORT).show(); //TODO
                }
                return true;


            default:
                return super.dispatchKeyEvent(event);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(Buttontesting .this);
        builder.setMessage("Is it functionality working properly?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();


    }


}
