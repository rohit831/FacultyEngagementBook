package com.gr.facultyengagementbook.HomeScreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gr.facultyengagementbook.GroupsTab.GroupsFragment;
import com.gr.facultyengagementbook.RecordsTab.RecordsFragment;
import com.gr.facultyengagementbook.TimetableTab.TimetableFragment;

public class HomeScreenAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public HomeScreenAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                TimetableFragment timetableFragment = new TimetableFragment();
                return timetableFragment;
            case 1:
                RecordsFragment recordsFragment = new RecordsFragment();
                return recordsFragment;
            case 2:
                GroupsFragment groupsFragment = new GroupsFragment();
                return groupsFragment;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
