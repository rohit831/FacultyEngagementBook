package com.gr.facultyengagementbook.Admin.FacultyTab;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gr.facultyengagementbook.Authentication.Login;
import com.gr.facultyengagementbook.GroupsTab.GroupsFragmentAdapter;
import com.gr.facultyengagementbook.Model.DBHandler;
import com.gr.facultyengagementbook.Model.FacultyDetails;
import com.gr.facultyengagementbook.R;

import java.util.ArrayList;

public class FacultyFragment extends Fragment
{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private final Context context = getActivity();
    private DBHandler dbHandler;
    EditText facultySearch;
    private ArrayList<FacultyDetails> facultyDetails = new ArrayList<FacultyDetails>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.admin_faculty_fragment,container,false);
         return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHandler = new DBHandler(getActivity(),null,null,1);
        recyclerView = (RecyclerView) view.findViewById(R.id.admin_recycler_view_facutly_fragment);
        layoutManager = new LinearLayoutManager(getActivity());
        facultySearch = (EditText)view.findViewById(R.id.search_text_faculty_fragment);
//      SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Login.PREFS,Context.MODE_PRIVATE);
        facultyDetails = dbHandler.getAllFacultyDetails("");

//        facultyDetails.add(new FacultyDetails("Dr.Barik Sir", "7771925730","",""));
//        facultyDetails.add(new FacultyDetails("Dr.Gaurav Sir", "8109797908","",""));
//        facultyDetails.add(new FacultyDetails("Dr.Rohit Sir", "9039888667","",""));
//        facultyDetails.add(new FacultyDetails("Dr.Rahul Sir", "8959551927","",""));

        adapter = new FacultyFragmentAdapter(getActivity(),facultyDetails);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        searchListener();
    }

    public void searchListener()
    {
        facultySearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                query = query.toString().toLowerCase();

                final ArrayList<FacultyDetails> searchedFacultyList = new ArrayList<>();
                for(int i= 0 ; i<facultyDetails.size() ; i++)
                {
                    final String recyclerViewText = facultyDetails.get(i).getName().toLowerCase();
                    if(recyclerViewText.contains(query))
                    {
                        searchedFacultyList.add(facultyDetails.get(i));
                    }
                }

                adapter = new FacultyFragmentAdapter(getActivity(),searchedFacultyList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

}
