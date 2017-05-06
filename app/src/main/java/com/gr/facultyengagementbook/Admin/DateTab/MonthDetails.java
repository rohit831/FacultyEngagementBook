package com.gr.facultyengagementbook.Admin.DateTab;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gr.facultyengagementbook.R;
import com.gr.facultyengagementbook.Utilities.DaysInMonth;

public class MonthDetails extends AppCompatActivity{

    private TextView month_selected;
    private RecyclerView recyclerView;
    final Context context = this;
    private  String year,month;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_month_details);

        month = getIntent().getStringExtra("Month");
        year = getIntent().getStringExtra("Year");

        month_selected = (TextView) findViewById(R.id.admin_month_details_header);
        month_selected.setText(month);

        recyclerView = (RecyclerView) findViewById(R.id.admin_recycler_view_month_details);
        layoutManager = new GridLayoutManager(context,6);
        int noOfdays = DaysInMonth.getNoOfDays(month_selected.getText().toString().substring(0,3),
                month_selected.getText().toString()
                                        .substring(month_selected.getText().length()-3));

        adapter = new MonthDetailsAdapter(noOfdays,context,month,year);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


    }
}
