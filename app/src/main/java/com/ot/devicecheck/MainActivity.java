package com.ot.devicecheck;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("OT Device Check");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button device_info = (Button)findViewById(R.id.button1);
        device_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),CompleteInfo.class);
                startActivity(intent);
            }
        });

        Button auto_test = (Button)findViewById(R.id.button2);
        auto_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),Auto_Test.class);
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