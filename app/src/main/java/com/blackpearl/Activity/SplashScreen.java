package com.blackpearl.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blackpearl.R;

public class SplashScreen extends AppCompatActivity {
    static  boolean userfetched = true;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);


        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(userfetched){
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                }
                finish();
            }
        }, 4000);
    }
}
