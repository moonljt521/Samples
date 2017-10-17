package com.moon.samples.full_function_recyclerview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.moon.samples.utils.UDebug;

/**
 * author: moon
 * created on: 17/10/17 下午12:15
 * description:
 */
public class X1RecyclerView extends RecyclerView {

    private EmptyLayout emptyView;

    private View errorView;

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private OnClickListener clickListener ;


    public X1RecyclerView(Context context) {
        super(context);
        initEmptyView();
    }

    public X1RecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initEmptyView();
    }

    public X1RecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initEmptyView();
    }

    private void initEmptyView(){
        emptyView = new EmptyLayout(getContext());
    };

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            UDebug.i("onItemRangeInserted" + itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };
    private void checkIfEmpty() {
        if (emptyView != null && getAdapter() != null) {
            final boolean emptyViewVisible =
                    getAdapter().getItemCount() == 0;

            emptyView.setErrorType(EmptyLayout.NODATA);

            setVisibility(emptyViewVisible ? GONE : VISIBLE);
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

    //设置没有内容时，提示用户的空布局
    public void setEmptyView(EmptyLayout emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }


}
