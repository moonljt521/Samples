package com.moon.samples.main.datarepository;

import android.arch.lifecycle.LiveData;

import com.moon.samples.main.MainBody;
import com.moon.samples.utils.UDebug;

import java.util.ArrayList;
import java.util.List;

/**
 * author: moon
 * created on: 17/11/17 下午5:27
 * description:
 */
public class DataRepository {


    private DataRepository(){

    }

    public static DataRepository getInstance(){
        return DataReposHolder.INSTANCE;
    }


    private static class DataReposHolder{
        private static final DataRepository INSTANCE = new DataRepository();

    }

    public LiveData<List<MainBody>> getMainItemList (String input){
        if (!"refresh".equals(input)){
            UDebug.i("refresh 不匹配。。。");
            return null;
        }

        List<MainBody> arr = new ArrayList<>();

        MainBody body = new MainBody(0, "自定义view");
        arr.add(body);
        body = new MainBody(1, "注解");
        arr.add(body);
        body = new MainBody(2, "rxJava2");
        arr.add(body);
        body = new MainBody(3, "DSBridge");
        arr.add(body);
        body = new MainBody(4, "rxjava2+retrofit2+JSOUP抓取html并解析");
        arr.add(body);
        body = new MainBody(5, "属性动画");
        arr.add(body);
        body = new MainBody(6, "bottomSheet");
        arr.add(body);
        body = new MainBody(7, "dataBinding");
        arr.add(body);
        body = new MainBody(8, "webView");
        arr.add(body);
        body = new MainBody(9, "定制RecyclerView");
        arr.add(body);
        body = new MainBody(10, "JNI和Java互调");
        arr.add(body);
        body = new MainBody(11, "android 事件分发机制");
        arr.add(body);
        body = new MainBody(12, "constraintLayout布局测试");
        arr.add(body);
        body = new MainBody(13, "onMeasure onLayout");
        arr.add(body);

        return (LiveData<List<MainBody>>) arr;
    }


}
