package com.moon.samples;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.moon.samples.annotation.AnnotationActivity;
import com.moon.samples.base.BaseActivity;
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
import com.moon.samples.main.MainFragment;
import com.moon.samples.main.adapter.MainAdapter;
import com.moon.samples.main.MainBody;
import com.moon.samples.onmeasure_onlayout.OnMeasure2OnLayoutActivity;
import com.moon.samples.propertyanimator.PropertyAnimatorActivity;
import com.moon.samples.rxjava2.RxJava2Activity;
import com.moon.samples.utils.CacheDataManager;
import com.moon.samples.utils.SettingUtil;
import com.moon.samples.viewcomponent.ViewcomponentActivity;
import com.moon.samples.webview.NativeWebViewActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity{


    private String totalSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        notLoadSlide = true;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // setactionbar icon
        if (getSupportActionBar()!=null){
            getSupportActionBar().setLogo(R.mipmap.page_icon_network);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container,MainFragment.getInstance())
                .commit();
    }



    @Override
    protected String getActionTitle() {
        return "主页";
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        getWindow();
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

            case R.id.action_night_Mode:
                changeNightMode();
                break;

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

    /**
     * 夜间模式
     */
    private void changeNightMode(){

        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (mode == Configuration.UI_MODE_NIGHT_YES) {
            SettingUtil.setIsNightMode(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            SettingUtil.setIsNightMode(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
        recreate();
    }
}
