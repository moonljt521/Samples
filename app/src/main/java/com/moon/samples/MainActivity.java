package com.moon.samples;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
import com.moon.samples.main.ItemDecoration;
import com.moon.samples.main.MainAdapter;
import com.moon.samples.main.MainBody;
import com.moon.samples.onmeasure_onlayout.OnMeasure2OnLayoutActivity;
import com.moon.samples.propertyanimator.PropertyAnimatorActivity;
import com.moon.samples.rxjava2.RxJava2Activity;
import com.moon.samples.utils.CacheDataManager;
import com.moon.samples.utils.CacheManager;
import com.moon.samples.utils.UDebug;
import com.moon.samples.utils.UiUtils;
import com.moon.samples.viewcomponent.ViewcomponentActivity;
import com.moon.samples.webview.NativeWebViewActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ItemDragListener {

    private List<MainBody> arr = new ArrayList<>();

    private RecyclerView recyclerView;

    private MainAdapter adapter;
    private ItemTouchHelper itemTouchHelper;

    private MyItemTouchHelperCallBack callBack;

    private String totalSize ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        notLoadSlide = true;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setData();

        // setactionbar icon
        if (getSupportActionBar()!=null){
            getSupportActionBar().setLogo(R.mipmap.page_icon_network);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }

        recyclerView = (RecyclerView) findViewById(R.id.main_fun_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new ItemDecoration(2));
        recyclerView.setAdapter(adapter = new MainAdapter(this, arr, this));
        adapter.setMcListener(new MainAdapter.ViewItemListener() {
            @Override
            public void itemClick(View v, int position) {
                startIntent(arr.get(position).index);
            }
        });


        callBack = new MyItemTouchHelperCallBack(adapter);

        itemTouchHelper = new ItemTouchHelper(callBack);

        // 将 recyclerview 与 itemtouchhelper 绑定
        itemTouchHelper.attachToRecyclerView(recyclerView);




    }


    private void setData() {

        MainBody body = new MainBody(0, "自定义view");
        arr.add(body);
        body = new MainBody(1, "注解");
        arr.add(body);
        body = new MainBody(2, "rxJava2");
        arr.add(body);
        body = new MainBody(3, "DSBridge");
        arr.add(body);
        body = new MainBody(4, "rxjava2+retrofit2+JSOUP抓取html并解析");
        arr.add(body);
        body = new MainBody(5, "属性动画");
        arr.add(body);
        body = new MainBody(6, "bottomSheet");
        arr.add(body);
        body = new MainBody(7, "dataBinding");
        arr.add(body);
        body = new MainBody(8, "webView");
        arr.add(body);
        body = new MainBody(9, "定制RecyclerView");
        arr.add(body);
        body = new MainBody(10, "JNI和Java互调");
        arr.add(body);
        body = new MainBody(11, "android 事件分发机制");
        arr.add(body);
        body = new MainBody(12, "constraintLayout布局测试");
        arr.add(body);
        body = new MainBody(13, "onMeasure onLayout");
        arr.add(body);
    }

    @Override
    protected String getActionTitle() {
        return "主页";
    }

    private void startIntent(int position) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(MainActivity.this, ViewcomponentActivity.class);

                break;

            case 1:
                intent.setClass(MainActivity.this, AnnotationActivity.class);

                break;

            case 2:
                intent.setClass(MainActivity.this, RxJava2Activity.class);

                break;
            case 3:
                intent.setClass(MainActivity.this, DSBridgeActivity.class);

                break;

            case 4:
                intent.setClass(MainActivity.this, JsoupActivity.class);

                break;
            case 5:
                intent.setClass(MainActivity.this, PropertyAnimatorActivity.class);

                break;
            case 6:
                intent.setClass(MainActivity.this, BottomSheetActivity.class);

                break;

            case 7:
                intent.setClass(MainActivity.this, DataBindingDemoActivity.class);

                break;

            case 8:
                intent.setClass(MainActivity.this, NativeWebViewActivity.class);

                break;

            case 9:
                intent.setClass(MainActivity.this, CustomRecyclerViewActivity.class);

                break;

            case 10:

                intent.setClass(MainActivity.this, JniActivity.class);

                break;

            case 11:

                intent.setClass(MainActivity.this, TestDispatchTouchEventActivity.class);

                break;

            case 12:

                intent.setClass(MainActivity.this, ConstaintlayoutActivity.class);

                break;

            case 13:
                intent.setClass(MainActivity.this, OnMeasure2OnLayoutActivity.class);

                break;

            default:
                Toast.makeText(getApplicationContext(), "不知道你点了什么，反正不起作用", Toast.LENGTH_SHORT).show();

                break;
        }

        startActivity(intent);
    }

    @Override
    public void onStartDrags(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onStart() {
        super.onStart();


//        UiUtils.traversalView(findViewById(R.id.main_layout));

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_compose:

                break;

            case R.id.action_delete:



                try {
                    totalSize = CacheDataManager.getTotalCacheSizeWithGlide(getApplicationContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }


                new AlertDialog.Builder(MainActivity.this).setTitle("清理缓存？")
                        .setMessage("当前缓存大小为："+totalSize )
                        .setPositiveButton("清除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                CacheDataManager.clearAllCache(getApplicationContext());

                                Toast.makeText(getApplicationContext(),"清理结束。。。",Toast.LENGTH_LONG).show();

                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();


                break;
        }

        return true ;
    }
}
