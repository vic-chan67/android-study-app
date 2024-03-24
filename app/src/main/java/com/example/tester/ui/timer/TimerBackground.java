package com.example.tester.ui.timer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TimerBackground extends View {

    private Paint backgroundPaint;

    public TimerBackground(Context context) {
        super(context);
        init();
    }

    public TimerBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimerBackground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        backgroundPaint = new Paint();
            backgroundPaint.setColor(Color.parseColor("#A0A0A0")); // Set initial background color
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the background with the current paint color
        canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);
    }

    // Method to set the background color dynamically
    public void setBackgroundColor(int color) {
        backgroundPaint.setColor(color);
        invalidate(); // Redraw the view
    }
}
