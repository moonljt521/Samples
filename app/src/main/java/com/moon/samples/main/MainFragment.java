package com.moon.samples.main;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.moon.samples.R;
import com.moon.samples.annotation.AnnotationActivity;
import com.moon.samples.bottomsheet.BottomSheetActivity;
import com.moon.samples.constraintlayout.ConstaintlayoutActivity;
import com.moon.samples.databinding.DataBindingDemoActivity;
import com.moon.samples.dispatch_event.TestDispatchTouchEventActivity;
import com.moon.samples.dsbridge.DSBridgeActivity;
import com.moon.samples.full_function_recyclerview.CustomRecyclerViewActivity;
import com.moon.samples.itemtouchhelper.ItemDragListener;
import com.moon.samples.itemtouchhelper.MyItemTouchHelperCallBack;
import com.moon.samples.jni.JniActivity;
import com.moon.samples.jsoupcrawler.JsoupActivity;
import com.moon.samples.main.adapter.MainAdapter;
import com.moon.samples.main.datarepository.DataRepository;
import com.moon.samples.main.listener.OnItemClickListener;
import com.moon.samples.main.viewmodel.MainViewModel;
import com.moon.samples.messenger_ipc.MessengerIPCActivity;
import com.moon.samples.onmeasure_onlayout.OnMeasure2OnLayoutActivity;
import com.moon.samples.propertyanimator.PropertyAnimatorActivity;
import com.moon.samples.rxjava2.RxJava2Activity;
import com.moon.samples.utils.Logger;
import com.moon.samples.handler_thread.HandlerThreadActivity;
import com.moon.samples.webview.NativeWebViewActivity;

import java.util.List;

/**
 * author: moon
 * created on: 17/11/17 下午4:09
 * description:
 */
public class MainFragment extends Fragment implements ItemDragListener, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerView;

    private MainAdapter adapter;
    private ItemTouchHelper itemTouchHelper;

    private MyItemTouchHelperCallBack callBack;

    private Activity activity;

    private MainViewModel mainViewModel = null;

    private final OnItemClickListener<MainBody> onItemClickListener = new OnItemClickListener<MainBody>() {
        @Override
        public void onClick(MainBody mainBody) {
            startIntent(mainBody.index);
        }
    };

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
        Logger.i("onAttach...");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.i("mainFragment -- onCreateView...");
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.i("mainFragment..onActivity..");
        subscribeUI();
    }


    @Override
    public void onDetach() {
        super.onDetach();

        Logger.i("onDetach...");
    }

    private void initViews(View view) {
        swipeRefreshLayout = view.findViewById(R.id.fragment_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = view.findViewById(R.id.fragment_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new ItemDecoration(2));
        recyclerView.setAdapter(adapter = new MainAdapter(activity, this, onItemClickListener));

        callBack = new MyItemTouchHelperCallBack(adapter);

        itemTouchHelper = new ItemTouchHelper(callBack);

        // 将 recyclerview 与 itemtouchhelper 绑定
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    private void subscribeUI() {
        // 注册生命周期
//        getLifecycle().addObserver(new MyLifecycleObserver());

        MainViewModel.Factory factory = new MainViewModel.Factory(activity.getApplication()
                , DataRepository.getInstance());

        mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);

        mainViewModel.getMainDataList().observe(this, new Observer<List<MainBody>>() {
            @Override
            public void onChanged(@Nullable List<MainBody> mainBodies) {

                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }

                if (adapter != null) {
                    adapter.refreshData(mainBodies);
                }
            }
        });
        mainViewModel.initMainData();
    }


    @Override
    public void onStartDrags(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onRefresh() {

        mainViewModel.refreshMainData();
    }

    private void startIntent(int position) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(activity, HandlerThreadActivity.class);

                break;

            case 1:
                intent.setClass(activity, AnnotationActivity.class);

                break;

            case 2:
                intent.setClass(activity, RxJava2Activity.class);

                break;
            case 3:
                intent.setClass(activity, DSBridgeActivity.class);

                break;

            case 4:
                intent.setClass(activity, JsoupActivity.class);

                break;
            case 5:
                intent.setClass(activity, PropertyAnimatorActivity.class);

                break;
            case 6:
                intent.setClass(activity, BottomSheetActivity.class);

                break;

            case 7:
                intent.setClass(activity, DataBindingDemoActivity.class);

                break;

            case 8:
                intent.setClass(activity, NativeWebViewActivity.class);

                break;

            case 9:
                intent.setClass(activity, CustomRecyclerViewActivity.class);

                break;

            case 10:

                intent.setClass(activity, JniActivity.class);

                break;

            case 11:

                intent.setClass(activity, TestDispatchTouchEventActivity.class);

                break;

            case 12:

                intent.setClass(activity, ConstaintlayoutActivity.class);

                break;

            case 13:
                intent.setClass(activity, OnMeasure2OnLayoutActivity.class);

                break;

            case 14:
                intent.setClass(activity, MessengerIPCActivity.class);

                break;

            default:
                Toast.makeText(activity.getApplicationContext(), "不知道你点了什么，反正不起作用", Toast.LENGTH_SHORT).show();

                break;
        }

        startActivity(intent);
    }

}
