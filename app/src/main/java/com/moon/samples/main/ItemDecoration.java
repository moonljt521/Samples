package com.moon.samples.main;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author: moon
 * created on: 17/9/7 下午4:23
 * description:
 */
public class ItemDecoration extends RecyclerView.ItemDecoration {

    private int mPaddingHeight;

    private Paint dividerPaint;

    private int paddingLeft = 25;

    public ItemDecoration(int paddingHeight) {
        this.mPaddingHeight = paddingHeight;
        dividerPaint = new Paint();
        dividerPaint.setColor(Color.RED);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.bottom = mPaddingHeight;

    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft()+ paddingLeft;
        final int right = parent.getWidth() - parent.getPaddingRight() - paddingLeft;

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount ; i++) {
            final View childView = parent.getChildAt(i);
            final int top = childView.getBottom();
            final int bottom = childView.getBottom() + mPaddingHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }


    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
