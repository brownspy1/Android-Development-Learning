package com.brownspy1.technicaljadughor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    LinearLayout card_1 ,card_2,card_3;
    Button videoplayarrow ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        card_1 = findViewById(R.id.video_01);
        card_2 = findViewById(R.id.video_02);
        card_3 = findViewById(R.id.video_03);
        videoplayarrow = findViewById(R.id.videoplayarrow);
        // Set default video link
        card_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Video_play.video_link = "https://www.youtube.com/embed/Xk6heMqGHKE";
                Toast.makeText(MainActivity.this,"Palying video.....",Toast.LENGTH_LONG).show();
                Intent video_play = new Intent(MainActivity.this,Video_play.class);
                startActivity(video_play);
            }
        });
        card_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Video_play.video_link = "https://www.youtube.com/embed/naySsDoo2e0";
                Toast.makeText(MainActivity.this,"Palying video.....",Toast.LENGTH_LONG).show();
                Intent video_play = new Intent(MainActivity.this,Video_play.class);
                startActivity(video_play);
            }
        });
        card_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Video_play.video_link = "https://www.youtube.com/embed/YW4fWr9mDt0";
                Toast.makeText(MainActivity.this,"Palying video.....",Toast.LENGTH_LONG).show();
                Intent video_play = new Intent(MainActivity.this,Video_play.class);
                startActivity(video_play);
            }
        });
        videoplayarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Playing video.....",Toast.LENGTH_LONG).show();
                Intent paly = new Intent(MainActivity.this,Video_play.class);
                startActivity(paly);

            }
        });


    }
}