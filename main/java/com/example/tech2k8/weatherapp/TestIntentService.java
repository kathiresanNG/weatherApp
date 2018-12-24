package com.example.tech2k8.weatherapp;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class TestIntentService extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public TestIntentService() {
        super("Test Intent service");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
