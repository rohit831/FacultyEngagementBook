package com.gr.facultyengagementbook.Admin.DateTab;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gr.facultyengagementbook.Model.RecordDetails;
import com.gr.facultyengagementbook.R;
import com.gr.facultyengagementbook.Utilities.NumberToRoman;

import java.util.ArrayList;

public class DateDetailsAdapter extends RecyclerView.Adapter<DateDetailsAdapter.DateDetailsViewHolder>
{
    ArrayList<RecordDetails> recordDetails;

    public DateDetailsAdapter(ArrayList<RecordDetails> recordDetails) {
        this.recordDetails = recordDetails;
    }

    @Override
    public DateDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_date_detaii_layout,parent,false);
        DateDetailsViewHolder holder = new DateDetailsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DateDetailsViewHolder holder, int position) {

        holder.vh_branch.setText(recordDetails.get(position).getBranch());
        holder.vh_sem.setText(recordDetails.get(position).getSemester());
        holder.vh_period.setText(NumberToRoman.convertToRoman(recordDetails.get(position).getPeriod()));
        holder.vh_assigned_to.setText(recordDetails.get(position).getEngagedFacultyName());
        holder.vh_incharge.setText(recordDetails.get(position).getFacultyName());
    }

    @Override
    public int getItemCount() {
        return recordDetails.size();
    }

    public class DateDetailsViewHolder extends RecyclerView.ViewHolder
    {
        TextView vh_incharge,vh_assigned_to,vh_period;
        TextView vh_branch,vh_sem;
        public DateDetailsViewHolder(View itemView) {
            super(itemView);
            vh_incharge = (TextView) itemView.findViewById(R.id.admin_date_detail_incharge_field);
            vh_assigned_to= (TextView) itemView.findViewById(R.id.admin_date_detail_assigned_to_field);
            vh_period = (TextView) itemView.findViewById(R.id.admin_date_detail_period_field);
            vh_branch = (TextView) itemView.findViewById(R.id.admin_date_detail_branch_field);
            vh_sem = (TextView) itemView.findViewById(R.id.admin_date_detail_sem_field);
        }
    }
}
