package com.gr.facultyengagementbook.Admin.FacultyTab;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gr.facultyengagementbook.Model.FacultyDetails;
import com.gr.facultyengagementbook.R;

import java.util.ArrayList;


public class FacultyFragmentAdapter extends RecyclerView.Adapter<FacultyFragmentAdapter.FacultyFragmentViewHolder>{

    private Context context;
    private ArrayList<FacultyDetails> facultyDetails;

    public FacultyFragmentAdapter(Context context, ArrayList<FacultyDetails> facultyDetails) {
        this.context = context;
        this.facultyDetails = facultyDetails;
    }

    @Override
    public FacultyFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_faculty_layout,parent,false);
        FacultyFragmentViewHolder holder = new FacultyFragmentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FacultyFragmentViewHolder holder, int position) {
        holder.vh_faculty.setText(facultyDetails.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return facultyDetails.size();
    }

    class FacultyFragmentViewHolder extends RecyclerView.ViewHolder
    {
        TextView vh_faculty;
        public FacultyFragmentViewHolder(final View itemView) {
            super(itemView);
            vh_faculty = (TextView) itemView.findViewById(R.id.admin_faculty_field);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,AdminFacultyDetails.class);
                    intent.putExtra("faculty_mobileNo",facultyDetails.get(getAdapterPosition()).getMobileNo().toString());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
