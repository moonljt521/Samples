package com.moon.samples.full_function_recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.moon.samples.R;
import com.moon.samples.BaseActivity;
import com.moon.xultrarecycle.EmptyLayout;
import com.moon.xultrarecycle.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomRecyclerViewActivity extends BaseActivity {

    private XRecyclerView recyclerView;

    private MulRecyclerViewAdapter adapter;

    private List<String> list = new ArrayList<>();

    private int page = 1;
    private int totalSize;

    private EmptyLayout emptyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_function_recycler_view);

        recyclerView = (XRecyclerView) findViewById(R.id.mulRecyclerVieww);

        emptyLayout = (EmptyLayout) findViewById(R.id.emptyView);

//        emptyLayout = View.inflate(this,R.layout.view_error_layout,null);

//        recyclerView.setEmptyLayout(emptyLayout);

        findViewById(R.id.mulRecyclerViewwClearBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                adapter.notifyDataSetChanged();

                recyclerView.refreshComplete();
            }
        });

        findViewById(R.id.mulRecyclerViewwReLoadBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);

                recyclerView.autoRefresh();
            }
        });

        //  监听  刷新 和 加载更多
        recyclerView.setOnListListener(new XRecyclerView.OnListListener() {
            @Override
            public void onLoadingMore() {
                addData();
            }

            @Override
            public void onRefreshing() {
                refreshData();
            }
        });



        recyclerView.setAdapter(adapter = new MulRecyclerViewAdapter(this, list, null));

        recyclerView.setDataChangeListener(new XRecyclerView.DataChangeListener() {
            @Override
            public void change(boolean isEmpty) {
                 emptyLayout.setErrorType(isEmpty ? EmptyLayout.NODATA : EmptyLayout.HIDE_LAYOUT);
            }
        });

        recyclerView.canCelLoadMore();

        refreshData();
    }

    @Override
    protected String getActionTitle() {
        return "recyclerView";
    }

    private void addData() {
        totalSize = list.size();

        if (totalSize >= 100) {
            recyclerView.loadingComplete();
            return;
        }

        for (int i = 0; i < 20; i++) {
            list.add("第 " + (totalSize + i) + " 项目");
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();

                recyclerView.refreshComplete();
            }
        },2000);


    }

    /**
     * 刷新
     */
    private void refreshData() {

        list.clear();

        for (int i = 0; i < 20; i++) {
            list.add("第 " + i + " 项目");
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                totalSize = list.size();

                adapter.notifyDataSetChanged();

                recyclerView.refreshComplete();
            }
        },10000);



    }

}
