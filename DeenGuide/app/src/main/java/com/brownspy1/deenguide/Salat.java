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
        salat.setWebViewClient(new WebViewClient());
        salat.loadUrl("https://brownspy1.github.io/Deen/Namaz.html");

    }
    @Override
    public void onBackPressed() {
        if (salat.canGoBack()) {
            salat.goBack();
        } else {
            super.onBackPressed();
        }
    }
}