package com.brownspy1.newsadmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class news_manupulate extends AppCompatActivity {
    ListView news_list;
    ImageView edit_btn;
    ImageView add_btn;
    ImageView delete_btn;
    LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news_manupulate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        news_list = findViewById(R.id.news_list);
        edit_btn = findViewById(R.id.edit_btn);
        add_btn = findViewById(R.id.add_btn);
        delete_btn = findViewById(R.id.delete_btn);

        get_news("hello");

        //button edit
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Click edit",Toast.LENGTH_SHORT).show();
            }
        });
        //button add
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Click add",Toast.LENGTH_SHORT).show();
            }
        });
        //button delete
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Click Delete",Toast.LENGTH_SHORT).show();
            }
        });


    }
    private  class News_Adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            layoutInflater = getLayoutInflater();
            View my_news = layoutInflater.inflate(R.layout.news_card,null);
            return my_news;
        }
    }

    private void get_news(String url){
        News_Adapter adapter = new News_Adapter();
        news_list.setAdapter(adapter);
    }
}