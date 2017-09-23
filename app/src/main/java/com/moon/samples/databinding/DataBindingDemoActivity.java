package com.moon.samples.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.moon.samples.R;

/**
 * author: moon
 * created on:
 * description: databing 使用 测试
 */
public class DataBindingDemoActivity extends AppCompatActivity {

    private ActivityDataBindingDemoBinding binding;
    private NameBody name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = new NameBody();
        name.name.set("刻意练习");
//        name.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6YQR9G2znaeh1jNxFXmRUC5ZSM4T_AUBhVDKPXJ7BCz4CHpx62w");

        binding = DataBindingUtil.setContentView(this, R.layout
                .activity_data_binding_demo);

        binding.setMyHandler(new MyHandlers());

        binding.setName1(name);

//        binding.setUrl("http://i7.qhmsg.com/t01b48a6f15bf0cf5c1.jpg");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    name.name.set("xxx");
//                    name.imageUrl.set("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6YQR9G2znaeh1jNxFXmRUC5ZSM4T_AUBhVDKPXJ7BCz4CHpx62w");

                    binding.setUrl("http://i7.qhmsg.com/t01b48a6f15bf0cf5c1.jpg");

//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    });


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//                .start();
    }

    public void  onTaskClick() {

    }





}
