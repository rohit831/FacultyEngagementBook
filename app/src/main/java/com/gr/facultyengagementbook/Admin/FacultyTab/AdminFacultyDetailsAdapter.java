package com.gr.facultyengagementbook.Admin.FacultyTab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gr.facultyengagementbook.Model.FacultyRecordDetails;
import com.gr.facultyengagementbook.Model.RecordDetails;
import com.gr.facultyengagementbook.R;

import java.util.ArrayList;

public class AdminFacultyDetailsAdapter extends RecyclerView.Adapter<AdminFacultyDetailsAdapter.AdminFacultyDetailsViewHolder>{

    private ArrayList<RecordDetails> facultyRecordDetails;
    private Context context;

    public AdminFacultyDetailsAdapter(ArrayList<RecordDetails> facultyRecordDetails, Context context) {
        this.facultyRecordDetails = facultyRecordDetails;
        this.context = context;
    }


    @Override
    public AdminFacultyDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_faculty_detail_layout,parent,false);
        AdminFacultyDetailsViewHolder holder = new AdminFacultyDetailsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdminFacultyDetailsViewHolder holder, int position) {

        holder.vh_date_field.setText(facultyRecordDetails.get(position).getDate());
        holder.vh_period_field.setText(String.valueOf(facultyRecordDetails.get(position).getPeriod()));
        holder.vh_assignedto_field.setText(facultyRecordDetails.get(position).getEngagedFacultyName());
        holder.vh_branch_field.setText(facultyRecordDetails.get(position).getBranch());
        holder.vh_sem_field.setText(facultyRecordDetails.get(position).getSemester().substring(0,3));
    }

    @Override
    public int getItemCount() {
        return facultyRecordDetails.size();
    }

    public class AdminFacultyDetailsViewHolder extends RecyclerView.ViewHolder
    {
        TextView vh_date_field, vh_period_field, vh_assignedto_field;
        TextView vh_branch_field,vh_sem_field;
        public AdminFacultyDetailsViewHolder(View itemView) {
            super(itemView);
            vh_date_field = (TextView)itemView.findViewById(R.id.admin_faculty_detail_date_field);
            vh_period_field = (TextView)itemView.findViewById(R.id.admin_faculty_detail_period_field);
            vh_assignedto_field = (TextView)itemView.findViewById(R.id.admin_faculty_detail_assignedto_field);
            vh_branch_field = (TextView) itemView.findViewById(R.id.admin_faculty_detail_branch_field);
            vh_sem_field = (TextView) itemView.findViewById(R.id.admin_faculty_detail_sem_field);
        }
    }
}
