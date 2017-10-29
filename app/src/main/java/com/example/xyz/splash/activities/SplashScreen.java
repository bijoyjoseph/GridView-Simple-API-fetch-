package com.example.xyz.splash.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xyz.splash.R;
import com.example.xyz.splash.util.PreferenceUtil;

public class SplashScreen extends AppCompatActivity {

    public static final int SPLASH_DISPLAY_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent();
                if(PreferenceUtil.fetchPrefString(SplashScreen.this, PreferenceUtil.LOGIN_DETAILS, PreferenceUtil.EMAIL) != null
                        && PreferenceUtil.fetchPrefString(SplashScreen.this, PreferenceUtil.LOGIN_DETAILS, PreferenceUtil.PASSWORD) != null) {
                    intent.setClass(SplashScreen.this, MainActivity.class);
                }
                else {
                    intent.setClass(SplashScreen.this, LoginActivity.class);
                }
                SplashScreen.this.startActivity(intent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_DURATION);
    }
}
