package com.example.diagnoseme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private Button signOut;
    private FirebaseAuth mFAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mFAuth.getCurrentUser();
    private TextView welcome;
    private static final String TAG = "HomeActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        signOut=  findViewById(R.id.button_sign_out);
        welcome = findViewById(R.id.textView_home_welcome);

      welcomeUser(user);

        signOutUser();
    }
    private void welcomeUser(FirebaseUser user){
        user = mFAuth.getCurrentUser();
        if(user!= null) {
            String email = user.getEmail();
            welcome.setText("Signed In: \n" +email);
        }
        else {
            welcome.setText("Signed out");
        }
    }

    private void signOutUser(){
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFAuth.signOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                Toast.makeText(HomeActivity.this, "Signed out", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }
}