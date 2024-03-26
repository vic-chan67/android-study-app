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

    /**
     * onCreate
     * Called when starting the activity to create the ui and its components
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // Setup components
        timerText = findViewById(R.id.timer_time);
        timerBackground = findViewById(R.id.backgroundView);

        // Setup startStop button listener
        startStop = findViewById(R.id.timer_startstop);
        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If timer running (timeRunning is True), stopTimer() called
                if (timeRunning) {
                    stopTimer();
                // If timer stopped (timeRunning is False), startTimer() called
                } else {
                    startTimer();
                }
            }
        });

        // Setup backButton listener
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Takes the user to the HomeActivity page
                Intent backIntent = new Intent(TimerActivity.this, HomeActivity.class);
                startActivity(backIntent);
            }
        });
    }

    /**
     * stopTimer()
     * Stops the timer, updates the timeRunning boolean, and sets the startStop button text
     */
    private void stopTimer() {
        timer.cancel();
        timeRunning = false;
        startStop.setText(R.string.timer_start);
    }

    /**
     * startTimer()
     * Creates variables for the study/break times and starts the timer
     */
    private void startTimer() {
        // Set studyTime and breakTime variables
        long studyTime = 50 * 60 * 1000;
        long breakTime = 10 * 60 * 1000;

        // Start new CountDownTimer (countDownInterval = 1000 milliseconds = 1 second)
        timer = new CountDownTimer(totalTime, 1000) {
            /**
             * onTick()
             * @param millisUntilFinished   amount of time (milliseconds) until timer finished
             * When the timer is running
             */
            @Override
            public void onTick(long millisUntilFinished) {
                timeRunning = true;     // set bool to true (timer is running)
                startStop.setText(R.string.timer_stop);     // change button text to "Stop Timer"
                timeLeft = millisUntilFinished;     // set the timeLeft
                updateTimer();      // updateTimer() updates the timer text
                updateBackground();     // updateBackground() updates the background text
            }

            /**
             * onFinish()
             * When the timer is finished
             */
            @Override
            public void onFinish() {
                timeRunning = false;        // set bool to false (timer is finished)
                startStop.setText(R.string.timer_start);        // change button text to "Start Timer"

                // Check if the totalTime was the studyTime
                if (totalTime == studyTime) {
                    totalTime = breakTime;      // set totalTime to breakTime
                    String timeLeftText = "10:00";      // set timer text to 10 minutes
                    timerText.setText(timeLeftText);

                // Check if the totalTime was the breakTime
                } else if (totalTime == breakTime) {
                    totalTime = studyTime;      // set totalTime to studyTime
                    String timeLeftText = "50:00";      // set timer text to 50 minutes
                    timerText.setText(timeLeftText);
                }
            }
        }.start();
    }

    /**
     * updateTimer()
     * Updates the timer text to reflect the time left in minutes:seconds format
     */
    private void updateTimer() {
        // Find the time in minutes and seconds
        long minutes = (timeLeft / 1000) / 60;
        long seconds = (timeLeft / 1000) % 60;

        @SuppressLint("DefaultLocale")
        String timeLeftText = String.format("%02d:%02d", minutes, seconds);
        timerText.setText(timeLeftText);
    }

    /**
     * updateBackground()
     * Updates the background height to represent percentage of time left
     */
    private void updateBackground() {
        double percentage = (double) timeLeft / totalTime;
        // Call timerBackground function setPercentage()
        timerBackground.setPercentage(percentage);
    }
}