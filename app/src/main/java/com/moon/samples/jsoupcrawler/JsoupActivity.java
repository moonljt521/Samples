package com.moon.samples.jsoupcrawler;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.moon.samples.MyApplication;
import com.moon.samples.R;
import com.moon.samples.BaseActivity;
import com.moon.samples.utils.UDebug;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * jsoup 爬虫
 */
public class JsoupActivity extends BaseActivity {

    private List<String> mBannerUrls = new ArrayList<>();

    private IJSoupAPI api;

    private List<JSoupBody> dataList = new ArrayList<>();

    private JSoupAdapter adapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);

        recyclerView = (RecyclerView) findViewById(R.id.jSoupRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new JSoupAdapter(this, mBannerUrls, dataList));



        initConfig();

        retrofit2Request();

//        test();

    }

    private void test() {

        Observable.create((ObservableOnSubscribe<String>) o -> {

            try {
                Document parse = Jsoup.connect("http://www.jianshu.com/").get();
                Elements bannerElements = parse.getElementsByClass("banner");
                for (Element e : bannerElements) {
                    String png = e.getElementsByTag("img").attr("src");
                    o.onNext(png);
                }
            } catch (IOException e) {
                o.onError(null);
                e.printStackTrace();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        UDebug.i("s = " + s);

                    }

                    @Override
                    public void onError(Throwable e) {
                        UDebug.i("??????报错了");
                    }

                    @Override
                    public void onComplete() {

                    }
                });



        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(111);
                System.out.println(111222);

            }
        });



    }

    @Override
    protected String getActionTitle() {
        return "JSoup爬取数据并解析成列表";
    }

    private void retrofit2Request() {

        api.getList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Document parse = Jsoup.parse(responseBody.string());
                            Elements bannerElements = parse.getElementsByClass("banner");
                            for (Element e : bannerElements) {
                                String png = e.getElementsByTag("img").attr("src");
//                                UDebug.i("src = " + png);
                                mBannerUrls.add("http:" + png);
                            }

                            Elements contentElements = parse.getElementsByClass("content");
                            for (Element e : contentElements) {
                                JSoupBody body = new JSoupBody();
                                body.setTitle(e.getElementsByClass("title").text());
                                body.setContent(e.getElementsByClass("abstract").text());
                                dataList.add(body);
                            }

                            if (adapter != null) {
                                adapter.notifyDataSetChanged();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initConfig() {
        //沉浸式状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
//
//        //隐藏状态栏
//        if (getSupportActionBar()!=null){
//            getSupportActionBar().hide();
//        }

        //初始化 retrofit
        api = MyApplication.getMyApp().getRetrofit().create(IJSoupAPI.class);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startBanner();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopBanner();
        }
    }
}
