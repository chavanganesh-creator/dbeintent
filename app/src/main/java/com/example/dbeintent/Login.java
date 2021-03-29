package com.example.dbeintent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class Login extends Fragment {
    private EditText email,pswk,inputdoc;
    private Button login;
    private static final String TAG = "MainActivity";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_login, container, false);
       email=(EditText)v.findViewById(R.id.email);
       pswk=(EditText)v.findViewById(R.id.pswk);
       login=(Button)v.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String emilstr = email.getText().toString();
               String pswkstr = pswk.getText().toString();
               //Toast.makeText(getActivity(), pswkstr, Toast.LENGTH_SHORT).show();
               if(emilstr.length()==0)
               {
                   email.requestFocus();
                   email.setError("Field Can't Be Emety");
               }
               else if(!emilstr.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})+$"))
               {
                   email.requestFocus();
                   email.setError("Enter Only Alphabetical Charachter");
               }
               else if(pswkstr.length()==0)
               {
                   pswk.requestFocus();
                   pswk.setError("Field Cant't Be Emety");
               }
               else
               {
                   FirebaseFirestore db = FirebaseFirestore.getInstance();
                   DocumentReference docIdRef=db.collection("Employee").document(emilstr);
                   docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                       @Override
                       public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                           if (task.isSuccessful()) {
                               DocumentSnapshot document = task.getResult();
                               String st= String.valueOf(document.getString("pswkd"));
                               if (pswkstr.equals(st)) {
                                   SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                   SharedPreferences.Editor editor = pref.edit();
                                   editor.putString("username",emilstr);
                                   editor.commit();
                                   Toast.makeText(getActivity(), "Login Sucessfull", Toast.LENGTH_LONG).show();
                                       Intent nxtscreen = new Intent(getContext(), nextstep1.class);
                                       startActivity(nxtscreen);
                                       getActivity().finish();
                               }
                               else Toast.makeText(getActivity(),"Login Unsucessfull",Toast.LENGTH_LONG).show();
                           }
                       }
                   });
               }
           }
       });
        
        return v;

    }
}

