package com.example.dbeintent.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbeintent.R;
import com.google.firebase.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AttendanceRecycler extends RecyclerView.Adapter<AttendanceRecycler.ViewHolder> {
    private ArrayList<Date> noteArrayList;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("dd");
    SimpleDateFormat sdfday = new SimpleDateFormat("E");
    SimpleDateFormat sdfinout=new SimpleDateFormat("hh:mm");

    public AttendanceRecycler(ArrayList<Date> noteArrayList, Context context) {
        this.noteArrayList = noteArrayList;
        this.context =  context;
    }

    @NonNull
    @Override
    public AttendanceRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new  AttendanceRecycler.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_attendance_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.atten_date.setText(String.valueOf(sdf.format(noteArrayList.get(position))));
        holder.atten_day.setText(String.valueOf(sdfday.format(noteArrayList.get(position))));
        holder.indate.setText(String.valueOf(sdfinout.format(noteArrayList.get(position))));
        holder.outdate.setText(String.valueOf(sdfinout.format(noteArrayList.get(position))));

    }



    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView atten_date;
        private final TextView atten_day;
        private final TextView indate;
        private final TextView outdate;

        public ViewHolder(View itemView) {
            super(itemView);
            atten_date = itemView.findViewById(R.id.atten_day);
            atten_day=itemView.findViewById(R.id.atten_day);
            indate = itemView.findViewById(R.id.indate);
            outdate=itemView.findViewById(R.id.outdate);

        }
    }
}
