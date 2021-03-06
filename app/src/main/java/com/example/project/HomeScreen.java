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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class HomeScreen extends AppCompatActivity {

    private FirebaseUser user;
    private static final String TAG = "HomeScreen";
    private String name = "";
    private Button updatePersonal, updateSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        user = getInstance().getCurrentUser();
        String uid = user.getUid();

        super.onCreate(savedInstanceState);

        //------------------------------------------------------            This is the Hello [username] box
        DocumentReference docRef = db.collection("User_Information").document(uid);             //Need to optimize to load faster
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        name = document.get("First Name").toString();
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
                TextView textView = (TextView) findViewById(R.id.Greeting);
                textView.setText("Hello " + name + "!");
            }
        });
        //----------------------------------------------------
        setContentView(R.layout.activity_home_screen);
        //----------------------------------------------------      Update Personal Information Button
        updatePersonal = findViewById(R.id.UpdatePersonalOption);
        updatePersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeScreen.this, EditUserActivity.class);
                startActivity(myIntent);
            }
        });
        //--------------------------------------------------------------------
        // ---------------------------------------------         Update Schedule Information button
        updateSchedule = findViewById(R.id.UpdateScheduleOption);
        updateSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeScreen.this, ScheduleActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
