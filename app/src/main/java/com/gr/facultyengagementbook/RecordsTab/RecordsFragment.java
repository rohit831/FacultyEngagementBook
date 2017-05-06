package com.gr.facultyengagementbook.RecordsTab;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;

import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import com.gr.facultyengagementbook.Authentication.Login;
import com.gr.facultyengagementbook.Model.DBHandler;
import com.gr.facultyengagementbook.Model.FacultyRecordDetails;
import com.gr.facultyengagementbook.R;

import java.util.ArrayList;

public class RecordsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FacultyRecordDetails> facultyRecordDetailses;
    private DBHandler dbHandler;
    SharedPreferences sharedPreferences;

    @Nullable

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.activity_records_fragment,container,false);

        dbHandler = new DBHandler(getContext(),null,null,1);

        sharedPreferences = getActivity().getSharedPreferences(Login.PREFS, Context.MODE_PRIVATE);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_records_fragment);
        layoutManager = new LinearLayoutManager(getActivity());
        facultyRecordDetailses = new ArrayList<FacultyRecordDetails>();
        facultyRecordDetailses = dbHandler.getFacultyRecords(sharedPreferences.getString(Login.MOBILE_NO,""));

        adapter = new RecordsFragmentAdapter(facultyRecordDetailses);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}

