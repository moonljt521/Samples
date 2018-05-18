package com.moon.samples.custom_recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.moon.samples.R;
import com.moon.samples.base.BaseActivity;
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


        Button clearDataBtn = genericFindViewById(R.id.mulRecyclerViewwClearBtn);

        // 清空数据
        clearDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                adapter.notifyDataSetChanged();

                recyclerView.refreshComplete();
            }
        });

        // 重新加载
        findViewById(R.id.mulRecyclerViewwReLoadBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerView.autoRefresh();
            }
        });


        // 加载大量数据
        findViewById(R.id.mulRecyclerViewLoadBigDataBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addLargeData();


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

        emptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);


        recyclerView.autoRefresh();
    }

    @Override
    protected String getActionTitle() {
        return "recyclerView";
    }


    /**
     * 加载大量数据
     */
    private void addLargeData() {

        for (int i = 0; i < 100000; i++) {
            list.add(i + ",,,");
        }

        adapter.notifyDataSetChanged();

        recyclerView.smoothScroll2Position(adapter.getItemCount() - 1);

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
        }, 2000);


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
        }, 1000);


    }

}
