package com.example.dbeintent.ui.report;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dbeintent.R;
import com.example.dbeintent.ui.home.ActiveRunTask;
import com.example.dbeintent.ui.home.AddTaskEmp;
import com.example.dbeintent.ui.home.Attendance;
import com.example.dbeintent.ui.home.Complaint;
import com.example.dbeintent.ui.home.History;

import java.util.ArrayList;

public class ReportNextActivity extends AppCompatActivity {
    private ArrayList<String> mImageUrls = new ArrayList<>();
    FragmentManager fm = getSupportFragmentManager();
    private static final String TAG = "HomeNextActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_next);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        String imageName = getIntent().getStringExtra("image_name");
        Bundle bundle=new Bundle();
        bundle.putString("imgurl", getIntent().getStringExtra("image_url"));
        switch (imageName){
            case "View Report"     :      loadfragment(new ViewReport(),bundle);break;
            case "Create Report"   :      loadfragment(new WriteReport(), bundle);break;
//            case "Attendance"    :      loadfragment(new Attendance(),bundle);break;
//            case "Complaint"     :      loadfragment(new Complaint(),bundle);break;
//            case "History"       :      loadfragment(new History(),bundle);break;
        }

    }


    private void loadfragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        fm.beginTransaction().add(R.id.servefragment,fragment).commit();
    }

}
