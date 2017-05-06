package com.gr.facultyengagementbook.Timetable;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gr.facultyengagementbook.Model.DBHandler;
import com.gr.facultyengagementbook.Model.TimeTableDetails;
import com.gr.facultyengagementbook.R;
import com.gr.facultyengagementbook.Authentication.Login;
import com.gr.facultyengagementbook.Utilities.NumberToRoman;

import java.util.ArrayList;

public class MonFragment extends Fragment {

    private Button add_lecture_button;
    private TextView period_field_dialog;
    private Spinner branch_spinner, semester_spinner;
    private Button add_lecture_dialog_add_button, add_lecture_dialog_cancel_button;
    private String branch, semester;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TimeTableDetails timeTableDetails;
    private ArrayList<TimeTableDetails> timeTableDetailses;
    private DBHandler dbHandler;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_mon_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {

        super.onViewCreated(view, savedInstanceState);
        add_lecture_button = (Button)view.findViewById(R.id.add_lecture_button);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_mon_fragment);
        layoutManager = new LinearLayoutManager(getActivity());
        timeTableDetailses = new ArrayList<TimeTableDetails>();
        dbHandler = new DBHandler(getContext(),null,null,1);
        sharedPreferences = getActivity().getSharedPreferences(Login.PREFS, Context.MODE_PRIVATE);
        timeTableDetailses = dbHandler.getTimeTable("Mon",sharedPreferences.getString(Login.MOBILE_NO,""));
        adapter = new TimetableRecyclerAdapter(getContext(),timeTableDetailses,"Mon");
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        add_lecture_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View promptView = layoutInflater.inflate(R.layout.add_lecture_dialog,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(promptView);
                period_field_dialog = (TextView)promptView.findViewById(R.id.period_field_dialog);
                branch_spinner = (Spinner)promptView.findViewById(R.id.branch_spinner);
                semester_spinner = (Spinner)promptView.findViewById(R.id.semester_spinner);
                add_lecture_dialog_add_button = (Button)promptView.findViewById(R.id.add_lecture_dialog_add_button);
                add_lecture_dialog_cancel_button = (Button)promptView.findViewById(R.id.add_lecture_dialog_cancel_button);
                String branches[] = {"FREE","BIO-MED","BIO-TECH","CHEM","CIVIL","CSE","ELEX","ET&T","IT","MIN","META"};
                String semesters[] = {"FREE","1st Sem", "2nd Sem", "3rd Sem", "4th Sem", "5th Sem", "6th Sem", "7th Sem", "8th Sem"};
                ArrayAdapter<String> branchAdapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_item, branches);
                branch_spinner.setAdapter(branchAdapter);
                final ArrayAdapter<String> semesterAdapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_item, semesters);
                semester_spinner.setAdapter(semesterAdapter);
                branch_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        branch = branch_spinner.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }

                });

                semester_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override

                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        semester = semester_spinner.getItemAtPosition(position).toString();

                    }

                    @Override

                    public void onNothingSelected(AdapterView<?> parent) {

                    }

                });

                builder.setCancelable(false);
                final AlertDialog alertDialog = builder.create();

                if(timeTableDetailses.size()<8){

                    period_field_dialog.setText(NumberToRoman.convertToRoman(timeTableDetailses.size()+1));
                    alertDialog.show();
                }
                else
                    Toast.makeText(getContext(), "Maximum Periods Limit Reached!!", Toast.LENGTH_SHORT).show();


                add_lecture_dialog_add_button.setOnClickListener(new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {

                        if(branch_spinner.getSelectedItem().toString().equals("FREE")){

                            branch = "FREE";
                            semester = "";
                        }
                        else if(semester_spinner.getSelectedItem().toString().equals("FREE")) {

                            branch = "FREE";
                            semester = "";
                        }

                        timeTableDetails = new TimeTableDetails(timeTableDetailses.size(),branch, semester);
                        timeTableDetailses.add(timeTableDetails);
                        if(dbHandler.addTimetable("Mon",sharedPreferences.getString(Login.MOBILE_NO,""),timeTableDetails) != -1)
                            Toast.makeText(getContext(), "Lecture Added!!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getContext(), "Something Went Wrong!!", Toast.LENGTH_SHORT).show();

                        alertDialog.dismiss();
                        adapter.notifyItemInserted(timeTableDetailses.size());
                    }
                });

                add_lecture_dialog_cancel_button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        alertDialog.cancel();
                    }
                });
            }
        });
    }
}