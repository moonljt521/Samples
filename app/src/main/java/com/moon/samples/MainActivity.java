package com.moon.samples;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.moon.samples.base.BaseActivity;
import com.moon.samples.main.MainFragment;
import com.moon.samples.utils.CacheDataManager;
import com.moon.samples.utils.Logger;
import com.moon.samples.utils.SettingUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xiaofei.library.hermeseventbus.HermesEventBus;

public class MainActivity extends BaseActivity{


    private String totalSize;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        notLoadSlide = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HermesEventBus.getDefault().register(this);

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showAnotherProcess(String s){
        Logger.i(">>>" + s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        HermesEventBus.getDefault().unregister(this);
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
