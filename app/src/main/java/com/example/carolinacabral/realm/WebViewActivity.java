package com.example.carolinacabral.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = new WebView(WebViewActivity.this);
        webView.getSettings().setJavaScriptEnabled(true);

        //String pdfURL = "https://drive.google.com/file/d/1HumqgF77ZqEmMlF96nHB8B4Xgt8D1_OW/view?usp=sharing";


        webView.loadUrl("https://drive.google.com/file/d/1HumqgF77ZqEmMlF96nHB8B4Xgt8D1_OW/view?usp=sharing");

        setContentView(webView);
    }
}
