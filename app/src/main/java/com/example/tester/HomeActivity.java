package com.example.tester;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tester.ui.timer.TimerActivity;
import com.example.tester.ui.todo.TodoActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    /**
     * onCreate
     * Called when starting the activity to create the ui and its components
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Setup the todoButton listener
        Button todoButton = findViewById(R.id.home_todo);
        todoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Takes the user to the TodoActivity page (to-do lists)
                Intent todoIntent = new Intent(HomeActivity.this, TodoActivity.class);
                startActivity(todoIntent);
            }
        });

        // Setup the timerButton listener
        Button timerButton = findViewById(R.id.home_timer);
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Takes the user to the TimerActivity page (pomodoro timer)
                Intent timerIntent = new Intent(HomeActivity.this, TimerActivity.class);
                startActivity(timerIntent);
            }
        });

        // Setup the logoutButton listener
        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logs user out of their account
                FirebaseAuth.getInstance().signOut();
                // Takes the user back to the MainActivity page (main page to log back in or register a new account)
                Intent logoutIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(logoutIntent);
            }
        });
    }
}