package com.moon.xultrarecycle;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.moon.xultrarecycle.utils.XUltraLog;


/**
 * Created by L.K.X on 2017/3/30.
 */
public class LoadMoreRecyclerView extends RecyclerView {

    private MoreItemDecoration moreItemDecoration;
    private LinearLayoutManager layout;
    private boolean isFirst = true;

    private boolean isHaveMore = true;
    private boolean isLoadingMore = false;
    private int height;
    private int[] location = new int[2];
    private int[] location_listview = new int[2];

    public LoadMoreRecyclerView(Context context) {
        super(context);
        init();
    }


    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        moreItemDecoration = new MoreItemDecoration(getContext().getApplicationContext());
        addItemDecoration(moreItemDecoration);
    }

    public void loading() {
        isHaveMore = true;
        isLoadingMore = false;
        moreItemDecoration.setLoadDes(getContext().getResources().getString(R.string.error_view_loading));
    }

    public void loadingComplete() {
        isHaveMore = false;
        moreItemDecoration.setLoadDes(getContext().getResources().getString(R.string.error_view_have_no_data));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        getLocationInWindow(location_listview);
        height = getHeight();
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(isFirst){
            isFirst = false;
            return;
        }

        if (layout != null && getAdapter() != null) {
            int lastVisibleItemPosition = layout.findLastVisibleItemPosition();
            if (getAdapter() != null && getAdapter().getItemCount() > 0 && lastVisibleItemPosition >= getAdapter().getItemCount() - 1 && !isLoadingMore && isHaveMore && isHaveMore) {
                View childAt = layout.findViewByPosition(getAdapter().getItemCount() - 1);
                if (childAt != null) {
                    childAt.getLocationInWindow(location);
                    //System.out.println(location[1]+childAt.getHeight()+ moreItemDecoration.getFootHeight()+"getBottom()" + (location_listview[1]+height));
                    if (location[1] + childAt.getHeight() + moreItemDecoration.getFootHeight() <= location_listview[1] + height) {
                        isLoadingMore = true;
                        if (onLoadMoreListener.get() != null) {
                            onLoadMoreListener.get().onLoading();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
        checkIfEmpty();
    }

    public void setMyLayoutManager(LinearLayoutManager layout) {
        this.layout = layout;
        setLayoutManager(layout);
    }



    interface OnLoadMoreListener {
        void onLoading();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener.set(onLoadMoreListener);
    }

    public void hideLoadMore() {
        if (moreItemDecoration != null) {
            moreItemDecoration.setLoadDes("");
        }
    }

    private final AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            XUltraLog.i("onItemRangeInserted" + itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };


    private void checkIfEmpty() {
        if (getAdapter() != null) {
            final boolean emptyViewVisible =
                    getAdapter().getItemCount() == 0;


//            if (emptyViewVisible){
//
//                emptyView.setVisibility(VISIBLE);
//                ((EmptyLayout)emptyView).setErrorType(EmptyLayout.NODATA);
//                setVisibility(View.GONE);
//            }else {
//                emptyView.setVisibility(GONE);
//                ((EmptyLayout)emptyView).setErrorType(EmptyLayout.HIDE_LAYOUT);
//                setVisibility(VISIBLE);
//            }

            if (dataChangeObserver!=null){
                dataChangeObserver.dataChange(emptyViewVisible);
            }

        }
    }

    private final ThreadLocal<OnLoadMoreListener> onLoadMoreListener = new ThreadLocal<>();

    public void setDataChangeObserver(IDataChangeObserver dataChangeObserver) {
        this.dataChangeObserver = dataChangeObserver;
    }

    private IDataChangeObserver dataChangeObserver;

    interface IDataChangeObserver {

        void dataChange(boolean isEmpty) ;
    }

}
