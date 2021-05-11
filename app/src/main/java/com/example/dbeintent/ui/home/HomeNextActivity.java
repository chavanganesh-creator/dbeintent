package com.example.dbeintent.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.dbeintent.R;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class HomeNextActivity extends AppCompatActivity {
    private ArrayList<String> mImageUrls = new ArrayList<>();
    FragmentManager fm = getSupportFragmentManager();
    private static final String TAG = "HomeNextActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_next);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        String imageName = getIntent().getStringExtra("image_name");
        Bundle bundle=new Bundle();
        bundle.putString("imgurl", getIntent().getStringExtra("image_url"));
        switch (imageName){
            case "Activited Task":      loadfragment(new ActiveRunTask(),bundle);break;
            case "Add Task"      :      loadfragment(new AddTaskEmp(), bundle);break;
            case "Attendance"    :      loadfragment(new Attendance(),bundle);break;
            case "Complaint"     :      loadfragment(new Complaint(),bundle);break;
            case "History"       :      loadfragment(new History(),bundle);break;
        }


    }

    private void loadfragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        fm.beginTransaction().add(R.id.servefragment,fragment).commit();
    }


}