package com.moon.samples.full_function_recyclerview.view;

import android.content.Context;
import android.util.AttributeSet;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * author: moon
 * created on: 17/10/18 下午12:25
 * description:
 */
public class XRParallaxPrtFrameLayout extends PtrFrameLayout {

    public XRParallaxPrtFrameLayout(Context context) {
        super(context);
        initHeaderView();
    }

    public XRParallaxPrtFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView();
    }

    public XRParallaxPrtFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initHeaderView();
    }

    private void initHeaderView(){
        XHeadView xHeadView = new XHeadView(getContext());

        setHeaderView(xHeadView);

        addPtrUIHandler(xHeadView);
    }
}
