package com.moon.samples.arouter_a;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

import dongyin.com.arouter_moudle.looper.MainLooper;

/**
 * author: moon
 * created on: 17/12/19 下午3:39
 * description:
 */
@Interceptor(priority = 6)
public class Arouter_A_interceptor implements IInterceptor {

    Context context;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if ("/routerb/RouterBActivity".equals(postcard.getPath())){

            try {
                Thread.sleep(1500);
                postcard.withString("keyinterceptor","keyinterceptor_value");
                callback.onContinue(postcard);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void init(Context context) {
        this.context = context;
    }
}
