package com.moon.xultrarecycle;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * author: moon
 * created on: 17/10/18 下午12:55
 * description:  仿微信小程序三个点的头部刷新动画
 */
public class XHeadView extends View implements PtrUIHandler {

    private Paint mPaint; //


    private static final float RADIUS = 10;

    private static final int SIZE = 3;

    private static final int HEAD_VIEW_HEIGHT = 100;

    private Timer timer;

    private int index;

    private boolean isKeepRefresh;

    private int limitHeadViewMarginLeftDimen;

    public XHeadView(Context context) {
        super(context);
        init();
    }

    public XHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public XHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }

    /**
     * 初始化 ** 等
     */
    private void init() {
        // 初始化画笔
        mPaint = new Paint();
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        limitHeadViewMarginLeftDimen = (wm.getDefaultDisplay().getWidth() - 60) / 2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centX = limitHeadViewMarginLeftDimen + 10;

        int centY = 50;

        int darkPosX = 0;

        mPaint.setColor(Color.GRAY);
        for (int i = 0; i < SIZE; i++) {
            if (index % 3 == i) {
                darkPosX = centX;
            }
            canvas.drawCircle(centX, centY, RADIUS, mPaint);
            centX += 50;
        }

        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(darkPosX, centY, RADIUS, mPaint);

        index++;
    }

    /**
     * 启动定时器 刷新UI
     */
    private void executeTask() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isKeepRefresh) {
                    postInvalidate();
                }
            }
        }, 0, 800);
    }



    /**
     * 以下四个方法执行顺序  prepare -> begin -> complete -> reset
     */


    @Override
    public void onUIReset(PtrFrameLayout frame) {
        // 重置头View的动画状态，一般停止刷新动画。
        if (timer != null) {
            isKeepRefresh = false;
            timer.cancel();
            timer = null;
        }

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        // 准备刷新的UI。 手指拖住，下拉，不松手的时候会触发， 一次
        executeTask();

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        // 开始刷新的UI动画。
        isKeepRefresh = true;

    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        // 刷新完成，停止刷新动画。
        isKeepRefresh = false;
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        // 手指下拉的时候的状态，我们的下拉动画的控制就是通过这个方法：
        // frame是刷新的root layout。
        // isUnderTouch是手指是否按下，因为还有自动刷新，手指肯定是松开状态。
        // status是现在的加载状态，准备、加载中、完成：PREPARE、LOADING、COMPLETE。
        // ptrIndicator是一些下拉偏移量的参数封装。
        // TODO: 17/10/18

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = measureDimension(limitHeadViewMarginLeftDimen, widthMeasureSpec);
        int height = measureDimension(HEAD_VIEW_HEIGHT, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    public int measureDimension(int defaultSize, int measureSpec) {
        int result;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {

            result = specSize;
        } else {
            result = defaultSize;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
