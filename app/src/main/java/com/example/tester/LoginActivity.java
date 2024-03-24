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

public class LoginActivity extends AppCompatActivity {

    private EditText textEmail, textPassword;

    /**
     * onCreate
     * Called when starting the activity to create the ui and its components
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Setup components
        textEmail = findViewById(R.id.login_email);
        textPassword = findViewById(R.id.login_password);
        Button loginButton = findViewById(R.id.loginL_button);
        TextView registerButton = findViewById(R.id.loginR_button);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        
        // Setup the loginButton listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input for their email and password
                String email = textEmail.getText().toString().trim();
                String password = textPassword.getText().toString().trim();

                // Check if the user input is empty
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Enter email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Enter password", Toast.LENGTH_LONG).show();
                    return;
                }

                // User authentication (sign in)
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                            // Takes the user to the HomeActivity page (home)
                            Intent loginIntent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(loginIntent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        // Setup registerButton listener
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Takes the user to the RegisterActivity page (register an account)
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}