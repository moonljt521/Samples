package com.moon.xultrarecycle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.moon.xultrarecycle.utils.XRecyclerViewUtils;


/**
 * Created by L.K.X on 2017/3/26.
 */
public class MoreItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private Paint mValuePaint;

    private Context mContext;

    public int getFootHeight() {
        return footHeight;
    }

    private int footHeight ;


    public MoreItemDecoration(Context context) {
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setColor(Color.TRANSPARENT);//画出脚背景的颜色
        mPaint.setAntiAlias(true);


        mValuePaint = new Paint();
        mValuePaint.setAntiAlias(true);
        mValuePaint.setColor(Color.parseColor("#666666"));
        mValuePaint.setTextSize(XRecyclerViewUtils.dip2px(mContext,14));
        mValuePaint.setStyle(Paint.Style.FILL);
        //该方法即为设置基线上那个点究竟是left,center,还是right  这里我设置为center
        mValuePaint.setTextAlign(Paint.Align.CENTER);

        footHeight = XRecyclerViewUtils.dip2px(mContext,40);

        loadDes = mContext.getResources().getString(R.string.error_view_loading);
    }

    public void setLoadDes(String loadDes) {
        this.loadDes = loadDes;
    }

    private String loadDes ;

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child));
            final int bottom = top + footHeight;

            if (i == childCount - 1) {
                Rect rect = new Rect(left, top, right, bottom);
                c.drawRect(rect, mPaint);

                Paint.FontMetrics fontMetrics = mValuePaint.getFontMetrics();
                float topp = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
                float bottomm = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
                int baseLineY = (int) (rect.centerY() - topp / 2 - bottomm / 2);//基线中间点的y轴计算公式
                c.drawText(loadDes, rect.centerX(), baseLineY, mValuePaint);
            } else {
                c.drawRect(0, 0, 0, 0, mPaint);
            }
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemCount = parent.getAdapter().getItemCount();
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (childAdapterPosition == itemCount - 1) {
            outRect.set(0, 0, 0, footHeight);
        } else {
            outRect.set(0, 0, 0, 0);
        }
    }
}
