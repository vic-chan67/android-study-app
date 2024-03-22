package com.example.tester;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // to-do list button
        Button todoButton = findViewById(R.id.home_todo);
        todoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent todoIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(todoIntent);
            }
        });

        // pomodoro timer button
        Button timerButton = findViewById(R.id.home_timer);
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timerIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(timerIntent);
            }
        });

        // personal stats button
        Button statsButton = findViewById(R.id.home_stats);
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent statsIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(statsIntent);
            }
        });

        // personal rewards button
        Button rewardsButton = findViewById(R.id.home_rewards);
        rewardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rewardsIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(rewardsIntent);
            }
        });

        // settings button
        Button settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });
    }
}