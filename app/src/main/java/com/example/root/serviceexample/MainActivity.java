package com.example.root.serviceexample;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.root.serviceexample.service.CustomService;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private boolean isServiceConnected;
    private CustomService mCustomService;
    private TextView mTime;
    private Button mGetTime;

    private TextView mPausetime;
    private Button mShowTime;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTime = (TextView) findViewById(R.id.time);
        mGetTime = (Button) findViewById(R.id.gettime);

        mPausetime = (TextView) findViewById(R.id.pausetime);
        mShowTime = (Button) findViewById(R.id.ShowTime);

        mGetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTime.setText(getTime());
            }
        });

        mShowTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mPausetime.setText(pausetime());

            }
        });


        Intent intent = new Intent(this, CustomService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        // startService(intent);


    }

    private String getTime() {
        if (mCustomService != null) {
            return mCustomService.getTime();
        }
        return "";
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isServiceConnected = true;
            CustomService.CustomBinder binder = (CustomService.CustomBinder) service;
            mCustomService = null != binder ? binder.getInstance() : null;
            mCustomService.startTimer();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceConnected = false;
        }
    };}


