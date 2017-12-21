package com.moon.samples;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.moon.samples.uncaughthandler.CrashHandler;
import com.squareup.leakcanary.LeakCanary;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xiaofei.library.hermeseventbus.HermesEventBus;

/**
 * author: moon
 * created on: 17/9/4 下午4:31
 * description:
 */
public class MyApplication extends Application {

    private String baseUrl = "http://www.jianshu.com/";
//    private String baseUrl = "http://api.laifudao.com/open/";

    private Retrofit retrofit;

    private static MyApplication myApp;

    private long start =0;

    @Override
    public void onCreate() {
        super.onCreate();

        myApp = this;

        HermesEventBus.getDefault().init(this);

        if (!BuildConfig.DEBUG){
            new CrashHandler(){

                @Override
                public void doWithAfterCrash() {

                    Intent intent = new Intent(myApp,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    myApp.startActivity(intent);

                }
            }.init(this);
        }

        configLeakCanary();
//        Gson gson = new GsonBuilder()
//                //配置你的Gson
//                .set
//                .create();
        OkHttpClient client=new OkHttpClient();
        client.newBuilder().connectTimeout(4, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(baseUrl)
                .build();

        //让Glide能用HTTPS

//        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    private void configLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return ;
        }
        LeakCanary.install(this);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static MyApplication getMyApp() {
        return myApp;
    }

    public long getStart() {
        return start;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
