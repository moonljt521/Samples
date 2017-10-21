package com.moon.samples.rxjava2;

import android.os.Bundle;
import android.util.Log;

import com.moon.samples.R;
import com.moon.samples.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJava2Activity extends BaseActivity {
    int p = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java2);


        testZip();
    }

    @Override
    protected String getActionTitle() {
        return "rxJava2";
    }


    private void testZip(){
        Observable<Integer>  o1 =Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);

                e.onComplete();

            }
        }).subscribeOn(Schedulers.io());


        Observable<String>  o2 =Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("A");
                e.onNext("B");
                e.onNext("C");
            }
        }).subscribeOn(Schedulers.io());


        Observable.zip(o1, o2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void testFlatMap(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                for (int i = 0;i<3000;i++){
                    p++;

                    if (p == 100){
                        e.onNext(p);
                    }else if (p == 200){
                        e.onNext(p);
                    }
                }
                e.onNext(p);

            }
        }).flatMap(new Function<Integer, ObservableSource<Float>>() {
            @Override
            public ObservableSource<Float> apply(Integer integer) throws Exception {

                return nextOperation(integer);
            }
        }).subscribe(new Consumer<Float>() {
            @Override
            public void accept(Float s) throws Exception {
                Log.i("moon",s.toString());
            }
        });
    }


    private Observable<Float> nextOperation(Integer v){

//        return Observable.create(new ObservableOnSubscribe<Float>() {
//            @Override
//            public void subscribe(ObservableEmitter<Float> e) throws Exception {
//                e.onNext((float) (v.intValue() + 1));
//            }
//        });

        return Observable.create(e -> e.onNext((float) (v.intValue() + 1)));

    }


}
