package com.example.dbeintent.ui.report;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dbeintent.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ViewReport extends Fragment {
    ArrayList<String> reportlist;

    public ViewReport() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  v= inflater.inflate(R.layout.fragment_view_report, container, false);
        ImageView image =v.findViewById(R.id.image);
        TextView reportId=(TextView)v.findViewById(R.id.report_id);
        TextView custName=(TextView)v.findViewById(R.id.cust_name);
        TextView reportAdress=(TextView)v.findViewById(R.id.report_address);
        EditText reportDiscription=(EditText)v.findViewById(R.id.reportdiscription);
        EditText report_Mob_Number=(EditText) v.findViewById(R.id.report_mob_number);
        EditText reportGenerationBy=(EditText) v.findViewById(R.id.report_generation);
        EditText reportDate=(EditText) v.findViewById(R.id.report_date);
        EditText billType=(EditText) v.findViewById(R.id.bill_type);
        EditText billAmount=(EditText) v.findViewById(R.id.bill_amount);

        Spinner spinner =(Spinner) v.findViewById(R.id.spinner);
        ProgressBar smp=(ProgressBar)v.findViewById(R.id.progress_circular);
        reportDiscription.setEnabled(false);
        report_Mob_Number.setEnabled(false);
        reportGenerationBy.setEnabled(false);
        reportDate.setEnabled(false);
        billAmount.setEnabled(false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Report")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {


                        reportlist = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : value) {
                            if (doc.get("report_id") != null) {
                                reportlist.add(doc.getString("report_id"));
                            }
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item, reportlist);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);

                    }

                });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                executedb(reportlist.get(i));
            }

            private void executedb(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                DocumentReference docIdRef=db.collection("Report").document(s);
                docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            smp.setVisibility(View.GONE);
                            DocumentSnapshot document = task.getResult();
                            SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
                            reportId.setText(String.valueOf(document.getString("report_id")));
                            custName.setText(String.valueOf(document.getString("cust_name")));
                            reportAdress.setText(String.valueOf(document.getString("report_address")));
                            reportDiscription.setText(String.valueOf(document.getString("report_discription")));
                            report_Mob_Number.setText(String.valueOf(document.getLong("cust_mobile_no")));
                            reportGenerationBy.setText(String.valueOf(document.getString("emp_id")));
                            billType.setText(String.valueOf(document.getString("bill_type")));
                            billAmount.setText(String.valueOf(document.getLong("bill_amount")));
                            reportDate.setText(String.valueOf(sdf.format(document.getDate("report_date"))));
                            String imageUrl = String.valueOf(document.getString("img_url"));
                            imageGallaery(imageUrl);
                        }
                    }

                    private void imageGallaery(String imageUrl) {
                        Glide.with(getContext())
                                .asBitmap()
                                .load(imageUrl)
                                .into(image);
                    }

                });

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });



        return v;

    }


}