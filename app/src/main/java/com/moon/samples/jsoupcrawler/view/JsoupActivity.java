package com.moon.samples.jsoupcrawler.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ljq.mvpframework.factory.CreatePresenter;
import com.ljq.mvpframework.factory.PresenterMvpFactoryImpl;
import com.ljq.mvpframework.view.AbstractMvpAppCompatActivity;
import com.moon.samples.R;
import com.moon.samples.jsoupcrawler.model.JSoupAdapter;
import com.moon.samples.jsoupcrawler.model.JSoupBody;
import com.moon.samples.jsoupcrawler.presenter.JsoupPresenter;
import com.moon.samples.jsoupcrawler.view.JSoupView;
import com.moon.samples.utils.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * jsoup 爬虫
 */
@CreatePresenter(JsoupPresenter.class)
public class JsoupActivity extends AbstractMvpAppCompatActivity<JSoupView,JsoupPresenter> implements JSoupView{

    private JSoupAdapter adapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);

        recyclerView = (RecyclerView) findViewById(R.id.jSoupRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new JSoupAdapter());


    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpPresenter().requestHttp();

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
                        Logger.i("s = " + s);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.i("??????报错了");
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    protected String getActionTitle() {
        return "JSoup爬取数据并解析成列表";
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

    @Override
    public void requestLoading() {
        Toast.makeText(getApplicationContext(),"数据加载中...",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSuccess(List<String> mBannerList, List<JSoupBody> mDataList) {
        if (adapter !=null){
            adapter.refreshData(mBannerList,mDataList);
        }

    }

    @Override
    public void fail() {
        Toast.makeText(getApplicationContext(),"加载失败",Toast.LENGTH_SHORT).show();
    }
}
