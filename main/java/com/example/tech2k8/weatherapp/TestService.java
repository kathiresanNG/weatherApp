package com.example.tech2k8.weatherapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class TestService extends Service{

    Runnable runnable;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Intent intent1 = new Intent(TestService.this,MapsActivity.class);
        PendingIntent pendingIntent =PendingIntent.getActivity(TestService.this,2010,intent1,0);

        NotificationCompat.Builder builder =new NotificationCompat.Builder(TestService.this,"Defalut");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Background work");
        builder.setContentText("We are working in background");
        builder.setContentIntent(pendingIntent);



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            NotificationManager manager =getSystemService(NotificationManager.class);
            manager.notify(1001,builder.build());
        }
        else
        {
            NotificationManager manager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(1001,builder.build());
        }


        final Handler handler =new Handler();
        runnable =new Runnable() {
            @Override
            public void run() {
                Log.i("TestService","servvice is running");
                handler.postDelayed(runnable, 1000);
                //stopSelf();
            }
        };
        handler.post(runnable);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
