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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class EditUserActivity extends AppCompatActivity {

    private Button Personal_Update, ScheduleUpdate;
    private FirebaseAuth Authentication;
    private static final String TAG = "EditUserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Authentication = FirebaseAuth.getInstance();
        String uid = Authentication.getCurrentUser().getUid();

        DocumentReference docRef = db.collection("User_Information").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        TextView textView = (TextView) findViewById(R.id.First_Name);
                        textView.setText(document.get("First Name").toString());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        super.onCreate(savedInstanceState);
        Personal_Update = findViewById(R.id.button);
        Personal_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(EditUserActivity.this, EditUserActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
