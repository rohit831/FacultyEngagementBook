package com.gr.facultyengagementbook.Timetable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class EditTimetableAdapter extends FragmentPagerAdapter {

    int numOfTabs;

    public EditTimetableAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MonFragment monFragment = new MonFragment();
                return monFragment;

            case 1:
                TueFragment tueFragment = new TueFragment();
                return tueFragment;

            case 2:
                WedFragment wedFragment = new WedFragment();
                return wedFragment;

            case 3:
                ThuFragment thuFragment = new ThuFragment();
                return thuFragment;

            case 4:
                FriFragment friFragment = new FriFragment();
                return friFragment;

            case 5:
                SatFragment satFragment = new SatFragment();
                return satFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
