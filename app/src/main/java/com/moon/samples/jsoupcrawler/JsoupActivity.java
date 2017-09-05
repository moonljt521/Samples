package com.moon.samples.jsoupcrawler;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.moon.samples.MyApp;
import com.moon.samples.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

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
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * jsoup 爬虫
 */
public class JsoupActivity extends AppCompatActivity {

    private Banner banner ;

    private List<String> urls = new ArrayList<>();

    private IJsoupAPI api ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);

//        urls.add("http://img3.imgtn.bdimg.com/it/u=2474696812,1208726339&fm=27&gp=0.jpg");
//        urls.add("http://img3.imgtn.bdimg.com/it/u=2474696812,1208726339&fm=27&gp=0.jpg");
//        urls.add("http://img3.imgtn.bdimg.com/it/u=2474696812,1208726339&fm=27&gp=0.jpg");
//        urls.add("http://img3.imgtn.bdimg.com/it/u=2474696812,1208726339&fm=27&gp=0.jpg");

        banner = (Banner) findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());


        // setting
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(null);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        initConfig();

        getHttpHtmlString();

    }

    private void getHttpHtmlString() {


        api.getList()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Document parse = Jsoup.parse(responseBody.string());

                            Elements list = parse.getElementsByTag("link");
                            for (Element e : list){
                                String png = e.attr("href");
                                Log.i("moon","href = "+ png);
                                if (!TextUtils.isEmpty(png) && png.endsWith("png")){
                                    urls.add("http:"+png);
                                }
                            }

                            banner.setImages(urls);

                            banner.start();


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

    private void initConfig(){
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        //隐藏状态栏
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        //初始化 retrofit
        api = MyApp.getMyApp().getRetrofit().create(IJsoupAPI.class);

    }




    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

        }

//        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//        @Override
//        public ImageView createImageView(Context context) {
//            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
//            return simpleDraweeView;
//        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (banner!=null){
            banner.startAutoPlay();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (banner!=null){
            banner.stopAutoPlay();
        }
    }
}
