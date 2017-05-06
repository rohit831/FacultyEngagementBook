package com.gr.facultyengagementbook.SplashScreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.gr.facultyengagementbook.Authentication.Login;
import com.gr.facultyengagementbook.R;


public class SplashScreen extends AppCompatActivity {

    ImageView splash_logo;
    TypeWriter typeWriter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splash_logo = (ImageView) findViewById(R.id.splash_logo);
        typeWriter = (TypeWriter) findViewById(R.id.splash_text);

        typeWriter.setCharacterDelay(90);
        typeWriter.animateText("Faculty Engagement Book");

        splash_logo.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fadein));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        },3000);
    }
}
