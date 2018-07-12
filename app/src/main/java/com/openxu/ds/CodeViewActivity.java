package com.openxu.ds;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.openxu.oxlib.base.BaseActivity;

public class CodeViewActivity extends BaseActivity {

    WebView webView;
    private String file;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_codeview;
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.webView);
        //得到webview设置
        WebSettings webSettings = webView.getSettings();
        //允许使用javascript
        webSettings.setJavaScriptEnabled(true);
        //设置字符编码
        webSettings.setDefaultTextEncodingName("UTF-8");
        //支持缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        file = getIntent().getStringExtra("file");
        webView.loadUrl(file);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
    }



}
