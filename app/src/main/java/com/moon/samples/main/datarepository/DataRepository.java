package com.moon.samples.main.datarepository;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import com.moon.samples.main.MainBody;
import com.moon.samples.utils.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * author: moon
 * created on: 17/11/17 下午5:27
 * description:
 */
public class DataRepository {

    private List<MainBody> arr = new ArrayList<>();
    private MutableLiveData<List<MainBody>> mutableLiveData = new MutableLiveData<>();

    private int refreshCount;


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
            Logger.i("input = " + input);
            return getChangeList();
        }

        arr.clear();

        MainBody body;
        body = new MainBody(0, "测试HandlerThread");
        arr.add(body);
        body = new MainBody(1, "注解");
        arr.add(body);
        body = new MainBody(2, "rxJava2");
        arr.add(body);
        body = new MainBody(3, "DSBridge");
        arr.add(body);
        body = new MainBody(4, "rxjava2+retrofit2+JSOUP");
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
        body = new MainBody(14, "messenger IPC");
        arr.add(body);
        body = new MainBody(15, "学习Scroller用法");
        arr.add(body);
        body = new MainBody(16, "bindservice");
        arr.add(body);
        body = new MainBody(17, "测试ARouter的用法");
        arr.add(body);
        body = new MainBody(18, "recyclerView动画");
        arr.add(body);
        body = new MainBody(19, "学习下嵌套滚动");
        arr.add(body);
        body = new MainBody(20, "文件下载");
        arr.add(body);

        mutableLiveData.setValue(arr);
        return mutableLiveData;
    }

    public LiveData<List<MainBody>> getChangeList(){

        if (arr.size()>0){
            refreshCount ++ ;
            arr.get(0).refreshCount = refreshCount;
        }

        mutableLiveData.setValue(arr);
        return mutableLiveData;
    }

}
