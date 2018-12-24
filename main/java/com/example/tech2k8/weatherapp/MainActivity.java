package com.example.tech2k8.weatherapp;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button loadNextBtn;
    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadNextBtn=findViewById(R.id.load_next);
        Intent serviceIntent =new Intent(MainActivity.this,TestIntentService.class);


        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            startForegroundService(serviceIntent);
        }
        else{
            startService(serviceIntent);
            stopService(serviceIntent);
        }
        loadNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag)
                {
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction =manager.beginTransaction();
                    FragmentOne one = new FragmentOne();
                    transaction.replace(R.id.host,one).commit();
                    flag=false;
                }
                else
                {
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction =manager.beginTransaction();
                    FragmentTwo two = new FragmentTwo();
                    transaction.replace(R.id.host,two).commit();
                    flag=true;
                }

            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lauch_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.menu_1)
        {
            //Toast.makeText(this, "settings Menu", Toast.LENGTH_SHORT).show();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction =manager.beginTransaction();
            FragmentTwo two = new FragmentTwo();
            transaction.replace(R.id.host,two).commit();

        }

        if (item.getItemId()==R.id.menu_2)
        {
            //Toast.makeText(this, "logout ", Toast.LENGTH_SHORT).show();

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction =manager.beginTransaction();
            FragmentOne one = new FragmentOne();
            transaction.replace(R.id.host,one).commit();
        }
        return super.onOptionsItemSelected(item);
    }
}
