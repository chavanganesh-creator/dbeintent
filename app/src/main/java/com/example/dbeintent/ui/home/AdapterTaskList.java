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




//    public AdapterTaskList(@NonNull FirestoreRecyclerOptions<Note> options) {
//            super(options);
//        }
//        @Override
//        protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull Note model) {
//            holder.textViewTitle.setText(model.getTitle());
//            holder.textViewDescription.setText(model.getDescription());
//            holder.textViewPriority.setText(String.valueOf(model.getPriority()));
//        }


    @NonNull
    @Override
    public AdapterTaskList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notes, parent, false));
    }

    @Override
     public void onBindViewHolder(@NonNull AdapterTaskList.ViewHolder holder, int position) {
         holder.textViewTitle.setText(noteArrayList.get(position).getTitle());
         holder.textViewDescription.setText(noteArrayList.get(position).getDescription());

     }

     @Override
     public int getItemCount() {
         return noteArrayList.size();
     }

     class ViewHolder extends RecyclerView.ViewHolder {
         private final TextView textViewTitle;
         private final TextView textViewDescription;

            public ViewHolder(View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.text_view_title);
                textViewDescription = itemView.findViewById(R.id.text_view_description);

            }
        }
    }

