package com.example.dbeintent.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dbeintent.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class ActiveRunTask extends Fragment {
    public ActiveRunTask() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_active_run_task, container, false);
        EditText taskid=(EditText)v.findViewById(R.id.taskid);
        EditText taskdiscription=(EditText)v.findViewById(R.id.taskdiscription);
        EditText taskaddress=(EditText)v.findViewById(R.id.task_Address);
        EditText task_refnumber=(EditText)v.findViewById(R.id.task_refnumber);
        EditText task_generation=(EditText)v.findViewById(R.id.task_generation);
        EditText task_date=(EditText)v.findViewById(R.id.task_date);
        TextView minheader=(TextView) v.findViewById(R.id.minheader);
        taskid.setEnabled(false);
        taskdiscription.setEnabled(false);
        taskaddress.setEnabled(false);
        task_refnumber.setEnabled(false);
        task_generation.setEnabled(false);
        task_date.setEnabled(false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docIdRef=db.collection("Task").document("y123");
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
                        minheader.setText(String.valueOf(document.getString("cli_id")));
                        taskid.setText(String.valueOf(document.getString("cli_id")));
                        taskdiscription.setText(String.valueOf(document.getString("cli_description")));
                        taskaddress.setText(String.valueOf(document.getString("task_address")));
                        task_refnumber.setText(String.valueOf(document.getLong("task_refferalcode")));
                        task_generation.setText(String.valueOf(document.getString("cli_name")));
                        task_date.setText(String.valueOf(sdf.format(document.getDate("task_genrateddate"))));
                }
            }
        });
        return v;
    }
}