package com.brownspy1.castomdesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView cardView,Button;
    Animation FadeIn;
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
        cardView = findViewById(R.id.textView);
        Button = findViewById(R.id.Button);
        FadeIn = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
        cardView.startAnimation(FadeIn);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView.startAnimation(FadeIn);
                Intent pageTwo = new Intent(MainActivity.this,Url_Image_Lode.class);
                Toast.makeText(MainActivity.this,"Going image page",Toast.LENGTH_SHORT).show();
                startActivity(pageTwo);

            }
        });


    }
}