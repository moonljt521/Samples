package com.moon.samples.full_function_recyclerview.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.moon.samples.R;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


/**
 * Created by L.K.X on 2017/3/30.
 */
public class XRecyclerView extends FrameLayout implements LoadMoreRecyclerView.OnLoadMoreListener {

    private LoadMoreRecyclerView recyclerview;
//    private PtrClassicFrameLayout ptr_freash;
    private XRParallaxPrtFrameLayout ptr_freash;
    private boolean isRefreshing = false;

    public void setEmptyLayout(View emptyLayout) {
        this.emptyLayout = emptyLayout;
    }

    private View emptyLayout;

    public XRecyclerView(Context context) {
        super(context);
        init();
    }

    public XRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.xrefreshview, this);
        recyclerview = (LoadMoreRecyclerView) findViewById(R.id.recyclervieww);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setMyLayoutManager(linearLayoutManager);
        recyclerview.setOnLoadMoreListener(this);
        // TODO: 17/10/18

//        ptr_freash = (PtrClassicFrameLayout) findViewById(R.id.ptr_refresh);
        ptr_freash = (XRParallaxPrtFrameLayout) findViewById(R.id.ptr_refresh);
//        ptr_freash.setLastUpdateTimeRelateObject(this);
        ptr_freash.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view1) {
                if (recyclerview.getChildCount() == 0 || recyclerview.getChildCount() > 0 && linearLayoutManager.findFirstVisibleItemPosition() == 0 && recyclerview.getChildAt(0).getTop() >= 0) {
                    return true;
                }
                return false;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                if (onListListener != null && !isRefreshing) {
                    isRefreshing = true;
                    onListListener.onRefreshing();
                }
            }
        });

    }


    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerview.setAdapter(adapter);

        recyclerview.setEmptyView(emptyLayout);

    }

    @Override
    public void onLoading() {
        if (onListListener != null && !isRefreshing) {
            onListListener.onLoadingMore();
        }
    }


    private OnListListener onListListener;

    public interface OnListListener {
        void onLoadingMore();

        void onRefreshing();
    }

    public void setOnListListener(OnListListener onListListener) {
        this.onListListener = onListListener;
    }


    public void loading() {
        recyclerview.loading();
    }

    public void loadingComplete() {
        recyclerview.loadingComplete();
    }

    public void refreshComplete() {
        isRefreshing = false;
        ptr_freash.refreshComplete();
    }


    public void setTopPading(int topPading) {
        recyclerview.setPadding(0, topPading, 0, 0);
    }

    public void hideLoadMore() {
        if (recyclerview != null) {
            recyclerview.hideLoadMore();
        }
    }

}
