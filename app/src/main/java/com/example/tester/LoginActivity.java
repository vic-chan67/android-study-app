package com.example.tester;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText textEmail, textPassword;
    private Button buttonLogin, buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textEmail = findViewById(R.id.login_email);
        textPassword = findViewById(R.id.login_password);
        buttonLogin = findViewById(R.id.loginL_button);
        buttonRegister = findViewById(R.id.loginR_button);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve textEmail and textPassword entered by the user
                String email = textEmail.getText().toString().trim();
                String password = textPassword.getText().toString().trim();

                // Perform login authentication logic here
                // For example, you can check if the textEmail and textPassword are valid
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}

