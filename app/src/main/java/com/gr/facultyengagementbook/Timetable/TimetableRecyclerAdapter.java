package com.gr.facultyengagementbook.Timetable;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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

import com.gr.facultyengagementbook.Authentication.Login;
import com.gr.facultyengagementbook.Model.DBHandler;
import com.gr.facultyengagementbook.Model.TimeTableDetails;
import com.gr.facultyengagementbook.R;
import com.gr.facultyengagementbook.Utilities.NumberToRoman;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by gaurav on 12/4/17.
 */

public class TimetableRecyclerAdapter extends RecyclerView.Adapter<TimetableRecyclerAdapter.TimetableRecyclerViewHolder> {

    Context context;
    ArrayList<TimeTableDetails> timeTableDetailses;
    String day;
    DBHandler dbHandler;
    SharedPreferences sharedPreferences;

    private TextView period_field_dialog;
    private Spinner branch_spinner, semester_spinner;
    private Button add_lecture_dialog_edit_button, add_lecture_dialog_cancel_button;
    private String branch, semester;
    private TimeTableDetails timeTableDetails;

    public TimetableRecyclerAdapter(Context context, ArrayList<TimeTableDetails> timeTableDetailses, String day) {
        this.timeTableDetailses = timeTableDetailses;
        this.context = context;
        this.day = day;
    }

    @Override
    public TimetableRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dbHandler = new DBHandler(context,null,null,1);
        sharedPreferences = context.getSharedPreferences(Login.PREFS,Context.MODE_PRIVATE);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecture_layout,parent,false);
        TimetableRecyclerViewHolder timetableRecyclerViewHolder = new TimetableRecyclerViewHolder(view);
        return timetableRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(TimetableRecyclerViewHolder holder, int position) {

        holder.vh_period_field.setText(NumberToRoman.convertToRoman(position+1));
        holder.vh_branch_field.setText(timeTableDetailses.get(position).getBranch());
        holder.vh_semester_field.setText(timeTableDetailses.get(position).getSemester());
    }

    @Override
    public int getItemCount() {
        return timeTableDetailses.size();
    }

    public class TimetableRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView vh_period_field, vh_branch_field, vh_semester_field;

        public TimetableRecyclerViewHolder(View itemView) {
            super(itemView);

            vh_period_field = (TextView)itemView.findViewById(R.id.period_field);
            vh_branch_field = (TextView)itemView.findViewById(R.id.branch_field);
            vh_semester_field = (TextView)itemView.findViewById(R.id.semester_field);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    View promptView = layoutInflater.inflate(R.layout.add_lecture_dialog,null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(promptView);
                    period_field_dialog = (TextView)promptView.findViewById(R.id.period_field_dialog);
                    branch_spinner = (Spinner)promptView.findViewById(R.id.branch_spinner);
                    semester_spinner = (Spinner)promptView.findViewById(R.id.semester_spinner);
                    add_lecture_dialog_edit_button = (Button)promptView.findViewById(R.id.add_lecture_dialog_add_button);
                    add_lecture_dialog_cancel_button = (Button)promptView.findViewById(R.id.add_lecture_dialog_cancel_button);
                    add_lecture_dialog_edit_button.setText("EDIT");
                    String branches[] = {"FREE","BIO-MED","BIO-TECH","CHEM","CIVIL","CSE","ELEX","ET&T","IT","MIN","META"};
                    String semesters[] = {"FREE","1st Sem", "2nd Sem", "3rd Sem", "4th Sem", "5th Sem", "6th Sem", "7th Sem", "8th Sem"};
                    ArrayAdapter<String> branchAdapter = new ArrayAdapter<String>(context,R.layout.spinner_item, branches);
                    branch_spinner.setAdapter(branchAdapter);
                    final ArrayAdapter<String> semesterAdapter = new ArrayAdapter<String>(context,R.layout.spinner_item, semesters);
                    semester_spinner.setAdapter(semesterAdapter);

                    int branch_positon , semester_positon;
                    for(branch_positon = 0; branch_positon < branches.length; branch_positon++){
                        if(branch_spinner.getItemAtPosition(branch_positon).equals(timeTableDetailses.get(
                                getAdapterPosition()).getBranch()))
                            break;
                    }

                    for(semester_positon = 0; semester_positon < semesters.length; semester_positon++){
                        if(semester_spinner.getItemAtPosition(semester_positon).equals(timeTableDetailses.get(
                                getAdapterPosition()).getSemester()))
                            break;
                        if(timeTableDetailses.get(getAdapterPosition()).getSemester().equals("")) {
                            semester_positon = 0;
                            break;
                        }
                    }

                    branch_spinner.setSelection(branch_positon);
                    semester_spinner.setSelection(semester_positon);

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

                        period_field_dialog.setText(NumberToRoman.convertToRoman(getAdapterPosition()+1));
                        alertDialog.show();
                    }
                    else
                        Toast.makeText(context, "Maximum Periods Limit Reached!!", Toast.LENGTH_SHORT).show();


                    add_lecture_dialog_edit_button.setOnClickListener(new View.OnClickListener() {

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

                            timeTableDetails = new TimeTableDetails(getAdapterPosition(),branch, semester);
                            timeTableDetailses.set(getAdapterPosition(),timeTableDetails);
                            if(dbHandler.editTimetable(day,sharedPreferences.getString(Login.MOBILE_NO,""),timeTableDetails) != -1)
                                Toast.makeText(context, "Lecture Updated!!", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(context, "Something Went Wrong!!", Toast.LENGTH_SHORT).show();

                            alertDialog.dismiss();
                            notifyDataSetChanged();
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

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to delete??");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dbHandler.deleteTimetable(day, sharedPreferences.getString(Login.MOBILE_NO,""),getAdapterPosition());
                            dbHandler.rearrangeLectures(day, sharedPreferences.getString(Login.MOBILE_NO,""),getAdapterPosition());
                            timeTableDetailses.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    return true;
                }
            });
        }
    }
}
