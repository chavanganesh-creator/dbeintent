package com.example.dbeintent.ui.user;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public UserViewModel() {
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