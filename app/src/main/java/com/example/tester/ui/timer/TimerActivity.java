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
    private long totalTime = 50 * 60 * 1000;   // default value of 50 minutes study time
    private long timeLeft = totalTime;      // initial timeLeft
    private boolean timeRunning = false;      // false = timer stopped (shows start button), true = timer running (shows stop button)
    private Button startStop;

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
    }

    // stop the timer
    private void stopTimer() {
        timer.cancel();
        timeRunning = false;
        startStop.setText(R.string.timer_start);
    }

    // start the timer
    private void startTimer() {
        // set studyTime and breakTime variables
        long studyTime = 50 * 60 * 1000;
        long breakTime = 10 * 60 * 1000;

        // start new CountDownTimer (countDownInterval = 1000 milliseconds = 1 second)
        timer = new CountDownTimer(totalTime, 1000) {
            @Override
            // when timer is running
            public void onTick(long millisUntilFinished) {
                timeRunning = true;     // set bool to true (timer is running)
                startStop.setText(R.string.timer_stop);     // change button text to "Stop Timer"
                timeLeft = millisUntilFinished;     // set the timeLeft
                updateTimer();      // updateTimer() updates the timer text
                updateBackground();     // updateBackground() updates the background text
            }

            @Override
            // when timer is finished
            public void onFinish() {
                timeRunning = false;        // set bool to false (timer is finished)
                startStop.setText(R.string.timer_start);        // change button text to "Start Timer"

                // check if the totalTime was the studyTime
                if (totalTime == studyTime) {
                    totalTime = breakTime;      // set totalTime to breakTime
                    String timeLeftText = "10:00";      // set timer text to 10 minutes
                    timerText.setText(timeLeftText);
                } else if (totalTime == breakTime) {
                    totalTime = studyTime;      // set totalTime to studyTime
                    String timeLeftText = "50:00";      // set timer text to 50 minutes
                    timerText.setText(timeLeftText);
                }
            }
        }.start();
    }

    // update the timer text
    private void updateTimer() {
        // find the time in minutes and seconds
        long minutes = (timeLeft / 1000) / 60;
        long seconds = (timeLeft / 1000) % 60;

        @SuppressLint("DefaultLocale")
        String timeLeftText = String.format("%02d:%02d", minutes, seconds);
        timerText.setText(timeLeftText);
    }

    // update background colour to represent time left
    private void updateBackground() {
        double percentage = (double) timeLeft / totalTime;
        // call timerBackground function setPercentage()
        timerBackground.setPercentage(percentage);
    }
}