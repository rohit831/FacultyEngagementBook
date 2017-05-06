package com.gr.facultyengagementbook.HomeScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.gr.facultyengagementbook.Authentication.Login;
import com.gr.facultyengagementbook.R;
import com.gr.facultyengagementbook.Timetable.EditTimetable;

public class HomeScreen extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.hometab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Time Table"));
        tabLayout.addTab(tabLayout.newTab().setText("Records"));
        tabLayout.addTab(tabLayout.newTab().setText("Groups"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.home_viewPager);
        final HomeScreenAdapter homeScreenAdapter = new HomeScreenAdapter(getSupportFragmentManager()
                ,tabLayout.getTabCount());
        viewPager.setAdapter(homeScreenAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.edit_timetable:
                startActivity(new Intent(HomeScreen.this, EditTimetable.class));
                return true;
            case R.id.logout:
                SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(this, Login.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
        }        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit!!", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {            @Override
        public void run() {
            doubleBackToExitPressedOnce=false;
        }
        }, 2000);
    }
}
