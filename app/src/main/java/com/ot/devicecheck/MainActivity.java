package com.ot.devicecheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity
{
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button device_info = (Button)findViewById(R.id.button1);
        device_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),DeviceInfo.class);
                startActivity(intent);
            }
        });

        Button manual_test = (Button)findViewById(R.id.button3);
        manual_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), Manual_Test.class);
                startActivity(intent);
            }
        });
    }





}