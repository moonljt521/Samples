package com.moon.samples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.moon.samples.annotation.AnnotationActivity;
import com.moon.samples.dsbridge.BaseActivity;
import com.moon.samples.dsbridge.DSBridgeActivity;
import com.moon.samples.itemtouchhelper.ItemDragListener;
import com.moon.samples.itemtouchhelper.MyItemTouchHelperCallBack;
import com.moon.samples.jsoupcrawler.JsoupActivity;
import com.moon.samples.main.ItemDecoration;
import com.moon.samples.main.MainAdapter;
import com.moon.samples.main.MainBody;
import com.moon.samples.propertyanimator.PropertyAnimatorActivity;
import com.moon.samples.rxjava2.RxJava2Activity;
import com.moon.samples.utils.UDebug;
import com.moon.samples.viewcomponent.ViewcomponentActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ItemDragListener {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

//    private String[] arr = {"自定义view", "注解", "rxJava2", "DSBridge", "rxjava2+retrofit2+JSOUP抓取html并解析", "属性动画"};

    private List<MainBody> arr = new ArrayList<>();

    private RecyclerView recyclerView;

    private MainAdapter adapter;
    private ItemTouchHelper itemTouchHelper;

    private MyItemTouchHelperCallBack callBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        notLoadSlide = true;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setData();

        recyclerView = (RecyclerView) findViewById(R.id.main_fun_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new ItemDecoration(2));
        recyclerView.setAdapter(adapter = new MainAdapter(this, arr, this));
        adapter.setMcListener(new MainAdapter.ViewItemListener() {
            @Override
            public void itemClick(View v, int position) {

                UDebug.i(">>>>>"+arr.get(position).index);

                startIntent(arr.get(position).index);
            }
        });

        callBack = new MyItemTouchHelperCallBack(adapter);

        itemTouchHelper = new ItemTouchHelper(callBack);

        // 将 recyclerview 与 itemtouchhelper 绑定
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private void setData() {

        MainBody body = new MainBody(0,"自定义view");
        arr.add(body);
        body = new MainBody(1,"注解");
        arr.add(body);
        body = new MainBody(2,"rxJava2");
        arr.add(body);
        body = new MainBody(3,"DSBridge");
        arr.add(body);
        body = new MainBody(4,"rxjava2+retrofit2+JSOUP抓取html并解析");
        arr.add(body);
        body = new MainBody(5,"属性动画");
        arr.add(body);

    }

    @Override
    protected String getActionTitle() {
        return "主页";
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

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

            case 5:
                intent.setClass(MainActivity.this, PropertyAnimatorActivity.class);

                break;
            case 4:
                intent.setClass(MainActivity.this, JsoupActivity.class);

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
}
