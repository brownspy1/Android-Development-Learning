package com.brownspy1.castomdesign;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class Url_Image_Lode extends AppCompatActivity {
    ImageView noImage;
    Button button;
    Animation FadeIn;
    int num = 99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.url_image_lode);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        noImage = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        FadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        //show default image
        Glide
                .with(this)
                .load("https://i.postimg.cc/50spxwxv/icons8-no-image-80.png")
                .placeholder(R.drawable.rounded_back)
                .into(noImage);

        // show image from url by clicking button

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noImage.startAnimation(FadeIn);
                num++;
                String imageURL = "https://picsum.photos/300/200?random="+num;
                Glide
                        .with(Url_Image_Lode.this)
                        .load(imageURL)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(noImage);
            }
        });
    }
}