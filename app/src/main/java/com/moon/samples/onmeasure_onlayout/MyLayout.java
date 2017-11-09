package com.moon.samples.onmeasure_onlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.moon.samples.utils.UDebug;

/**
 * Created by moon on 2017/11/9.
 */

public class MyLayout extends LinearLayout {
    public MyLayout(Context context) {
        super(context);
    }

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureWidth = measureWidth(0, widthMeasureSpec);
        int measureHeight = measureHeight(0, heightMeasureSpec);
        // 计算自定义的ViewGroup中所有子控件的大小
        // 首先判断params.width的值是多少，有三种情况。
        //
        // 如果是大于零的话，及传递的就是一个具体的值，那么，构造MeasupreSpec的时候可以直接用EXACTLY。
        //
        // 如果为-1的话，就是MatchParent的情况，那么，获得父View的宽度，再用EXACTLY来构造MeasureSpec。
        //
        // 如果为-2的话，就是wrapContent的情况，那么，构造MeasureSpec的话直接用一个负数就可以了。
        // measureChildren(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);

            int widthSpec = 0;
            int heightSpec = 0;
            LayoutParams params = (LayoutParams) v.getLayoutParams();
            if (params.width > 0) {
                widthSpec = MeasureSpec.makeMeasureSpec(params.width,
                        MeasureSpec.EXACTLY);
            } else if (params.width == -1) {
                widthSpec = MeasureSpec.makeMeasureSpec(measureWidth,
                        MeasureSpec.EXACTLY);
            } else if (params.width == -2) {
                widthSpec = MeasureSpec.makeMeasureSpec(measureWidth,
                        MeasureSpec.AT_MOST);
            }

            if (params.height > 0) {
                heightSpec = MeasureSpec.makeMeasureSpec(params.height,
                        MeasureSpec.EXACTLY);
            } else if (params.height == -1) {
                heightSpec = MeasureSpec.makeMeasureSpec(measureHeight,
                        MeasureSpec.EXACTLY);
            } else if (params.height == -2) {
                heightSpec = MeasureSpec.makeMeasureSpec(measureWidth,
                        MeasureSpec.AT_MOST);
            }
            v.measure(widthSpec, heightSpec);

        }
        // 设置自定义的控件MyViewGroup的大小
        setMeasuredDimension(measureWidth, measureHeight);
    }

    private int measureWidth(int size, int pWidthMeasureSpec) {
        int result = size;
        int widthMode = MeasureSpec.getMode(pWidthMeasureSpec);// 得到模式
        int widthSize = MeasureSpec.getSize(pWidthMeasureSpec);// 得到尺寸

        switch (widthMode) {
            /**
             * mode共有三种情况，取值分别为MeasureSpec.UNSPECIFIED, MeasureSpec.EXACTLY,
             * MeasureSpec.AT_MOST。
             *
             *
             * MeasureSpec.EXACTLY是精确尺寸，
             * 当我们将控件的layout_width或layout_height指定为具体数值时如andorid
             * :layout_width="50dip"，或者为FILL_PARENT是，都是控件大小已经确定的情况，都是精确尺寸。
             *
             *
             * MeasureSpec.AT_MOST是最大尺寸，
             * 当控件的layout_width或layout_height指定为WRAP_CONTENT时
             * ，控件大小一般随着控件的子空间或内容进行变化，此时控件尺寸只要不超过父控件允许的最大尺寸即可
             * 。因此，此时的mode是AT_MOST，size给出了父控件允许的最大尺寸。
             *
             *
             * MeasureSpec.UNSPECIFIED是未指定尺寸，这种情况不多，一般都是父控件是AdapterView，
             * 通过measure方法传入的模式。
             */
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = widthSize;
                break;
        }
        return result;
    }

    private int measureHeight(int size, int pHeightMeasureSpec) {
        int result = size;

        int heightMode = MeasureSpec.getMode(pHeightMeasureSpec);
        int heightSize = MeasureSpec.getSize(pHeightMeasureSpec);

        switch (heightMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = heightSize;
                break;
        }
        return result;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        UDebug.i("left = "+ left +"，top = "+ top + ",right = "+right + ",bottom = "+ bottom);

        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        // 记录总高度
        int mTotalHeight = 0;
        // 遍历所有子视图
        int childCount = getChildCount();
        UDebug.i("childCount = " + childCount);
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            // 获取在onMeasure中计算的视图尺寸
            int measuredWidth = childView.getMeasuredWidth();

            int measureHeight = childView.getMeasuredHeight();

            UDebug.i("...."+ measuredWidth + "," + measureHeight);

//            childView.layout(left + (width-200)/2 , top , measuredWidth, measureHeight);
            UDebug.i("自定义偏移 = "+(width-200)/2 );
            childView.layout(left + (width-200)/2  , top , measuredWidth+ (width-200)/2, measureHeight);

//            mTotalHeight += measureHeight;

        }
    }
}
