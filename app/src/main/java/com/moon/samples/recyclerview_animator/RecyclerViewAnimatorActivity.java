package com.moon.samples.recyclerview_animator;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.moon.samples.R;
import com.moon.samples.base.BaseActivity;
import com.moon.samples.recyclerview_animator.animator_adapter.SlideInRightAnimationAdapter;
import com.moon.samples.recyclerview_animator.animators.ScaleInRightAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * recyclerView动画
 */
public class RecyclerViewAnimatorActivity extends BaseActivity {

    RecyclerView recyclerView;
    Button addBtn, addAllBtn;

    AnimationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_animator);

        recyclerView = findViewById(R.id.recycler_animation_list);

        addBtn = findViewById(R.id.recycler_animation_add);
        addAllBtn = findViewById(R.id.recycler_animation_addall);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        SlideInRightAnimationAdapter animationAdapter = new SlideInRightAnimationAdapter(adapter = new
                AnimationAdapter(null));

        animationAdapter.setFirstOnly(true);
        recyclerView.setAdapter(animationAdapter);

        //自定义动画
        recyclerView.setItemAnimator(new ScaleInRightAnimator());
        // 自定义点击事件
        adapter.setMcListener(new AnimationAdapter.ViewItemListener() {
            @Override
            public void itemClick(View v, int position) {

                adapter.removeData(position);

            }
        });

       List<String> list = initDatas();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addData("sdkfllekf");
            }
        });

        addAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.initData(list);
            }
        });
    }

    private List<String> initDatas(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(">>>>>>" + i);
        }
        return list;
    }

    @Override
    protected String getActionTitle() {
        return "recyclerView的动画";
    }


    private void setData() {

    }

}
