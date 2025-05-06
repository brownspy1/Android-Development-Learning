package com.brownspy1.deenguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeveloparInfo extends AppCompatActivity {
    ImageView facebook,instagram,linkedin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_developar_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        facebook = findViewById(R.id.btnFacebook);
        instagram = findViewById(R.id.btnInstagram);
        linkedin = findViewById(R.id.btnlinkdin);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/brownspy2"));
                startActivity(url);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/brownspy1"));
                startActivity(url);
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/brownspy1/"));
                startActivity(url);
            }
        });
    }
}