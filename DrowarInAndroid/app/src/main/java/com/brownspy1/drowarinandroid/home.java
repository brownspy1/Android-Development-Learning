package com.brownspy1.drowarinandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class home extends Fragment {

    WebView umaars_home;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View my_viwe = inflater.inflate(R.layout.fragment_home, container, false);
        WebView umaars_home = my_viwe.findViewById(R.id.umaars_home);
        umaars_home.getSettings().setJavaScriptEnabled(true);

        umaars_home.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                // --- আপনার জাভাস্ক্রিপ্ট কোড ---

                // যদি ক্লাস নেম 'main-header' হয়
                String CLASS_TO_HIDE = "main-header";

                String javascript = "javascript:(function() { " +
                        "var header = document.getElementsByClassName('" + CLASS_TO_HIDE + "')[0];" +
                        "if (header) { " +
                        "header.style.display = 'none';" + // হেডার লুকিয়ে দাও
                        "}" +
                        "})()";

                view.loadUrl(javascript);
            }
        });

        umaars_home.loadUrl("https://umaars.com");
        return my_viwe;
    }
}