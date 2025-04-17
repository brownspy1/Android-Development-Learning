package com.brownspy1.barisalcity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Find views inside onCreate after setContentView
        Button click_to_change = findViewById(R.id.clik_to_change);
        TextView crwe_name = findViewById(R.id.crew_name);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        click_to_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crwe_name.setText("This is new text");
            }
        });


        crwe_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_to_change.setText("HAHAHAAHAHHAHHAH");
            }
        });
    }
}
