package com.moon.samples.arouter_a;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.moon.samples.R;
import com.moon.samples.base.BaseActivity;
import com.moon.samples.utils.Logger;

import dongyin.com.arouter_moudle.router_b.ARouterBody;
import dongyin.com.arouter_moudle.router_b.ObjParcelable;
import dongyin.com.arouter_moudle.router_b.Router_B_Activity;

/**
 * author: moon
 * created on:
 * description: 测试 阿里巴巴 arouter的使用， 这个是 调用页面A
 */
@Route(path = "/routerb/ARouter_A_Activity")
public class ARouter_A_Activity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter__a_);

        findViewById(R.id.arouter_btn1).setOnClickListener(this);
        findViewById(R.id.arouter_btn2).setOnClickListener(this);
        findViewById(R.id.arouter_btn3).setOnClickListener(this);
        findViewById(R.id.arouter_btn4).setOnClickListener(this);
        findViewById(R.id.arouter_btn5).setOnClickListener(this);

    }

    @Override
    protected String getActionTitle() {
        return "测试阿里的arouter";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.arouter_btn1:
                // activity 直跳 无参数
                ARouter.getInstance().build("/routerb/RouterBActivity")
                        .navigation(this, new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {

                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                Toast.makeText(getApplicationContext(),"您被拦截了",Toast.LENGTH_SHORT).show();

                            }
                        });


                break;
            case R.id.arouter_btn2:
                // activity 直跳 有参数

                ARouterBody aRouterBody = new ARouterBody();
                aRouterBody.setAge(12);
                aRouterBody.setId(102);
                aRouterBody.setName("xiaomi");

                ObjParcelable objParcelable = new ObjParcelable();
                objParcelable.setAge(1992);

                ARouter.getInstance().build("/routerb/RouterBActivity")
                        .withString("key1","ljt")
                        .withSerializable("key2",  aRouterBody)
                        .withParcelable("key3",objParcelable)
                        .navigation();

                break;

            case R.id.arouter_btn3:
                // activity 跳转 然后被回调
                ARouter.getInstance().build("/routerb/RouterBActivity")
                        .navigation(ARouter_A_Activity.this,100);

                break;

            case R.id.arouter_btn4:
                // activity 跳转  动画
//                ARouter.getInstance().build("/routerb/RouterBActivity")
//                        .withTransition(R.anim.slide_in_bottom,R.anim.slide_out_bottom)
//                        .greenChannel()
//                        .navigation();

//                Intent intent = new Intent(this, Router_B_Activity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_bottom);


                if (Build.VERSION.SDK_INT >= 16) {
                    ActivityOptionsCompat compat = ActivityOptionsCompat.
                            makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);

                    ARouter.getInstance()
                            .build("/routerb/RouterBActivity")
                            .withOptionsCompat(compat)
                            .greenChannel()
                            .navigation();
                } else {
                    Toast.makeText(getApplicationContext(), "API < 16,不支持新版本动画", Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.arouter_btn5:
                // 获取服务



                break;

            default:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 123){

            Logger.i("ARouter_A_Activity......onActivityResult");

            if (data !=null){

                Toast.makeText(getApplicationContext(),"回传参数为："+ data.getStringExtra("callback")
                ,Toast.LENGTH_SHORT).show();

            }

        }
    }
}
