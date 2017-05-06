package com.gr.facultyengagementbook.TimetableTab.AvailableFaculty;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gr.facultyengagementbook.Authentication.Login;
import com.gr.facultyengagementbook.HomeScreen.HomeScreen;
import com.gr.facultyengagementbook.Model.AvailableFacultyDetails;
import com.gr.facultyengagementbook.Model.DBHandler;
import com.gr.facultyengagementbook.Model.RecordDetails;
import com.gr.facultyengagementbook.Model.TimeTableDetails;
import com.gr.facultyengagementbook.R;
import com.gr.facultyengagementbook.TimetableTab.TimetableFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AvailableFacultyRecyclerAdapter extends RecyclerView.Adapter<AvailableFacultyRecyclerAdapter.AvailableFacultyViewHolder>{

    ArrayList<AvailableFacultyDetails> availableFacultyDetails;
    Context context;
    TimeTableDetails timeTableDetails;
    DBHandler dbHandler;
    SharedPreferences sharedPreferences;

    public AvailableFacultyRecyclerAdapter(Context context, ArrayList<AvailableFacultyDetails> availableFacultyDetails, TimeTableDetails timeTableDetails) {
        this.availableFacultyDetails = availableFacultyDetails;
        this.context = context;
        this.timeTableDetails = timeTableDetails;
    }

    @Override
    public AvailableFacultyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.available_faculty_layout,parent,false);
        AvailableFacultyViewHolder holder = new AvailableFacultyViewHolder(view);
        dbHandler = new DBHandler(context,null,null,1);
        sharedPreferences = context.getSharedPreferences(Login.PREFS,Context.MODE_PRIVATE);
        return holder;
    }

    @Override
    public void onBindViewHolder(AvailableFacultyViewHolder holder, int position) {
        holder.vh_faculty_name.setText(availableFacultyDetails.get(position).getAvailableFacultyName());
    }

    @Override
    public int getItemCount() {
        return availableFacultyDetails.size();
    }

    public class AvailableFacultyViewHolder extends RecyclerView.ViewHolder
    {
        TextView vh_faculty_name;
        Button vh_assign;
        public AvailableFacultyViewHolder(final View itemView) {
            super(itemView);

            vh_faculty_name = (TextView) itemView.findViewById(R.id.avail_faculty_name_field);
            vh_assign = (Button) itemView.findViewById(R.id.assign_button);

            vh_assign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View customView = inflater.inflate(R.layout.confirm_assign_dialog,null);
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(customView);
                    builder.setCancelable(false);

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            RecordDetails recordDetails = new RecordDetails(sharedPreferences.getString(Login.MOBILE_NO, ""),
                                    availableFacultyDetails.get(getAdapterPosition()).getAvailableFacultyMobileNo(),
                                    new SimpleDateFormat("dd-MMM-yy").format(new Date()), dbHandler.getFacultyName(sharedPreferences.getString(Login.MOBILE_NO, "")),
                                    timeTableDetails.getBranch(), timeTableDetails.getSemester(), timeTableDetails.getPeriod(),
                                    availableFacultyDetails.get(getAdapterPosition()).getAvailableFacultyName());

                            if (dbHandler.isRecordPresent(sharedPreferences.getString(Login.MOBILE_NO, ""), new SimpleDateFormat("dd-MMM-yy").format(new Date()),
                                    timeTableDetails.getPeriod())) {

                                if (dbHandler.updateExistingRecord(sharedPreferences.getString(Login.MOBILE_NO, ""), new SimpleDateFormat("dd-MMM-yy").format(new Date()),
                                        timeTableDetails.getPeriod(), recordDetails) != -1)
                                    context.startActivity(new Intent(context, HomeScreen.class));
                            } else {

                                if (dbHandler.addNewRecord(recordDetails) != -1)
                                    context.startActivity(new Intent(context, HomeScreen.class));
                            }
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();


                }
            });

        }
    }
}
