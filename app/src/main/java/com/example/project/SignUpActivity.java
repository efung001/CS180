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




public class SignUpActivity extends AppCompatActivity {
    private Button SignUpDone;
    private FirebaseAuth Authentication;
    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Authentication = getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SignUpDone = findViewById(R.id.SignUpButton);
        SignUpDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText pass_field = (EditText)findViewById(R.id.password2);
                EditText user_field = (EditText)findViewById(R.id.email2);
                String email = user_field.getText().toString();
                String password = pass_field.getText().toString();

                Authentication.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = Authentication.getCurrentUser();
                                    Intent myIntent = new Intent(SignUpActivity.this, HomeScreen.class);
                                    startActivity(myIntent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                    Intent myIntent = new Intent(SignUpActivity.this, SignUpActivity.class);
                                    startActivity(myIntent);
                                }
                            }
                        });
            }
        });
    }
}
