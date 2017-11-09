package com.moon.samples.onmeasure_onlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.moon.samples.utils.UDebug;

/**
 * Created by moon on 2017/11/9.
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
        init();

    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    Paint paint = new Paint();

    private void init(){
        paint.setColor(Color.BLACK);


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);

//        if (width < height) {
//            height = width;
//        } else {
//            width = height;
//        }

        setMeasuredDimension(width, height);


    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        UDebug.i("myview," + "mode = " + mode + ",size = " + size);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                mySize = size;
                break;
            }
        }
        return mySize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(100,100,100,paint);

        setBackgroundColor(Color.BLUE);

    }
}
