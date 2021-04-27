package com.example.dbeintent.ui;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dbeintent.R;
import com.example.dbeintent.ui.home.HomeNextActivity;
import com.example.dbeintent.ui.report.ReportNextActivity;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;
    private String mNxtActivity;
    public RecyclerViewAdapter(Context context, ArrayList<String> imageNames, ArrayList<String> images, String nxthoneactivity) {
        mImageNames = imageNames;
        mImages = images;
        mContext = context;
        mNxtActivity=nxthoneactivity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.imageName.setText(mImageNames.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));

                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
                if(mNxtActivity=="HomeNextActivity.class") {
                    Intent homenxt = new Intent(view.getContext(), HomeNextActivity.class);
                    homenxt.putExtra("image_url", mImages.get(position));
                    homenxt.putExtra("image_name", mImageNames.get(position));
                    view.getContext().startActivity(homenxt);
                }
                if(mNxtActivity=="ReportNextActivity.class") {
                    Intent homenxt = new Intent(view.getContext(), ReportNextActivity.class);
                    homenxt.putExtra("image_url", mImages.get(position));
                    homenxt.putExtra("image_name", mImageNames.get(position));
                    view.getContext().startActivity(homenxt);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView imageName;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}