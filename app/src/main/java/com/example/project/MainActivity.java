package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private Button LogSign,submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogSign = findViewById(R.id.Log_Sign_Button);
        LogSign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this,LogSignActivity.class);
                startActivity(myIntent);
            }
        });

        //---------------------------------------------------------------------------------
        //Need to add in dropdown list to xml and variable that saves value of drop down list
        //Need submit button functionality that goes back to MainActivity
        //----------------------------------------------------------------------------------

        submit = findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
    }
}
