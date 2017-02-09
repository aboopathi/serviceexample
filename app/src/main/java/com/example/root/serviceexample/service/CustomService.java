package com.example.root.serviceexample.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by root on 21/1/17.
 */

public class CustomService extends Service{

    private CustomBinder mCustomBinder;
    private long ONE_MINUTE = 10 * 60 * 1000;
    private long ONe_SECOND = 1 * 1000;
    String time;
    String count;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        if (mCustomBinder == null) {
            mCustomBinder = new CustomBinder();
        }
        return mCustomBinder;
    }

    public void startTimer() {
        countDownTimer.start();
    }

    public void stopTimer() {
        countDownTimer.cancel();
        ;
    }

    public String getTime() {
        return time;
    }

    // public String pausetime(){
    //return  count;
//}



    CountDownTimer countDownTimer = new CountDownTimer(ONE_MINUTE, ONe_SECOND) {
        @Override
        public void onTick(long millisUntilFinished) {
            Log.v("boopathi",""+millisUntilFinished);
            time = "remaining time : " + millisUntilFinished / 1000;


        }

        @Override
        public void onFinish() {

        }
    };

    public class CustomBinder extends Binder {

        public CustomService getInstance() {
            return CustomService.this;
        }

    }
}
