package com.moon.samples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.moon.samples.annotation.AnnotationActivity;
import com.moon.samples.dsbridge.DSBridgeActivity;
import com.moon.samples.jsoupcrawler.JsoupActivity;
import com.moon.samples.main.MainAdapter;
import com.moon.samples.main.MyDecoration;
import com.moon.samples.rxjava2.RxJava2Activity;
import com.moon.samples.viewcomponent.ViewcomponentActivity;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private String[] arr = {"自定义view", "注解", "rxJava2", "DSBridge","JSOUP抓取html并解析"};

    private RecyclerView recyclerView;


    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.main_fun_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter = new MainAdapter(this, arr));
        adapter.setMcListener(new MainAdapter.ViewItemListener() {
            @Override
            public void itemClick(View v, int position) {
                startIntent(position);
            }
        });
        // Example of a call to a native method

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

            case 4:

                intent.setClass(MainActivity.this, JsoupActivity.class);

                break;

            default:


                break;

        }

        startActivity(intent);

    }
}
