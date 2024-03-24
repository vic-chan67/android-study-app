package com.example.tester;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText textEmail, textPassword;

    /**
     * onCreate
     * Called when starting the activity to create the ui and its components
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Setup components
        textEmail = findViewById(R.id.reg_email);
        textPassword = findViewById(R.id.reg_password);
        Button registerButton = findViewById(R.id.regR_button);
        TextView loginButton = findViewById(R.id.regL_button);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        
        // Setup registerButton listener
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input for their email and password
                String email = textEmail.getText().toString().trim();
                String password = textPassword.getText().toString().trim();

                // Check if the user input is empty
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(RegisterActivity.this, "Enter email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_LONG).show();
                    return;
                }

                // User authentication (create account)
                 auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) {
                             Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_LONG).show();
                             // Takes the user to the LoginActivity page (login to their new account)
                             Intent registerIntent = new Intent(getApplicationContext(), LoginActivity.class);
                             startActivity(registerIntent);
                             finish();
                         } else {
                             Toast.makeText(RegisterActivity.this, "Register failed", Toast.LENGTH_LONG).show();
                         }
                     }
                 });
            }
        });

        // Setup loginButton listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Takes the user to the LoginActivity page (login to their new account)
                Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}

