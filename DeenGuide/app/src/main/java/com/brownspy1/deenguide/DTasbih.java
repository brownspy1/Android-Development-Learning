package com.brownspy1.deenguide;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DTasbih extends AppCompatActivity {

    ImageView add,reset;
    TextView cnt;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dtasbih);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        add = findViewById(R.id.add_btn);
        reset = findViewById(R.id.reset_btn);
        cnt = findViewById(R.id.counter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                String bngaliCount = convertToBengaliNumber(count);
                cnt.setText(String.valueOf(bngaliCount));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                String bngaliCount = convertToBengaliNumber(count);
                cnt.setText(String.valueOf(bngaliCount));
                Toast.makeText(DTasbih.this, "Tasbih Count Reset", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public String convertToBengaliNumber(int number) {
        String[] banglaDigits = {"০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯"};
        String numStr = String.valueOf(number);
        StringBuilder banglaNumber = new StringBuilder();

        for (char digit : numStr.toCharArray()) {
            banglaNumber.append(banglaDigits[digit - '0']);
        }

        return banglaNumber.toString();
    }
}