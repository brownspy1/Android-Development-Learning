package com.brownspy1.technicaljadughor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Video_play extends AppCompatActivity {
    WebView webView;
    LinearLayout card_1 ,card_2,card_3;
    public static String video_link = "https://www.youtube.com/embed/HcgxRnofkls";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video_play);

        // Initialize WebView after setting the content view
        webView = findViewById(R.id.webVideo);
        card_1 = findViewById(R.id.video_01);
        card_2 = findViewById(R.id.video_02);
        card_3 = findViewById(R.id.video_03);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set default video link
        card_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video_link = "https://www.youtube.com/embed/Xk6heMqGHKE";
                Toast.makeText(Video_play.this,"Palying video.....",Toast.LENGTH_LONG).show();
                webView.loadUrl(video_link);
            }
        });


        card_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video_link = "https://www.youtube.com/embed/naySsDoo2e0";
                Toast.makeText(Video_play.this,"Palying video.....",Toast.LENGTH_LONG).show();
                webView.loadUrl(video_link);
            }
        });
        // Set the video link for card_3
        card_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                video_link = "https://www.youtube.com/embed/YW4fWr9mDt0";
                Toast.makeText(Video_play.this,"Palying video.....",Toast.LENGTH_LONG).show();
                webView.loadUrl(video_link);
            }
        });

        // Enable JavaScript and load the video link
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(video_link);
    }
}