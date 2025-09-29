package com.brownspy1.app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView newslist;
    ImageView banar_image;
    LayoutInflater layoutInflater;


    //store all news from databas API url
    ArrayList<HashMap<String,String>> newses_db = new ArrayList<>();
    HashMap<String,String> news_db;


    //--------------------------------------------------------------------------------------------
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

        newslist = findViewById(R.id.list_items);
        banar_image = findViewById(R.id.banar_image);
        //this function creted by mahadi this function call jason from api url
        request_news("http://192.168.0.105/PHP/News_Admin/read_news.php");





    }




    private class NewsAdaptar extends BaseAdapter{

        @Override
        public int getCount() {
            return newses_db.size();
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
            View news_viwe = layoutInflater.inflate(R.layout.layout,null);

            ImageView image_url = news_viwe.findViewById(R.id.usarImage);
            TextView catagoris = news_viwe.findViewById(R.id.catagoris);
            TextView newsTitel = news_viwe.findViewById(R.id.newsTitel);
            TextView newsDisc = news_viwe.findViewById(R.id.newsDisc);

            news_db = newses_db.get(i);
            String created = news_db.get("created");
            String news_id = news_db.get("news_id");
            String catagory = news_db.get("catagory");
            String titel = news_db.get("titel");
            String news = news_db.get("news");
            String image = news_db.get("image_url");

            catagoris.setText(catagory);
            newsTitel.setText(titel);
            newsDisc.setText(news);

            return news_viwe;
        }
    }

    //-----------------------------------------------------------------------------------------------------
    void request_news(String url){
        //request to api url get all news
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++){
                    try {
                        JSONObject item = jsonArray.getJSONObject(i);
                        String created   =item.getString("created");
                        String news_id   =item.getString("news_id");
                        String catagory  =item.getString("catagory");
                        String titel     =item.getString("titel");
                        String news      =item.getString("news");
                        String image_url =item.getString("image");

                        //add server respons to hash map
                        news_db = new HashMap<>();
                        news_db.put("created",created);
                        news_db.put("news_id",news_id);
                        news_db.put("catagory",catagory);
                        news_db.put("titel",titel);
                        news_db.put("news",news);
                        news_db.put("image_url",image_url);

                        //add hash map to arry list
                        newses_db.add(news_db);


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                };
                //Now make and add adaptar in list viwe
                if(newses_db.size()>0){
                    NewsAdaptar NewsAdaptar = new NewsAdaptar();
                    newslist.setAdapter(NewsAdaptar);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("not lode ",volleyError.toString());
            }
        });
        //Rerquest que [ami ja amar kaj korte bolsi sai jonno ai queue te add korte hobe]
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}