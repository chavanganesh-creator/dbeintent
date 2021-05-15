package com.example.dbeintent.ui.report;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.OnProgressListener;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class WriteReport extends Fragment {
    ArrayList<String> reportlist;
    int flag=0;
    private Uri filePath;
    private static final int CAMERA_REQUEST=1;
    ImageView imageView;
    String ImageUrllink;
    Bitmap photo;
    private Uri mImageUri = null;
    StorageReference mStorage=FirebaseStorage.getInstance().getReference();
    String _imgName;
    public WriteReport() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_write_report, container, false);
        ImageView image =v.findViewById(R.id.image);
        Spinner spinner =(Spinner) v.findViewById(R.id.spinner);
        ProgressBar smp=(ProgressBar)v.findViewById(R.id.progress_circular);
        EditText custName=(EditText) v.findViewById(R.id.cust_name);
        EditText reportAddress=(EditText) v.findViewById(R.id.report_address);
        EditText reportDiscription=(EditText)v.findViewById(R.id.reportdiscription);
        EditText report_Mob_Number=(EditText) v.findViewById(R.id.report_mob_number);
        EditText reportGenerationBy=(EditText) v.findViewById(R.id.report_generation);
        EditText reportDate=(EditText) v.findViewById(R.id.report_date);
        EditText billType=(EditText) v.findViewById(R.id.bill_type);
        EditText billAmount=(EditText) v.findViewById(R.id.bill_amount);
        Button saveReport=(Button)v.findViewById(R.id.submitbtn);
        imageView=(ImageView)v.findViewById(R.id.imgview);
        Button imgbtn=(Button)v.findViewById(R.id.submit_img);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String imageUrl = this.getArguments().getString("imgurl");
        Glide.with(getContext())
                .asBitmap()
                .load(imageUrl)
                .into(image);

        DocumentReference docIdRef=db.collection("Employee").document("chavan@21312");
        docIdRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {

                if (snapshot != null && snapshot.exists()) {
                    // Log.d(TAG, "Current data: " + snapshot.getData());
                     reportlist = new ArrayList<>();
                    reportlist = (ArrayList) snapshot.get("emp_pend_id");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item, reportlist);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                executedb(reportlist.get(i));
                smp.setVisibility(View.GONE);
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }

            private void executedb(String s) {
                saveReport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String cust_name = custName.getText().toString();
                        String report_address = reportAddress.getText().toString();
                        String report_discription = reportDiscription.getText().toString();
                        Number report_mob_number = Long.parseLong(report_Mob_Number.getText().toString());
                        String report_generation_by = reportGenerationBy.getText().toString();
                        String bill_type = billType.getText().toString();
                        Number bill_amount =Long.parseLong(billAmount.getText().toString());
                        if (custName.length()==0){
                            custName.requestFocus();
                            custName.setError("Field Can't Be Empty");
                        }else if (reportAddress.length()==0){
                            reportAddress.requestFocus();
                            reportAddress.setError("Field Can't Be Empty");
                        }else if (reportDiscription.length()==0){
                            reportDiscription.requestFocus();
                            reportDiscription.setError("Field Can't Be Empty");
                        }else if (report_Mob_Number.length()==0){
                            report_Mob_Number.requestFocus();
                            report_Mob_Number.setError("Field Can't Be Empty");
                        }else if (reportGenerationBy.length()==0){
                            reportGenerationBy.requestFocus();
                            reportGenerationBy.setError("Field Can't Be Empty");
                        }else if (billType.length()==0){
                            billType.requestFocus();
                            billType.setError("Field Can't Be Empty");
                        }else if (billAmount.length()==0){
                            billAmount.requestFocus();
                            billAmount.setError("Field Can't Be Empty");
                        }else {

                            Map<String, Object> write_report = new HashMap<>();
                            write_report.put("cust_name",cust_name);
                            write_report.put("report_address",report_address);
                            write_report.put("report_discription",report_discription);
                            write_report.put("cust_mobile_no",report_mob_number);
                            write_report.put("emp_id",report_generation_by);
                            write_report.put("bill_type",bill_type);
                            write_report.put("report_date",new Timestamp(new Date()));
                            write_report.put("bill_amount",bill_amount);
                            write_report.put("report_id",s);
                            write_report.put("img_url",ImageUrllink);
                            DocumentReference docIdRef=db.collection("Report").document(s);
                            docIdRef.set(write_report).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                                    DocumentReference docIdRef1=db.collection("Employee").document("chavan@21312");

                                    docIdRef1.update("emp_pend_id",FieldValue.arrayRemove(s));
                                }
                            });
                        }
                    }
                });
            }
        });
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

            }
        });


        return v;

    }

    private void saveUploadimage() {
        final String _imgName=System.currentTimeMillis()/1000+"_img.jpg";

        StorageReference riversRef = mStorage.child("Images/"+_imgName);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data1 = baos.toByteArray();
        riversRef.putBytes(data1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getContext(), "Image Successfuly Uploaded", Toast.LENGTH_SHORT).show();
                riversRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        ImageUrllink=task.getResult().toString();
                    }
                });
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode== Activity.RESULT_OK) {

            photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            saveUploadimage();
        }
    }

}