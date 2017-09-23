package com.moon.samples;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;

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

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static MyApplication getMyApp() {
        return myApp;
    }
}
