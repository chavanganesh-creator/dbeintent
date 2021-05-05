package com.example.dbeintent.ui.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.dbeintent.ui.home.classes.Note;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;


public class AddTaskEmp extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressBar loadingPB;
    public AdapterTaskList adapter;
    public RecyclerView recyclerView;
    public ArrayList<Note> noteArrayList;
    private int cnt=0;

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
        recyclerView.setHasFixedSize(true);
        adapter = new AdapterTaskList(noteArrayList,v.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        db.collection("Task")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {

                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        ArrayList<Note> newArrayList=new ArrayList<>();
                        if (e != null) {
                            Toast.makeText(getContext(), "Listen failed.", Toast.LENGTH_SHORT).show();
                            return;
                        }
//                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
//                            //Log.d(TAG, document.getId() + " => " + document.getData());
//                            Toast.makeText(getContext(), document.getId(), Toast.LENGTH_SHORT).show();
//                            break;
//
//                        }

                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            Note message = doc.toObject(Note.class);
                            Set<Note> set = new LinkedHashSet<>();

                            if (doc.exists()) {
                                loadingPB.setVisibility(View.GONE);
                                newArrayList.add(message);
                                set.addAll(newArrayList);
                                noteArrayList.clear();
                                noteArrayList.addAll(set);
                            }
                        }
                        adapter.notifyDataSetChanged();


                    }
                });
        return v;

    }

}
