package com.moon.samples.propertyanimator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: moon
 * created on: 17/9/12 下午6:03
 * description:
 */
public class TestArgbEvaluatorView extends View {

    private Paint paint = new Paint();



    private int color;

    public TestArgbEvaluatorView(Context context) {
        super(context);

        init();
    }

    public TestArgbEvaluatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();

    }

    public TestArgbEvaluatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

    }

    public TestArgbEvaluatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();

    }

    private void init(){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(color);
        canvas.drawCircle(200,600,100,paint);

    }
}
