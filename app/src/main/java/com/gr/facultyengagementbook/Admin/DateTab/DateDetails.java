package com.gr.facultyengagementbook.Admin.DateTab;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gr.facultyengagementbook.Model.DBHandler;
import com.gr.facultyengagementbook.Model.RecordDetails;
import com.gr.facultyengagementbook.R;

import java.util.ArrayList;

public class DateDetails extends AppCompatActivity {
    TextView date_selected;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<RecordDetails> recordDetails = new ArrayList<>();
    private DBHandler dbHandler;
    private final Context context = this;
    private  String date,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_date_show_details);

        date = getIntent().getStringExtra("Date");
        month = getIntent().getStringExtra("Month");
        year = getIntent().getStringExtra("Year");

        date_selected = (TextView) findViewById(R.id.admin_date_details_header);
        date_selected.setText(date + " " + month);

        dbHandler = new DBHandler(context,null,null,1);
        recyclerView = (RecyclerView) findViewById(R.id.admin_recycler_view_date_details);
        layoutManager = new LinearLayoutManager(context);

        String month = date_selected.getText().toString().substring(0,3);
        String year = date_selected.getText().toString().substring(date_selected.getText().toString().length()-3);
        recordDetails = dbHandler.getAllRecordsByDate(date,month,year);



        adapter = new DateDetailsAdapter(recordDetails);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


    }


}
