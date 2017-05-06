package com.gr.facultyengagementbook.Admin.DateTab;


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

import com.gr.facultyengagementbook.R;

import java.util.ArrayList;

public class DateFragment extends Fragment
{

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> monthNames;
    EditText monthSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.admin_date_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        monthNames = getMonthNames();
        recyclerView = (RecyclerView) view.findViewById(R.id.admin_recycler_view_date_fragment);
        adapter = new DateFragmentAdapter(monthNames,getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        monthSearch = (EditText) view.findViewById(R.id.search_text_date_fragment);
        searchListener();
    }

       private ArrayList<String> getMonthNames()
       {
           ArrayList<String> monthNames = new ArrayList<>();
           monthNames.add("April 2017");
           monthNames.add("March 2017");
           monthNames.add("February 2017");
           monthNames.add("January 2017");
           monthNames.add("December 2016");
           monthNames.add("November 2016");
           monthNames.add("October 2016");
           monthNames.add("September 2016");
           monthNames.add("August 2016");
           monthNames.add("July 2016");
           return monthNames;
       }


    private void searchListener()
    {
        monthSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();
                final ArrayList<String> searchedMonthNames = new ArrayList<>();
                for (int i=0; i<monthNames.size(); i++)
                {
                    final String recyclerViewText = monthNames.get(i).toLowerCase();
                    if(recyclerViewText.contains(query))
                    {
                        searchedMonthNames.add(monthNames.get(i));
                    }

                    adapter = new DateFragmentAdapter(searchedMonthNames,getActivity());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
