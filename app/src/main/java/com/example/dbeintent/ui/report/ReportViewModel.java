package com.example.dbeintent.ui.report;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.ContentValues.TAG;

public class ReportViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReportViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is Report View model fragment!!");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docIdRef=db.collection("Employee").document("chavan@21312");
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    mText.setValue("Data follows as" + document.getData());
                }
            }
        });
    }


    public LiveData<String> getText() {
        return mText;
    }
}