package com.moon.samples.propertyanimator;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.moon.samples.utils.Logger;

/**
 * author: moon
 * created on: 17/12/31 下午3:32
 * description:  一个view， top 处为弧形，白色填充
 */
public class ArcTopView extends View {

    private Paint paint;
    private int width;
    private int height;
    private RectF oval;
    private RectF square;


    private float startAngle ;

    private float swingAngle;

    Path path;



    public ArcTopView(Context context) {
        super(context);
        init();
    }

    public ArcTopView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();


    }

    public ArcTopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public ArcTopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init(){
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        width = dm.widthPixels;
        height = dm.heightPixels;

        startAngle = -30;

        swingAngle = -120;

        path = new Path();


        int a = width/2;
        int r = 100 + width/2;

        float x = (float) Math.sqrt(r * r - a* a);


        x = (float) (r * Math.cos(Math.abs(swingAngle)/2 * Math.PI /180));


        float h = r - x;

        Logger.i("cos 60 =  " +  Math.cos(60 * Math.PI /180));

        Logger.i("r = " + r + ", a = " + a + ",x = " + x  + ", h = " + h);

        // 弧形
        oval = new RectF( -100, 0,
                width + 100  , width/2+100);

        // 矩形
        square = new RectF(0, h, width,2000);






        Logger.i("x*x = "+ x*x );
        Logger.i("a*a = "+ a*a );
        Logger.i("r*r = "+ r*r );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawArc(oval,-30, -360,false,paint);
        canvas.drawArc(oval,startAngle, swingAngle,false,paint);

        canvas.drawRect(square,paint);



        // 跟上边代码等价
//        path.addArc(oval,-30,-120);
//
//        path.addRect(square, Path.Direction.CW );
//
//        canvas.drawPath(path,paint);

    }
}
