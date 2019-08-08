package com.rejsebuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    /**
     * Initiate the activity.
     *
     * @param state The previous state.
     */
    @Override
    protected void onCreate(Bundle state) {
        // Initiate the content view.
        super.onCreate(state);
        setContentView(R.layout.activity_main);

        // Connect page adapter to view element.
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(new MainPageAdapter(this, getSupportFragmentManager()));

        // Connect tab layout to view element.
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);
    }

    /**
     * Starts the settings page activity.
     *
     * @param view The current view
     */
    public void openSettings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
