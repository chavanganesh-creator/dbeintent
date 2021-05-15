package com.example.dbeintent.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dbeintent.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class Complaint extends Fragment {
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    Random rand = new Random();
    public Complaint() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_complaint, container, false);
        ImageView image =v.findViewById(R.id.image);
        EditText taskid=(EditText)v.findViewById(R.id.in_taskid);
        EditText compldiscr=(EditText)v.findViewById(R.id.in_compl_discr);
        EditText complissue_by=(EditText)v.findViewById(R.id.in_compl_issue_by);
        EditText comprefnumber=(EditText)v.findViewById(R.id.in_comp_refnumber);
        EditText compladdr=(EditText)v.findViewById(R.id.in_compl_addr);

        String imageUrl = this.getArguments().getString("imgurl");
        Glide.with(getContext())
                .asBitmap()
                .load(imageUrl)
                .into(image);

        Button btn=(Button)v.findViewById(R.id.submitbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String random_id=serverandom();
                String task_id = taskid.getText().toString();
                String compl_discr = compldiscr.getText().toString();
                String compl_issue_by = complissue_by.getText().toString();
                String comp_refnumber = comprefnumber.getText().toString();
                String compl_addr = compladdr.getText().toString();

                if(taskid.length()==0)
                {
                    taskid.requestFocus();
                    taskid.setError("Field Can't Be Empty");
                }else if(compldiscr.length()==0){
                    compldiscr.requestFocus();
                    compldiscr.setError("Field Can't Be Empty");
                }else if(complissue_by.length()==0){
                    complissue_by.requestFocus();
                    complissue_by.setError("Field Can't Be Empty");
                }else if(comprefnumber.length()==0){
                    comprefnumber.requestFocus();
                    comprefnumber.setError("Field Can't Be Empty");
                }else if(compladdr.length()==0){
                    compladdr.requestFocus();
                    compladdr.setError("Field Can't Be Empty");
                }else {
                    Map<String, Object> complaint = new HashMap<>();
                    complaint.put("Complaint_id", random_id);
                    complaint.put("Task_Id", task_id);
                    complaint.put("Complaint_discription", compl_discr);
                    complaint.put("Issue_by", compl_issue_by);
                    complaint.put("Complaint_refno", comp_refnumber);
                    complaint.put("Complaint_address", compl_addr);
                    complaint.put("Date_OF_Creation", new Timestamp(new Date()));
                    db.collection("ComplaintBox")
                            .add(complaint)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                }

            }
        });

        return v;
    }

    private String serverandom() {
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String alphaNumeric = lowerAlphabet + numbers;
        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        int length = 5;

        for (int i = 0; i < length; i++) {

            int index = random.nextInt(alphaNumeric.length());

            char randomChar = alphaNumeric.charAt(index);

            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
    }
}