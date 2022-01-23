package com.hadioz.inventorycontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {

    private ArrayList<Log> logs;
    private Context context;

    public LogAdapter(ArrayList<Log> data, Context context) {
        this.logs = data;
        this.context = context;

    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.log_item, parent, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        holder.admin.setText(logs.get(position).getAdmin());
        holder.date.setText(logs.get(position).getDate());
        holder.count.setText(Integer.toString(logs.get(position).getAmount()));

    }

    @Override
    public int getItemCount() {
        return (logs != null) ? logs.size(): 0;
    }

    public class LogViewHolder extends RecyclerView.ViewHolder {
        private TextView admin, date, count;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            admin = (TextView) itemView.findViewById(R.id.tv_admin_log);
            date = (TextView) itemView.findViewById(R.id.tv_date_log);
            count = (TextView) itemView.findViewById(R.id.tv_quantity_log);

        }
    }
}
