package com.moon.samples.webview;

import android.os.Bundle;
import android.webkit.WebView;

import com.moon.samples.R;
import com.moon.samples.BaseActivity;
import com.moon.samples.utils.UDebug;

public class NativeWebViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_web_view);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6YQR9G2znaeh1jNxFXmRUC5ZSM4T_AUBhVDKPXJ7BCz4CHpx62w");
    }

    @Override
    protected String getActionTitle() {
        return "webview";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
