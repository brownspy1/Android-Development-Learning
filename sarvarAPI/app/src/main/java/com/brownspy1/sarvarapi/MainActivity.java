package com.brownspy1.sarvarapi;

import static android.view.View.GONE;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    TextView textapi;
    ProgressBar progressBar2;
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

        RequestQueue queue = Volley.newRequestQueue(this);
        textapi = findViewById(R.id.textapi);
        progressBar2 = findViewById(R.id.progressBar2);
        StringRequest StringRequest = new StringRequest(Request.Method.GET, "https://api.umaars.com/api.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textapi.setText("SarverRespons:     "+response);
                progressBar2.setVisibility(GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textapi.setText("SarverRespons:"+error);
                progressBar2.setVisibility(GONE);
            }
        });
        queue.add(StringRequest);

    }
}