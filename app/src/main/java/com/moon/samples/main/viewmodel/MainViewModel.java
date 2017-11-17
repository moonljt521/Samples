package com.moon.samples.main.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.moon.samples.main.MainBody;
import com.moon.samples.main.datarepository.DataRepository;

import java.util.List;


/**
 * author: moon
 * created on: 17/11/17 下午4:59
 * description:
 */
public class MainViewModel extends AndroidViewModel {

    // 请求接口中查询的日期参数
    private MutableLiveData<String> mZhihuPageDate = new MutableLiveData<>();

    private LiveData<List<MainBody>> mainDataList ;

    private DataRepository mDataRepository;

    public MainViewModel(Application application,DataRepository repository) {
        super(application);
        this.mDataRepository = repository;

        mainDataList = Transformations.switchMap(mZhihuPageDate,new Function<String,LiveData<List<MainBody>>>(){

            @Override
            public LiveData<List<MainBody>> apply(String s)  {
                return mDataRepository.getMainItemList(s);
            }
        });

    }

    public LiveData<List<MainBody>> getMainDataList() {
        return mainDataList;
    }

    public void refreshMainData(){
        mZhihuPageDate.setValue("refresh");
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private final Application mApp ;
        private DataRepository repository;

        public Factory(Application application, DataRepository repository) {
            mApp = application;
            this.repository = repository;
        }


        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new MainViewModel(mApp,this.repository);
        }
    }


}
