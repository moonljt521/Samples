package com.moon.samples.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.moon.samples.R;

/**
 * author: moon
 * created on:
 * description: databing 使用 测试 ，代码只测试了textview和imageview的改变，但是期间出现了问题，无意间网上搜索了一个国外的https 前缀的图片地址，因为忘记了我的个人mac一直挂着vpn的
 * 显示没有问题，但手机一直不显示，导致研究了很久，修改了glide 的网络请求方式，自定义module也不起作用；
 *
 * databinding 可以在子线程修改数据，带动view的刷新，这点很好！！！
 */
public class DataBindingDemoActivity extends AppCompatActivity {

    private ActivityDataBindingDemoBinding binding;
    private NameBody name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = new NameBody();
        name.name.set("刻意练习");

        binding = DataBindingUtil.setContentView(this, R.layout
                .activity_data_binding_demo);

        binding.setMyHandler(new MyHandlers());

        binding.setName1(name);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    name.name.set("文字改变了");


//                    name.imageUrl.set("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6YQR9G2znaeh1jNxFXmRUC5ZSM4T_AUBhVDKPXJ7BCz4CHpx62w");

//                    binding.setUrl("http://i7.qhmsg.com/t01b48a6f15bf0cf5c1.jpg");
//                    binding.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506419549452&di=b9bbb3ff381fae079f7dd5bc6ffc072b&imgtype=0&src=http%3A%2F%2Fimg.it610.com%2Fimage%2Finfo2%2Fb3d4b17423c641b3993754b272631c11.jpg");
                    binding.setUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6YQR9G2znaeh1jNxFXmRUC5ZSM4T_AUBhVDKPXJ7BCz4CHpx62w");
//                    binding.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494585752563&di=df644f55457b30f672593d9c36d7865b&imgtype=0&src=http%3A%2F%2Fpic55.nipic.com%2Ffile%2F20141208%2F19462408_171130083000_2.jpg");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
