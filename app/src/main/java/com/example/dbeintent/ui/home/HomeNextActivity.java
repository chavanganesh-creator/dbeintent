package com.example.dbeintent.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.dbeintent.R;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class HomeNextActivity extends AppCompatActivity {

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

        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String imageUrl = getIntent().getStringExtra("image_url");

        }

        FragmentManager fm = getSupportFragmentManager();
        ActiveRunTask fm2 = new ActiveRunTask();
        fm.beginTransaction().add(R.id.servefragment, fm2).commit();

    }

}