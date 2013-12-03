package com.example.untitled;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Browser;
import android.webkit.WebView;

/**
 * Created with IntelliJ IDEA.
 * User: Charm
 * Date: 17.10.13
 * Time: 3:29
 * To change this template use File | Settings | File Templates.
 */
public class showNews extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(link);
    }
}
