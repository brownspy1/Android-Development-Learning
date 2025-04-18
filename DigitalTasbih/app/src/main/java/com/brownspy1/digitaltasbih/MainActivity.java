package com.brownspy1.digitaltasbih;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.view.View;
import android.widget.Toast;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView add_btn,reset_btn;
    TextView counter,hide_text;
    Button go_to_next_page,developer;
    int count = 0;
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



        counter = findViewById(R.id.counter);
        add_btn = findViewById(R.id.add_btn);
        reset_btn = findViewById(R.id.reset_btn);
        go_to_next_page = findViewById(R.id.go_to_next_page);
        hide_text = findViewById(R.id.hide_text);


// this button for add tasbi count
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                counter.setText(String.valueOf(count));
            }
        });
// for reset tasbih count
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                counter.setText(String.valueOf(count));
            }
        });

        // go to next activity / page with toest
        go_to_next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Going to Amol page",Toast.LENGTH_LONG).show();
                hide_text.setVisibility(View.INVISIBLE);

                Intent my_intent = new Intent(MainActivity.this,AmolZikir.class);
                startActivity(my_intent);
            }
        });

    }
}