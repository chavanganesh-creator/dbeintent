package com.example.dbeintent.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbeintent.R;
import com.example.dbeintent.ui.home.classes.Note;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AdapterTaskList extends RecyclerView.Adapter<AdapterTaskList.ViewHolder> {
    FirebaseFirestore db=FirebaseFirestore.getInstance();
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
        holder.task_addbucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> docData = new HashMap<>();
                docData.put("emp_pend_id", FieldValue.arrayUnion(noteArrayList.get(position).getTask_id()));
                db.collection("Employee").document("chavan@21312")
                         .update(docData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(context, "Successfully Added" , Toast.LENGTH_SHORT).show();
                            }

                        });
            }
        });
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
         private final Button task_addbucket;
            public ViewHolder(View itemView) {
                super(itemView);
                task_id = itemView.findViewById(R.id.task_id);
                cli_name = itemView.findViewById(R.id.cli_name);
                task_address = itemView.findViewById(R.id.task_address);
                cli_description = itemView.findViewById(R.id.cli_description);
                task_addbucket=itemView.findViewById(R.id.task_addbucket);
            }
        }
    }

