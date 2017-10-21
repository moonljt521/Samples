package com.moon.samples.viewcomponent;

import android.os.Bundle;

import com.moon.samples.R;
import com.moon.samples.BaseActivity;

public class ViewcomponentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcomponent);
    }

    @Override
    protected String getActionTitle() {
        return "view";
    }
}
