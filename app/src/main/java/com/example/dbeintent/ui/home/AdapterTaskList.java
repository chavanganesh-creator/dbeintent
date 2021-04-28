package com.example.dbeintent.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbeintent.R;

import java.util.ArrayList;

public class AdapterTaskList extends RecyclerView.Adapter<AdapterTaskList.ViewHolder> {
    private ArrayList<Note> noteArrayList;
    private Context context;
    public AdapterTaskList(ArrayList<Note> noteArrayList,Context context) {
        this.noteArrayList =  noteArrayList;
        this.context =  context;
    }


    @NonNull
    @Override
    public AdapterTaskList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listtag, parent, false));
    }

    @Override
     public void onBindViewHolder(@NonNull AdapterTaskList.ViewHolder holder, int position) {
        holder.task_id.setText("#"+noteArrayList.get(position).getTask_id());
        holder.cli_name.setText(noteArrayList.get(position).getCli_name());
        holder.task_address.setText(noteArrayList.get(position).getTask_address());
        holder.cli_description.setText(noteArrayList.get(position).getCli_description());

     }

     @Override
     public int getItemCount() {
         return noteArrayList.size();
     }

     class ViewHolder extends RecyclerView.ViewHolder {
         private final TextView task_id;
         private final TextView cli_name;
         private final TextView task_address;
         private final TextView cli_description;

            public ViewHolder(View itemView) {
                super(itemView);
                task_id = itemView.findViewById(R.id.task_id);
                cli_name = itemView.findViewById(R.id.cli_name);
                task_address = itemView.findViewById(R.id.task_address);
                cli_description = itemView.findViewById(R.id.cli_description);
            }
        }
    }

