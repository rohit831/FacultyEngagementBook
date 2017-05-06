package com.gr.facultyengagementbook.Admin.HomeScreen;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gr.facultyengagementbook.Admin.DateTab.DateFragment;
import com.gr.facultyengagementbook.Admin.FacultyTab.FacultyFragment;

public class AdminHomeScreenAdapter extends FragmentStatePagerAdapter
{
    int numOfTabs;

    public AdminHomeScreenAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new FacultyFragment();
            case 1:
                return new DateFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
