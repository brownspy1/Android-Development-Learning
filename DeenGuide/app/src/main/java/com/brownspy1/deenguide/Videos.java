package com.brownspy1.deenguide;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Videos extends AppCompatActivity {
    WebView waz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_videos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        waz = findViewById(R.id.playWaz);
        waz.getSettings().setJavaScriptEnabled(true);
        waz.setWebViewClient(new WebViewClient());
        waz.loadUrl("https://brownspy1.github.io/Deen/video.html");

    }
    @Override
    public void onBackPressed() {
        if (waz.canGoBack()) {
            waz.goBack();
        } else {
            super.onBackPressed();
        }
    }
    }
