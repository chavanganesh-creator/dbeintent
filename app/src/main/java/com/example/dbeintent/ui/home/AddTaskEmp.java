package com.example.dbeintent.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dbeintent.R;

public class AddTaskEmp extends Fragment {


    public AddTaskEmp() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_add_task_emp, container, false);
        ImageView image =v.findViewById(R.id.image);
        String imageUrl = this.getArguments().getString("imgurl");
        Glide.with(getContext())
                .asBitmap()
                .load(imageUrl)
                .into(image);

        return v;

    }

}