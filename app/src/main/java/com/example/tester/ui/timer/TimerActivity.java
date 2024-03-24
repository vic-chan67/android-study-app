package com.example.tester.ui.timer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tester.HomeActivity;
import com.example.tester.R;

public class TimerActivity extends AppCompatActivity {

    private TextView timerText;
    private TimerBackground timerBackground;
    private CountDownTimer timer;
    private long totalTime = 60 * 1000;   // default value of 50mins study time
    private long timeLeft = totalTime;
    private boolean timeRunning = false;      // false = timer stopped (shows start button), true = timer running (shows stop button)
    private Button startStop;
    private long studyTime = 50 * 60 * 1000;
    private long breakTime = 10 * 60 * 1000;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerText = findViewById(R.id.timer_time);
        timerBackground = findViewById(R.id.backgroundView);

        // timer start stop button
        startStop = findViewById(R.id.timer_startstop);
        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });

        // back button
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(TimerActivity.this, HomeActivity.class);
                startActivity(backIntent);
            }
        });

        // skip to study/break button
    }

    // stop the timer
    private void stopTimer() {
        timer.cancel();
        timeRunning = false;
        startStop.setText(R.string.timer_start);
    }

    // start the timer
    private void startTimer() {
        timer = new CountDownTimer(totalTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRunning = true;
                startStop.setText(R.string.timer_stop);
                timeLeft = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                if (totalTime == (60 * 1000)) {   // studyTime
                    totalTime = 2 * 60 * 1000;  // breakTime

                    timeRunning = false;
                    startStop.setText(R.string.timer_start);
                    String timeLeftText = "10:00";
                    timerText.setText(timeLeftText);
//                    timerBackground();
                } else if (totalTime == (2 * 60 * 1000)) {     // breakTime
                    totalTime = 60 * 1000;     // studyTime

                    timeRunning = true;
                    startStop.setText(R.string.timer_start);
                    String timeLeftText = "50:00";
                    timerText.setText(timeLeftText);
                }
            }
        }.start();
    }

    // update the timer text
    private void updateTimer() {
        long minutes = (timeLeft / 1000) / 60;
        long seconds = (timeLeft / 1000) % 60;

        @SuppressLint("DefaultLocale")
        String timeLeftText = String.format("%02d:%02d", minutes, seconds);
        timerText.setText(timeLeftText);
    }

    private void updateBackground() {
        double percentage = (double) timeLeft / totalTime;
    }
}