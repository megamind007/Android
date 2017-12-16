package com.sakib.servertest;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by sakib on 20-Mar-16.
 */
public class Splash extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        tv=(TextView)findViewById(R.id.tvSplash);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"font.otf");
        tv.setTypeface(typeface);


        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent i=new Intent(Splash.this,MainActivity.class);
                    startActivity(i);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }
}
