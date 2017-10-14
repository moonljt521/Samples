package com.moon.samples.propertyanimator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.text.DecimalFormat;

/**
 * author: moon
 * created on: 17/9/6 下午2:21
 * description:
 */
public class CirclePathProgressView extends View {



    private float progressDepth;

    private Paint paint;

    private Paint textPaint;

    private DecimalFormat decimalFormat;


    public CirclePathProgressView(Context context) {
        super(context);
        init();
    }

    public CirclePathProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CirclePathProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CirclePathProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(20f);

        textPaint = new Paint();
        textPaint.setTextSize(60);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLUE);

        decimalFormat = new DecimalFormat(".00");
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(20,20,400,400,  0 ,progressDepth * 2f,true,paint);

        canvas.drawText(decimalFormat.format(progressDepth * 2f /3.6f)+ "%",110,230, textPaint );

    }

    public float getProgressDepth() {
        return progressDepth;
    }

    public void setProgressDepth(float progressDepth) {
        this.progressDepth = progressDepth;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
