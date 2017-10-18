package com.moon.samples.full_function_recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.moon.samples.R;
import com.moon.samples.dsbridge.BaseActivity;
import com.moon.samples.full_function_recyclerview.adapter.MulRecyclerViewAdapter;
import com.moon.samples.full_function_recyclerview.view.EmptyLayout;
import com.moon.samples.full_function_recyclerview.view.XRecyclerView;
import com.moon.samples.utils.UDebug;

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

//        emptyLayout = (EmptyLayout) View.inflate(this,R.layout.view_error_layout,null);
        emptyLayout = (EmptyLayout) findViewById(R.id.emptyView);

        recyclerView.setEmptyLayout(emptyLayout);

        Button clearBtn = (Button) findViewById(R.id.mulRecyclerViewwClearBtn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();

                adapter.notifyDataSetChanged();

                recyclerView.refreshComplete();
            }
        });

        // loading -----
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

        recyclerView.loading();

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

    private void refreshData() {

        recyclerView.loading();

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
        },2000);



    }

}
