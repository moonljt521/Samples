package com.moon.samples.jsoupcrawler.presenter;

import com.ljq.mvpframework.presenter.BaseMvpPresenter;
import com.moon.samples.MyApplication;
import com.moon.samples.jsoupcrawler.model.IJSoupAPI;
import com.moon.samples.jsoupcrawler.model.JSoupBody;
import com.moon.samples.jsoupcrawler.view.JSoupView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * author: moon
 * created on: 17/12/16 下午5:44
 * description:
 */
public class JsoupPresenter extends BaseMvpPresenter<JSoupView>{

    private IJSoupAPI api;

    public JsoupPresenter() {
        api = MyApplication.getMyApp().getRetrofit().create(IJSoupAPI.class);
    }

    public void requestHttp(){

        if (getMvpView() !=null){
            getMvpView().requestLoading();
        }

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

                            List<String> mBannerUrls = new ArrayList<>();

                            for (Element e : bannerElements) {
                                String png = e.getElementsByTag("img").attr("src");
//                                Logger.i("src = " + png);
                                mBannerUrls.add("http:" + png);
                            }

                            Elements contentElements = parse.getElementsByClass("content");
                            List<JSoupBody> dataList = new ArrayList<>();
                            for (Element e : contentElements) {
                                JSoupBody body = new JSoupBody();
                                body.setTitle(e.getElementsByClass("title").text());
                                body.setContent(e.getElementsByClass("abstract").text());
                                dataList.add(body);
                            }


                            if (getMvpView() !=null){
                                getMvpView().requestSuccess(mBannerUrls,dataList);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getMvpView() !=null){
                            getMvpView().fail();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
