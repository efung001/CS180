package com.example.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class LogSignActivity extends AppCompatActivity {

    private Button Log_In, Sign_Up;
    private FirebaseAuth Authentication;
    private static final String TAG = "LogSignActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Authentication = getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_sign);

        Log_In = findViewById(R.id.LogIn);
        Log_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText pass_field = (EditText)findViewById(R.id.password);
                EditText user_field = (EditText)findViewById(R.id.email);
                String email = user_field.getText().toString();
                String password = pass_field.getText().toString();

                Authentication.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LogSignActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = Authentication.getCurrentUser();
                                    //updateUI(user);
                                    Intent myIntent = new Intent(LogSignActivity.this, HomeScreen.class);
                                    startActivity(myIntent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LogSignActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                    Intent myIntent = new Intent(LogSignActivity.this, LogSignActivity.class);
                                    startActivity(myIntent);
                                }
                            }
                        });
            }
        });

        Sign_Up = findViewById(R.id.SignUp);
        Sign_Up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LogSignActivity.this,SignUpActivity.class);
                startActivity(myIntent);
            }
        });
    }
}