package com.moon.samples.blockingqueue;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.moon.samples.base.BaseActivity;
import com.moon.samples.R;

public class BlockingQueueActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private BlockingQueueAdapter adapter;

    private Button addOne, addAll, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocking_queue);

        recyclerView = findViewById(R.id.blocking_rv);
        addOne = findViewById(R.id.blocking_add);
        addAll = findViewById(R.id.blocking_addall);
        clear = findViewById(R.id.blocking_clear);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new BlockingQueueAdapter());

        addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.add20();

                recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);

            }
        });


        addAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setScrolled(new BlockingQueueAdapter.IScrolled() {
                    @Override
                    public void scroll() {
                        if (adapter.getItemCount() > 0) {
                            recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);

                        }
                    }
                });
                adapter.executeQueue();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clearList();
            }
        });

        findViewById(R.id.blocking_tobottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
            }
        });

        findViewById(R.id.stopQueue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.stopQueue();

            }
        });


    }

    @Override
    protected String getActionTitle() {
        return "阻塞队列(模拟直播间高并发消息显示)";
    }
}
