package com.gr.facultyengagementbook.GroupsTab;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.gr.facultyengagementbook.Authentication.Login;
import com.gr.facultyengagementbook.Model.DBHandler;
import com.gr.facultyengagementbook.Model.FacultyDetails;
import com.gr.facultyengagementbook.R;


public class GroupsFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private final Context context = getActivity();
    private DBHandler dbHandler;
    private ArrayList<FacultyDetails> facultyDetails = new ArrayList<FacultyDetails>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_groups_fragment,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHandler = new DBHandler(getActivity(),null,null,1);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_groups_fragment);
        layoutManager = new LinearLayoutManager(getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Login.PREFS,Context.MODE_PRIVATE);
        facultyDetails = dbHandler.getAllFacultyDetails(sharedPreferences.getString(Login.MOBILE_NO,""));
//
//        facultyDetails.add(new FacultyDetails("Dr.Barik Sir", "7771925730","",""));
//        facultyDetails.add(new FacultyDetails("Dr.Gaurav Sir", "8109797908","",""));
//        facultyDetails.add(new FacultyDetails("Dr.Rohit Sir", "9039888667","",""));
//        facultyDetails.add(new FacultyDetails("Dr.Rahul Sir", "8959551927","",""));

        adapter = new GroupsFragmentAdapter(facultyDetails,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }
}
