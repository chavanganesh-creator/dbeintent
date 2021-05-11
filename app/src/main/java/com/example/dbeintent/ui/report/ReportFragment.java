package com.example.dbeintent.ui.report;

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


public class ReportFragment extends Fragment {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private String mNxtReportActivity="ReportNextActivity.class";

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
        mNames.add("View Report");

//        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/dbeintent.appspot.com/o/Panel_image%2FAdd_task.jpg?alt=media&token=7530c9d8-5743-4a32-9c88-23bf13b88e39");
//        mNames.add("Create Report");
//
//        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/dbeintent.appspot.com/o/Panel_image%2FAttendance.jpg?alt=media&token=e3203814-4d4e-440d-a3b7-b3e2d96d10e4");
//        mNames.add("Pending Report");



        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView =getView().findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext().getApplicationContext(), mNames, mImageUrls, mNxtReportActivity);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }
}