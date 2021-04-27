package com.example.dbeintent.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dbeintent.R;
import com.example.dbeintent.ui.RecyclerViewAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;


public class AddTaskEmp extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //DocumentReference notebookRef =db.collection("user").document("y123");
    ProgressBar loadingPB;
    public AdapterTaskList adapter;
    public RecyclerView recyclerView;
    public ArrayList<Note> noteArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_add_task_emp, container, false);
        ImageView image =v.findViewById(R.id.image);
        loadingPB = v.findViewById(R.id.idProgressBar);
        recyclerView=v.findViewById(R.id.recycler_view1);
        String imageUrl = this.getArguments().getString("imgurl");
        Glide.with(getContext())
                .asBitmap()
                .load(imageUrl)
                .into(image);
        noteArrayList=new ArrayList<>();
        //adapter = new AdapterTaskList(options);
       // RecyclerView recyclerView =v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        adapter = new AdapterTaskList(noteArrayList,v.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        db.collection("user").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    loadingPB.setVisibility(View.GONE);
                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d:list){
                        Note c=d.toObject(Note.class);
                        noteArrayList.add(c);
                    }
                    adapter.notifyDataSetChanged();

                } else {
                    // if the snapshot is empty we are displaying a toast message.
                    Toast.makeText(getContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                }
            }
        });



//        db.collection("user")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
//                                        @Nullable FirebaseFirestoreException e) {
//
//                        if (e != null) {
//                         //   Log.w("YourTag", "Listen failed.", e);
//                            Toast.makeText(getContext(), "Listen failed.", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
//                            if (doc.exists()){
//                                loadingPB.setVisibility(View.GONE);
//                                Note message = doc.toObject(Note.class);
//                                noteArrayList.add(message);
//                                adapter.notifyDataSetChanged();
//                            }
//                        }
//                        //Log.d("YourTag", "messageList: " + messageList);
//                        Toast.makeText(getContext()," outerforloop", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
        return v;

    }

}
