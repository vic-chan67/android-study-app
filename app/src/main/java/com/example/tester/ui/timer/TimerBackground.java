package com.example.tester.ui.timer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TimerBackground extends View {

    private Paint backgroundPaint;
    private int backgroundColour = Color.parseColor("#A0A0A0");     // background colour
    private double percentage;

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

    // set the background colour
    private void init() {
        backgroundPaint = new Paint();
        backgroundPaint.setColor(backgroundColour);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // find backgroundHeight from percentage of time left
        int backgroundHeight = (int) (getHeight() * percentage);
        // draw background with the backgroundHeight
        canvas.drawRect(0, getHeight() - backgroundHeight, getWidth(), getHeight(), backgroundPaint);
    }

    // set the background colour
    public void setBackgroundColour(int color) {
        backgroundPaint.setColor(color);
        // redraw
        invalidate();
    }

    // set percentage of time left
    public void setPercentage(double percentage) {
        this.percentage = percentage;
        // redraw
        invalidate();
    }
}