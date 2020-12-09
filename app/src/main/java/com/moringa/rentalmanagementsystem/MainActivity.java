package com.moringa.rentalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    Animation topAnimation,bottomAnimation;
    @BindView(R.id.launcherImageView) ImageView launcherImageView;
    @BindView(R.id.launcherTextView) TextView launcherTextView;
//    @BindView(R.id.launcherTextView2) TextView launcherTextView2;

    private static  int SPLASH_SCREEN=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        topAnimation= AnimationUtils.loadAnimation(this,R.anim.topanimation);
        bottomAnimation= AnimationUtils.loadAnimation(this,R.anim.bottomanimation);

        launcherTextView.setAnimation(topAnimation);
        launcherImageView.setAnimation(topAnimation);
//        launcherTextView2.setAnimation(topAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}