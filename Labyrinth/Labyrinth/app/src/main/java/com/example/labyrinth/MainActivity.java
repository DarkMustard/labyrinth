package com.example.labyrinth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private MenuAdapter menuAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH= dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        menuAdapter = new MenuAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);
        setUpViewPager(viewPager);

    }

    private void setUpViewPager(ViewPager viewPager){
        MenuAdapter adapter = new MenuAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment0());
        adapter.addFragment(new Fragment1());
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int number){
        viewPager.setCurrentItem(number);
    }
}