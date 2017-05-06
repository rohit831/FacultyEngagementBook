package com.gr.facultyengagementbook.TimetableTab.AvailableFaculty;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gr.facultyengagementbook.Authentication.Login;
import com.gr.facultyengagementbook.Model.AvailableFacultyDetails;
import com.gr.facultyengagementbook.Model.DBHandler;
import com.gr.facultyengagementbook.Model.TimeTableDetails;
import com.gr.facultyengagementbook.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AvailableFaculty extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private final Context context = this;
    private ArrayList<AvailableFacultyDetails> availableFacultyDetails = new ArrayList<AvailableFacultyDetails>();
    private DBHandler dbHandler;
    SharedPreferences sharedPreferences;
    private TimeTableDetails timeTableDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_faculties);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_available_faculties);
        layoutManager = new LinearLayoutManager(context);

        dbHandler = new DBHandler(this,null,null,1);
        sharedPreferences = getSharedPreferences(Login.PREFS,MODE_PRIVATE);
        availableFacultyDetails = dbHandler.getAvailableFaculties(new SimpleDateFormat("EEE").format(new Date()),
                sharedPreferences.getString(Login.MOBILE_NO,""),getIntent().getIntExtra("Period",0));

        timeTableDetails = new TimeTableDetails(getIntent().getIntExtra("Period",0),getIntent().getStringExtra("Branch"),
                getIntent().getStringExtra("Semester"));

        adapter = new AvailableFacultyRecyclerAdapter(context,availableFacultyDetails,timeTableDetails);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }
}
