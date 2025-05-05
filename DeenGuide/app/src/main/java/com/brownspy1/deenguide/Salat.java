package com.brownspy1.deenguide;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Salat extends AppCompatActivity {
    WebView salat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_salat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        salat = findViewById(R.id.webnamazsikkah);
        salat.getSettings().setJavaScriptEnabled(true);
        salat.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                salat.loadUrl("javascript:(function() { " +
                        // Remove header, footer, nav
                        "var nav = document.querySelector('nav'); if(nav) { nav.style.display='none'; }" +
                        "var header = document.querySelector('header'); if(header) { header.style.display='none'; }" +
                        "var footer = document.querySelector('footer'); if(footer) { footer.style.display='none'; }" +

                        // Add custom font from assets
                        "var style = document.createElement('style');" +
                        "style.innerHTML = \"@font-face { font-family: 'MyFont'; src: url('file:///android_asset/fonts/hindsiliguri_light.ttf'); } " +
                        "body { font-family: 'MyFont' !important; background-color: #079ba8; }\";" +
                        "document.head.appendChild(style);" +
                        "})()");
            }
        });
        salat.loadUrl("https://sites.google.com/view/asaduzzaman/islamic/%E0%A6%B8%E0%A6%B9%E0%A6%9C-%E0%A6%A8%E0%A6%AE%E0%A6%9C-%E0%A6%B6%E0%A6%95%E0%A6%B7");

    }
    @Override
    public void onBackPressed(){
        if(salat.canGoBack()){
            salat.goBack();
            salat.postDelayed(() -> {
                salat.loadUrl("javascript:(function() { " +
                        "var nav = document.querySelector('nav'); if(nav) { nav.style.display='none'; }" +
                        "var header = document.querySelector('header'); if(header) { header.style.display='none'; }" +
                        "var footer = document.querySelector('footer'); if(footer) { footer.style.display='none'; }" +
                        "document.body.style.backgroundColor = '#079ba8';" +
                        "document.body.style.textAlign = 'center';" +
                        "document.body.style.margin = '0 auto';" +
                        "var all = document.querySelectorAll('div, p, span, section, article');" +
                        "for (var i = 0; i < all.length; i++) {" +
                        "all[i].style.margin = '0 auto';" +
                        "all[i].style.textAlign = 'center';" +
                        "}" +
                        "})()");
            }, 500);
        }
        else {
            super.onBackPressed();
        }
    }
}