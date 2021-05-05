package com.example.dbeintent.ui.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dbeintent.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Attendance extends Fragment {
    public AttendanceRecycler attenAdapter;
    public RecyclerView recyclerView;


    public ArrayList<Date> noteArrayList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public Attendance() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment_attendance, container, false);



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docIdRef=db.collection("Employee").document("chavan@21312");
        docIdRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    ArrayList<Timestamp> arrList = new ArrayList<>();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    arrList = (ArrayList) documentSnapshot.get("emp_attendance");

//                    StringBuffer sb = new StringBuffer();
                    for (Timestamp s : arrList) {
                        Date dataDate = s.toDate();
                        noteArrayList.add(dataDate);
                    }
//                    String str = sb.toString();

                }
            }
        });
        recyclerView=view.findViewById(R.id.atten_recycler );
        noteArrayList =new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        attenAdapter = new AttendanceRecycler(noteArrayList,view.getContext());
        recyclerView.setAdapter(attenAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
        }
}