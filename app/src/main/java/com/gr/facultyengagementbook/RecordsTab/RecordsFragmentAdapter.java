package com.gr.facultyengagementbook.RecordsTab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import com.gr.facultyengagementbook.Model.FacultyRecordDetails;
import com.gr.facultyengagementbook.R;
import com.gr.facultyengagementbook.Utilities.NumberToRoman;

public class RecordsFragmentAdapter extends RecyclerView.Adapter<RecordsFragmentAdapter.RecordsFragmentViewHolder> {

    private ArrayList<FacultyRecordDetails> facultyRecordDetailses;

    public RecordsFragmentAdapter(ArrayList<FacultyRecordDetails> facultyRecordDetailses) {
        this.facultyRecordDetailses = facultyRecordDetailses;
    }

    @Override
    public RecordsFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout,parent,false);
        RecordsFragmentViewHolder recordsFragmentViewHolder = new RecordsFragmentViewHolder(view);
        return recordsFragmentViewHolder;
    }

    @Override
    public void onBindViewHolder(RecordsFragmentViewHolder holder, int position) {

        holder.vh_date_field.setText(facultyRecordDetailses.get(getItemCount()-1-position).getDate());
        holder.vh_record_period_field.setText(NumberToRoman.convertToRoman(facultyRecordDetailses.get(getItemCount()-1-position).getPeriod()+1));
        holder.vh_assignedto_field.setText(facultyRecordDetailses.get(getItemCount()-1-position).getEngagedFacultyName());
    }

    @Override
    public int getItemCount() {
        return facultyRecordDetailses.size();
    }

    public class RecordsFragmentViewHolder extends RecyclerView.ViewHolder{

        TextView vh_date_field, vh_record_period_field, vh_assignedto_field;

        public RecordsFragmentViewHolder(View itemView) {
            super(itemView);

            vh_date_field = (TextView)itemView.findViewById(R.id.date_field);
            vh_record_period_field = (TextView)itemView.findViewById(R.id.record_period_field);
            vh_assignedto_field = (TextView)itemView.findViewById(R.id.assignedto_field);
        }
    }
}
