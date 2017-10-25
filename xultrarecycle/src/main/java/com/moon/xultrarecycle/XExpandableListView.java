package com.moon.xultrarecycle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * author: moon
 * created on: 17/10/24 下午4:34
 * description: 扩展的 ExpandableListView
 */
public class XExpandableListView extends FrameLayout {

    private ExpandableListView expandableListView;
    private XRParallaxPrtFrameLayout ptr_freash;
    private boolean isRefreshing = false;

    public void setOnListListener(OnListListener onListListener) {
        this.onListListener = onListListener;
    }

    private OnListListener onListListener;


    public XExpandableListView(Context context) {
        super(context);
        init();
    }

    public XExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.xexpandablelistview, this);
        expandableListView = (ExpandableListView) findViewById(R.id.x_expandablelistview);

        expandableListView.setGroupIndicator(null);


        ptr_freash = (XRParallaxPrtFrameLayout) findViewById(R.id.ptr_refresh);
        ptr_freash.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view1) {
                if (expandableListView.getChildCount() == 0 || expandableListView.getChildCount() > 0  && expandableListView.getChildAt(0).getTop() >= 0) {
                    return true;
                }
                return false;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                if (onListListener != null && !isRefreshing) {
                    isRefreshing = true;

                    loading();

                    onListListener.onRefreshing();
                }
            }
        });
    }

    public void setOnGroupListener(ExpandableListView.OnGroupClickListener listener){
        if (expandableListView !=null){
            expandableListView.setOnGroupClickListener(listener);
        }
    }

    public void expandGroup(int groupPos){
        if (expandableListView !=null){
            expandableListView.expandGroup(groupPos);
        }
    }


    public void setAdapter(BaseExpandableListAdapter adapter) {
        expandableListView.setAdapter(adapter);

//        expandableListView.setDataChangeObserver(this);
    }

    public void setGroupIndicator(Drawable drawable){
        if (expandableListView !=null){
            expandableListView.setGroupIndicator(drawable);
        }
    }



    public interface OnListListener {

        void onLoadingMore();

        void onRefreshing();
    }




    public void loading() {
//        recyclerview.loading();
    }

    public void loadingComplete() {
//        recyclerview.loadingComplete();
    }

    public void refreshComplete() {
        isRefreshing = false;
        ptr_freash.refreshComplete();
    }


    public void autoRefresh(){
        ptr_freash.autoRefresh();
    }

}
