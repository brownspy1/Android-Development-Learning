package com.brownspy1.deenguide;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Browser extends AppCompatActivity {
    WebView hadis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_browser);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        hadis = findViewById(R.id.hadisview);
        hadis.getSettings().setJavaScriptEnabled(true);
        hide();
        hadis.loadUrl("https://messagebd.net/hadith");
    }
    public void hide(){
        hadis.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                hadis.loadUrl("javascript:(function() { " +
                        // Navbar, header, footer hide
                        "var nav = document.querySelector('nav'); if(nav) { nav.style.display='none'; }" +
                        "var header = document.querySelector('header'); if(header) { header.style.display='none'; }" +
                        "var footer = document.querySelector('footer'); if(footer) { footer.style.display='none'; }" +

                        // Body background color
                        "document.body.style.backgroundColor = '#079ba8';" +

                        // Align all content to center
                        "document.body.style.textAlign = 'center';" +
                        "document.body.style.margin = '0 auto';" +
                        "var all = document.querySelectorAll('div, p, span, section, article');" +
                        "for (var i = 0; i < all.length; i++) {" +
                        "all[i].style.margin = '0 auto';" +
                        "all[i].style.textAlign = 'center';" +
                        "}" +
                        "})()");
            }
        });
    }
    @Override
    public void onBackPressed(){
        if (hadis.canGoBack()){
            hadis.goBack();
            hadis.postDelayed(() -> {
                hadis.loadUrl("javascript:(function() { " +
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
        }else {
            super.onBackPressed();
        }
    }

}