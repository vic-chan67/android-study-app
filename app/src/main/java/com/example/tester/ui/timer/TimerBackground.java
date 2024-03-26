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

    /**
     * TimerBackground constructors to initialise a TimerBackground object
     */
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

    /**
     * init()
     * Sets the background colour
     */
    private void init() {
        backgroundPaint = new Paint();
        backgroundPaint.setColor(backgroundColour);
    }

    /**
     * onDraw()
     * @param canvas    the canvas the background is drawn on
     * Finds the background height represented by the percentage of time left
     * Draws the background with the new height
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Find backgroundHeight from percentage of time left
        int backgroundHeight = (int) (getHeight() * percentage);
        // Draw background with the backgroundHeight
        canvas.drawRect(0, getHeight() - backgroundHeight, getWidth(), getHeight(), backgroundPaint);
    }

    /**
     * setBackgroundColour()
     * @param colour    colour of the background to draw
     * Included for test purposes
     */
    public void setBackgroundColour(int colour) {
        backgroundPaint.setColor(colour);
        // Redraw
        invalidate();
    }

    /**
     * setPercentage()
     * @param percentage    percentage of time left
     */
    public void setPercentage(double percentage) {
        this.percentage = percentage;
        // Redraw
        invalidate();
    }
}