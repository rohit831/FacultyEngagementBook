package com.gr.facultyengagementbook.TimetableTab;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.gr.facultyengagementbook.Authentication.Login;
import com.gr.facultyengagementbook.Model.DBHandler;
import com.gr.facultyengagementbook.Model.TimeTableDetails;
import com.gr.facultyengagementbook.R;

public class TimetableFragment extends Fragment {

    private RecyclerView recyclerView;
    private DBHandler dbHandler;
    private RecyclerView.Adapter adapter;
    private TextView day;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<TimeTableDetails> timeTableDetails = new ArrayList<TimeTableDetails>();
    SharedPreferences sharedPreferences;
    private ArrayList<String > assignedFaculties = new ArrayList<String >();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_timetable_fragment,container,false);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        day = (TextView) view.findViewById(R.id.timetable_day);
        day.setText(dayOfTheWeek);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbHandler = new DBHandler(getActivity(),null,null,1);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_timetable_fragment);
        sharedPreferences = getActivity().getSharedPreferences(Login.PREFS, Context.MODE_PRIVATE);

        if(!(day.getText().toString().substring(0,3).equals("Sun")))
            timeTableDetails = dbHandler.getTimeTable(day.getText().toString().substring(0,3)
                    ,sharedPreferences.getString(Login.MOBILE_NO,""));

        for(int i = 0; i < 8; i++){

            assignedFaculties.add(dbHandler.getEngagedFacultyName(new SimpleDateFormat("dd-MMM-yy").format(new Date()),
                    sharedPreferences.getString(Login.MOBILE_NO,""),i));
        }

        adapter = new TimetableFragmentAdapter(getContext(),timeTableDetails,assignedFaculties);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }
}