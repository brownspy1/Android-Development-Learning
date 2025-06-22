package com.brownspy1.bdnews;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewsPlayground extends AppCompatActivity {
    ImageView newsImage,autoRead;
    TextView Titeltext,NewsDItailsText,catafornews;
    TextToSpeech playNews;
    public static Bitmap imageBit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news_playground);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        newsImage = findViewById(R.id.newsImage);
        autoRead = findViewById(R.id.autoRead);
        Titeltext = findViewById(R.id.Titeltext);
        NewsDItailsText = findViewById(R.id.NewsDItailsText);
        catafornews = findViewById(R.id.catafornews);

        playNews = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            }
        });


        Intent intent = getIntent();
        String catagoris = intent.getStringExtra("catagori");
        String Titel = intent.getStringExtra("Titel");
        String Discription = intent.getStringExtra("discription");
        String color = intent.getStringExtra("color");

        catafornews.setText(catagoris);
        catafornews.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
        if (imageBit!=null) newsImage.setImageBitmap(imageBit);
        Titeltext.setText(Titel);
        NewsDItailsText.setText(Discription);

        autoRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("STOP".equals(autoRead.getTag())) {
                    autoRead.setTag("Play");
                    autoRead.setImageResource(R.drawable.pause);
                    playNews.speak(Titel + Discription, TextToSpeech.QUEUE_FLUSH, null, "TTS_ID");
                } else if ("Play".equals(autoRead.getTag())) {
                    autoRead.setTag("STOP");
                    playNews.stop();
                    autoRead.setImageResource(R.drawable.baseline_mic_24);
                }
            }
        });

        playNews.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {

            }

            @Override
            public void onDone(String utteranceId) {
                runOnUiThread(() -> {
                    autoRead.setTag("STOP");
                    autoRead.setImageResource(R.drawable.baseline_mic_24);
                });

            }

            @Override
            public void onError(String utteranceId) {

            }
        });




    }
}