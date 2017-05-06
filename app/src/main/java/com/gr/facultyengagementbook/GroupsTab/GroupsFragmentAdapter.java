package com.gr.facultyengagementbook.GroupsTab;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.gr.facultyengagementbook.Model.FacultyDetails;
import com.gr.facultyengagementbook.R;

public class GroupsFragmentAdapter extends RecyclerView.Adapter<GroupsFragmentAdapter.GroupsFragmentViewHolder>{

    private ArrayList<FacultyDetails> facultyDetails;
    private Context context;

    public GroupsFragmentAdapter(ArrayList<FacultyDetails> facultyDetails, Context context) {
        this.facultyDetails = facultyDetails;
        this.context = context;
    }

    @Override
    public GroupsFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_layout,parent,false);
        GroupsFragmentViewHolder holder = new GroupsFragmentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GroupsFragmentViewHolder holder, int position) {

        holder.vh_faculty_name.setText(facultyDetails.get(position).getName());
        holder.vh_faculty_mobileNo.setText(facultyDetails.get(position).getMobileNo());
    }

    @Override
    public int getItemCount() {
        return facultyDetails.size();
    }

    public class GroupsFragmentViewHolder extends RecyclerView.ViewHolder
    {
        TextView vh_faculty_name,vh_faculty_mobileNo;
        public GroupsFragmentViewHolder(View itemView) {
            super(itemView);
            vh_faculty_mobileNo = (TextView) itemView.findViewById(R.id.faculty_mobileNo_field);
            vh_faculty_name = (TextView) itemView.findViewById(R.id.faculty_name_field);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + vh_faculty_mobileNo.getText().toString()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
