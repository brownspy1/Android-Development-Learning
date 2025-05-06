package com.brownspy1.deenguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
RelativeLayout tasbih,duah,waz,hadis,namaz,main;
Animation faid_in,zoom_in;
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
        tasbih = findViewById(R.id.click_tasbih);
        duah = findViewById(R.id.click_duah);
        waz = findViewById(R.id.click_waz);
        hadis = findViewById(R.id.click_hadis);
        namaz = findViewById(R.id.click_salat);
        main = findViewById(R.id.main);
        faid_in = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
        zoom_in = AnimationUtils.loadAnimation(MainActivity.this,R.anim.zoom_in);
        main.startAnimation(zoom_in);

        //tasbih
        tasbih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"আপনি এখন তাসবি পড়তে চলছেন!",Toast.LENGTH_SHORT).show();
                Intent digital_tasbih = new Intent(MainActivity.this,DTasbih.class);
                startActivity(digital_tasbih);
            }
        });

        //duah
        duah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"আপনি এখন দুআ পড়তে চলছেন!",Toast.LENGTH_SHORT).show();
                Intent duahpage = new Intent(MainActivity.this,Quotes.class);
                startActivity(duahpage);
            }
        });

        //waz
        waz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"আপনি এখন ওয়াজ সুনতে চলছেন!",Toast.LENGTH_SHORT).show();
                Intent wazpage = new Intent(MainActivity.this, Videos.class);
                startActivity(wazpage);
            }
        });

        //hadis
        hadis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"আপনি এখন হাদিস পড়তে চলছেন!",Toast.LENGTH_SHORT).show();
                Intent hadispage = new Intent(MainActivity.this, Browser.class);
                startActivity(hadispage);
            }
        });

        //namaz
        namaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namaz.startAnimation(faid_in);
                Toast.makeText(MainActivity.this,"আপনি এখন নামাজ শিখতে চলছেন!",Toast.LENGTH_SHORT).show();
                Intent namazpage = new Intent(MainActivity.this, Salat.class);
                startActivity(namazpage);
            }
        });
    }
}