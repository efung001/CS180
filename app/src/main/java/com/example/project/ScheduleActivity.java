package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ScheduleActivity extends AppCompatActivity {
   private Button Schedule_Update;
    private FirebaseAuth Authentication;
    private FirebaseUser user;
    private static final String TAG = "ScheduleActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Authentication = FirebaseAuth.getInstance();
        user = Authentication.getCurrentUser();
        String uid = user.getUid();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        DocumentReference docRef = db.collection("User_Information").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        List<String> monday = (List<String>) document.get("Monday");
                        List<String> tuesday = (List<String>) document.get("Tuesday");
                        List<String> wednesday = (List<String>) document.get("Wednesday");
                        List<String> thursday = (List<String>) document.get("Thursday");
                        List<String> friday = (List<String>) document.get("Friday");
                        if(monday.get(0) != "N/A")
                        {
                            TextView textView = (TextView) findViewById(R.id.test);
                            textView.setText(monday.get(0));
                        }
                        /*if(tuesday.get(0) != "N/A")
                        {

                        }
                        if(wednesday.get(0) != "N/A")
                        {

                        }
                        if(thursday.get(0) != "N/A")
                        {

                        }
                        if(friday.get(0) != "N/A")
                        {

                        }*/
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        Schedule_Update = findViewById(R.id.NewClass);
        Schedule_Update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ScheduleActivity.this,ScheduleActivity.class);
                startActivity(myIntent);
            }
        });

    }
}
