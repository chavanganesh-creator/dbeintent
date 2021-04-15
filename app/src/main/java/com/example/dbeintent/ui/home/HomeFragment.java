package com.example.dbeintent.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbeintent.R;
import com.example.dbeintent.ui.RecyclerViewAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private String mNxtHomeActivity="HomeNextActivity.class";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initImageBitmaps();
    }

    private void initImageBitmaps(){

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/dbeintent.appspot.com/o/Panel_image%2FActivited_task.jpg?alt=media&token=6a5412ed-87d8-47cc-ac35-da1aaaca0eac");
        mNames.add("Activited Task");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/dbeintent.appspot.com/o/Panel_image%2FAdd_task.jpg?alt=media&token=7530c9d8-5743-4a32-9c88-23bf13b88e39");
        mNames.add("Add Task");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/dbeintent.appspot.com/o/Panel_image%2FAttendance.jpg?alt=media&token=e3203814-4d4e-440d-a3b7-b3e2d96d10e4");
        mNames.add("Attendance");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/dbeintent.appspot.com/o/Panel_image%2FComplaint.jpg?alt=media&token=686f23dc-eddf-4789-ad9c-f8ad36eaed0a");
        mNames.add("Complaint");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/dbeintent.appspot.com/o/Panel_image%2FHistory.jpg?alt=media&token=788e6bd8-cdfa-409c-aaa2-f0d1593a59d6");
        mNames.add("History");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/dbeintent.appspot.com/o/Panel_image%2Fnumber-pad.png?alt=media&token=834c6dfc-bbeb-4f74-8b53-6581f919d68d");
        mNames.add("Dial Pad");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/dbeintent.appspot.com/o/Panel_image%2FAttendance.jpg?alt=media&token=e3203814-4d4e-440d-a3b7-b3e2d96d10e4");
        mNames.add("Attendance");


        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView =getView().findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext().getApplicationContext(), mNames, mImageUrls,mNxtHomeActivity);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

}