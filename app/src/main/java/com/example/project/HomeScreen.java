package com.example.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class HomeScreen extends AppCompatActivity {

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = getInstance().getCurrentUser();
        String uid = user.getUid();                 //-------------------------- Use this as primary key for database, I think it works but not 100% sure
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

 /*       if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();                                  This might be useful for getting user info

                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                String email = profile.getEmail();
                Uri photoUrl = profile.getPhotoUrl();
            }
        }
*/
        //--------------------------------------------------------------
        //Add in UI functionality
        //--------------------------------------------------------------
    }
}
