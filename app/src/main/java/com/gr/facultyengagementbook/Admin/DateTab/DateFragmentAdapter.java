package com.gr.facultyengagementbook.Admin.DateTab;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gr.facultyengagementbook.R;

import java.util.ArrayList;


public class DateFragmentAdapter extends RecyclerView.Adapter<DateFragmentAdapter.DateFragmentViewHolder> {

    private ArrayList<String> monthNames;
    private Context context;

    public DateFragmentAdapter(ArrayList<String> monthNames, Context context) {
        this.monthNames = monthNames;
        this.context = context;
    }

    @Override
    public DateFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_date_layout,parent,false);
        DateFragmentViewHolder holder = new DateFragmentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DateFragmentViewHolder holder, int position) {
        holder.vh_monthName.setText(monthNames.get(position));
    }

    @Override
    public int getItemCount() {
        return monthNames.size();
    }

    public class DateFragmentViewHolder  extends RecyclerView.ViewHolder
    {
        TextView vh_monthName;
        public DateFragmentViewHolder(final View itemView) {
            super(itemView);
            vh_monthName = (TextView)itemView.findViewById(R.id.admin_date_field);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,MonthDetails.class);
                    intent.putExtra("Month",vh_monthName.getText().toString());
                    intent.putExtra("Year",vh_monthName.getText().toString()
                                    .substring(vh_monthName.length()-2));
                   itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
