package com.gr.facultyengagementbook.Admin.DateTab;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gr.facultyengagementbook.R;

public class MonthDetailsAdapter extends RecyclerView.Adapter <MonthDetailsAdapter.MonthDetailViewHolder>{

    private int noOfDates;
    private Context context;
    private String month;
    private String year;

    public MonthDetailsAdapter(int noOfDates, Context context, String month, String year) {
        this.noOfDates = noOfDates;
        this.context = context;
        this.month = month;
        this.year = year;
    }

    @Override
    public MonthDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_month_detail_date,parent,false);
        MonthDetailViewHolder holder = new MonthDetailViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MonthDetailViewHolder holder, int position) {
        holder.vh_date.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return noOfDates;
    }

    public class MonthDetailViewHolder extends RecyclerView.ViewHolder
    {
        TextView vh_date;
        public MonthDetailViewHolder(View itemView) {
            super(itemView);
            vh_date = (TextView)itemView.findViewById(R.id.month_detail_date_field);


            vh_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DateDetails.class);
                    intent.putExtra("Date",vh_date.getText().toString());
                    intent.putExtra("Month",month);
                    intent.putExtra("Year",year);
                    context.startActivity(intent);
                }
            });
        }
    }
}
