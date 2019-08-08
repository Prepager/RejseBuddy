package com.rejsebuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Create the instance.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect page adapter to view element.
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(new MainPageAdapter(this, getSupportFragmentManager()));

        // Connect tab layout to view element.
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);
    }
}
