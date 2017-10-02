package com.moon.samples.dsbridge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.moon.samples.R;
import com.moon.samples.utils.UDebug;

import wendu.dsbridge.DWebView;
import wendu.dsbridge.OnReturnValue;

public class DSBridgeActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsbridge);

        final DWebView webView= (DWebView) findViewById(R.id.webView);
        webView.setJavascriptInterface(new JsApi());
        webView.clearCache(true);
        webView.loadUrl("file:///android_asset/test.html");
//        webView.loadUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6YQR9G2znaeh1jNxFXmRUC5ZSM4T_AUBhVDKPXJ7BCz4CHpx62w");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.callHandler("addValue",new Object[]{1,"hello"},new OnReturnValue(){
                    @Override
                    public void onValue(String retValue) {
                        UDebug.i("call succeed,return value is "+retValue);
                    }
                });

                // webView.callHandler("test",null);
            }
        });


    }

    @Override
    protected String getActionTitle() {
        return "DSBridge";
    }


}
