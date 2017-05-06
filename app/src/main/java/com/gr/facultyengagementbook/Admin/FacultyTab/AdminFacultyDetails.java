package com.gr.facultyengagementbook.Admin.FacultyTab;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gr.facultyengagementbook.Model.DBHandler;
import com.gr.facultyengagementbook.Model.FacultyDetails;
import com.gr.facultyengagementbook.Model.FacultyRecordDetails;
import com.gr.facultyengagementbook.Model.RecordDetails;
import com.gr.facultyengagementbook.R;
import com.gr.facultyengagementbook.Utilities.NumberToRoman;

import java.util.ArrayList;

public class AdminFacultyDetails extends AppCompatActivity {

    TextView mobileNo, email,name;
    private DBHandler dbHandler;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private final Context context = this;
    private ArrayList<RecordDetails> facultyRecordDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_faculty_details);
        dbHandler = new DBHandler(this,null,null,1);
       // FacultyDetails facultyDetails = new FacultyDetails("Dr. Barik Sir","9098065041","barik958@gmail.com","");
        FacultyDetails facultyDetails = dbHandler.getFacultyDetails(getIntent().getStringExtra("faculty_mobileNo"));


        mobileNo = (TextView) findViewById( R.id.admin_faculty_details_mobileNo);
        email = (TextView) findViewById(R.id.admin_faculty_details_email);
        name = (TextView) findViewById(R.id.admin_faculty_details_name);



        mobileNo.setText(facultyDetails.getMobileNo());
        email.setText(facultyDetails.getEmail());
        name.setText(facultyDetails.getName());

        mobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mobileNo.getText().toString()));
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/html");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
                try {
                    startActivity(emailIntent);
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(AdminFacultyDetails.this, "No app found to perform this operation!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.admin_recycler_view_faculty_details);
        layoutManager = new LinearLayoutManager(context);
         facultyRecordDetails = dbHandler.getAllRecordsByMobileNo(facultyDetails.getMobileNo());
//        facultyRecordDetails.add(new RecordDetails("","","12-Apr-17","", "CSE", "4th Sem", 5,"Barik Sir"));
//        facultyRecordDetails.add(new RecordDetails("","","15-Apr-17","", "Bio.tech", "7th Sem", 4,"Mehul Sir"));
//        facultyRecordDetails.add(new RecordDetails("","","20-Apr-17","", "IT", "4th", 5,"Gaurav Sir"));
//        facultyRecordDetails.add(new RecordDetails("","","25-Apr-17","", "CSE", "4th", 5,"Rohit Sir"));

        adapter = new AdminFacultyDetailsAdapter(facultyRecordDetails,context);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

}
