package com.gr.facultyengagementbook.TimetableTab;

import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.TextView;

import com.gr.facultyengagementbook.Model.TimeTableDetails;
import com.gr.facultyengagementbook.R;
import com.gr.facultyengagementbook.TimetableTab.AvailableFaculty.AvailableFaculty;
import com.gr.facultyengagementbook.Utilities.NumberToRoman;

import java.util.ArrayList;


public class TimetableFragmentAdapter extends RecyclerView.Adapter<TimetableFragmentAdapter.TimetableFragmentViewHolder> {

    private Context context;

    private ArrayList<TimeTableDetails> timeTableDetailses;

    private ArrayList<String > assignedFaclties;

    public TimetableFragmentAdapter(Context context, ArrayList<TimeTableDetails> timeTableDetailses, ArrayList<String> assignedFaclties)
    {

        this.context = context;
        this.timeTableDetailses = timeTableDetailses;
        this.assignedFaclties = assignedFaclties;
    }

    @Override
    public TimetableFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.period_layout,parent,false);
        TimetableFragmentViewHolder holder = new TimetableFragmentViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(TimetableFragmentViewHolder holder, int position) {

        holder.vh_period.setText(NumberToRoman.convertToRoman(position+1));
        holder.vh_branch.setText(timeTableDetailses.get(position).getBranch());
        holder.vh_sem.setText(timeTableDetailses.get(position).getSemester());

        if(assignedFaclties.get(position).equals("No Faculty"))
            holder.vh_assigned.setVisibility(View.GONE);
        else
            holder.vh_assigned.setText(assignedFaclties.get(position));

        /*int i = 0;

        while (i<assignedPeriods.size()) {

            if (assignedPeriods.get(i).getPeriod().equals("" + (position+1)))

                holder.vh_assigned.setText(assignedPeriods.get(i).getEngagedFacultyName());

            else

                i++;

        }

        if(i==assignedPeriods.size())

            holder.vh_assigned.setVisibility(View.GONE);

    */

    }

    @Override
    public int getItemCount() {

        return timeTableDetailses.size();
    }

    public class TimetableFragmentViewHolder extends RecyclerView.ViewHolder

    {
        TextView  vh_period, vh_branch , vh_sem ,vh_assigned;

        public TimetableFragmentViewHolder(View itemView) {

            super(itemView);

            vh_period = (TextView) itemView.findViewById(R.id.period_field);
            vh_branch = (TextView) itemView.findViewById(R.id.branch_field);
            vh_sem = (TextView) itemView.findViewById(R.id.semester_field);
            vh_assigned = (TextView) itemView.findViewById(R.id.assigned_field);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (timeTableDetailses.get(getAdapterPosition()).getBranch().equals("FREE")) {
                    }
                    else {

                        Intent intent = new Intent(context, AvailableFaculty.class);
                        intent.putExtra("Period", timeTableDetailses.get(getAdapterPosition()).getPeriod());
                        intent.putExtra("Branch", timeTableDetailses.get(getAdapterPosition()).getBranch());
                        intent.putExtra("Semester", timeTableDetailses.get(getAdapterPosition()).getSemester());
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}

