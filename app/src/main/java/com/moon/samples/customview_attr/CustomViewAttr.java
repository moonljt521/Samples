package com.moon.samples.customview_attr;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.moon.samples.R;
import com.moon.samples.utils.Logger;

/**
 * author: moon
 * created on: 18/2/9 上午10:47
 * description:
 */
public class CustomViewAttr extends View {


    private Paint paint = new Paint();

    private int moonViewColor;
    private int moonViewTextSize;

    public CustomViewAttr(Context context) {
        super(context);

    }

    public CustomViewAttr(Context context, @Nullable AttributeSet attrs) {

        this(context,attrs,0);
    }

    public CustomViewAttr(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs,defStyleAttr);
    }

    public CustomViewAttr(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawText("ljt",100,100,paint);

        canvas.drawCircle(400,400,200,paint);

    }


    private void init(AttributeSet attrs, int defStyleAttr){
        System.out.println("start................");

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomViewAttr);

        moonViewColor = a.getColor(R.styleable.CustomViewAttr_moonViewColor,Color.RED);

//        moonViewTextSize = a.getDimensionPixelSize(R.styleable.CustomViewAttr_moonViewTextSize,10);
        moonViewTextSize = (int) a.getDimension(R.styleable.CustomViewAttr_moonViewTextSize,40);

        Logger.i("color = " + moonViewColor + ", size = " + moonViewTextSize);

        a.recycle();

        paint.setColor(moonViewColor);

        paint.setTextSize(moonViewTextSize);


        System.out.println("end................");

    }

}
