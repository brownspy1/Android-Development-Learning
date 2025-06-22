package com.brownspy1.bdnews;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    ListView userList;
    LayoutInflater layoutInflater;
    ArrayList<HashMap<String,String>> NewsList = new ArrayList<>();
    HashMap<String,String> News = new HashMap<>();
    String color = null;
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

        userList = findViewById(R.id.userList);
        getsetdata();
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Newsadaption newsadaption = new Newsadaption();
        userList.setAdapter(newsadaption);




    }
    private class Newsadaption extends BaseAdapter{

        @Override
        public int getCount() {
            return NewsList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View news_card =  layoutInflater.inflate(R.layout.card, userList,false);

            //get all id from card layout
            ImageView Image = news_card.findViewById(R.id.usarImage);
            TextView catagoris = news_card.findViewById(R.id.catagoris);
            TextView Titel = news_card.findViewById(R.id.newsTitel);
            TextView DItails = news_card.findViewById(R.id.newsDisc);
            LinearLayout NewsCard = news_card.findViewById(R.id.usarCard);
            //get all date from ArryList<HashMap>
            HashMap<String,String> news_json= NewsList.get(position);
            String titel = news_json.get("titel");
            String catag = news_json.get("catagori");
            String disc = news_json.get("description") ;
            String imagUrl = news_json.get("image");

            //Now lode all data in front

            //Note: set bg tint for catagori and catagori text
            catagoris.setText(catag);

            if (catag.equalsIgnoreCase("international")) {
                color = "#20C63D";
            } else if (catag.equalsIgnoreCase("tech")) {
                color = "#FF9800";
            } else if (catag.equalsIgnoreCase("education")) {
                color = "#2196F3";
            } else if (catag.equalsIgnoreCase("entertainment")) {
                color = "#E91E63";
            } else if (catag.equalsIgnoreCase("gaming")) {
                color = "#3F51B5";
            }else if (catag.equalsIgnoreCase("health")) {
                color = "#E65100";
            }else {
                color = "#FFCCBC";
            }
            catagoris.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            //set imagge from url
            Picasso.get()
                    .load(imagUrl)
                    .placeholder(R.drawable.abcdefg)
                    .into(Image);
            //set Titel
            Titel.setText(titel);

            //set Description
            DItails.setText(disc);

            NewsCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //convart image to bitmap and send to NewsPlayground activity
                    Bitmap bitmap = ((BitmapDrawable) Image.getDrawable()).getBitmap();
                    NewsPlayground.imageBit = bitmap;

                    startActivity(new Intent(MainActivity.this,NewsPlayground.class)
                            .putExtra("catagori",catag)
                            .putExtra("Titel",titel)
                            .putExtra("discription",disc)
                            .putExtra("color",color)
                    );
                }
            });
            return news_card;
        }
    }


    private void getsetdata(){
        NewsList = new ArrayList<>();

        News = new HashMap<>();
        News.put("catagori", "international");
        News.put("titel", "ইরান-ইসরায়েল: সামরিক শক্তিতে কে এগিয়ে?");
        News.put("description", "হে জবুল্লাহ নেতা হাসান নাসরাল্লাহকে হত্যা এবং লেবাননে ইসরায়েলের আক্রমণকে ঘিরে ইসরায়েলে ইরানের মিসাইল হামলার পর দুই দেশের মধ্যে এখন চরম উত্তেজনা বিরাজ করছে। এ ঘটনায় মধ্যপ্রাচ্যসহ পুরো বিশ্বের নজর এখন ইরান-ইসরায়েল পরিস্থিতির দিকে। ইরানের হামলার জবাবে ইসরায়েলের পদক্ষেপ কী হবে সেটি নিয়েও উদ্বেগ রয়েছে।");
        News.put("image", "https://dailyinqilab.com/mediaStorage/content/images/2025June/aa-20250613160453.jpg");
        NewsList.add(News);

// News 2
        News = new HashMap<>();
        News.put("catagori", "tech");
        News.put("titel", "iPhone 16 তে কী নতুন ফিচার আসছে?");
        News.put("description", "অ্যাপল তাদের নতুন iPhone 16 নিয়ে বড় চমক দিতে যাচ্ছে। এবার থাকতে পারে AI‑powered ফিচার, better zoom lens এবং নতুন design language। leaks অনুযায়ী, ব্যাটারি লাইফ ও গেমিং পারফরম্যান্সেও বিশাল উন্নতি হবে।");
        News.put("image", "https://images.tv9bangla.com/wp-content/uploads/2024/08/1-158.jpg");
        NewsList.add(News);

// News 3
        News = new HashMap<>();
        News.put("catagori", "education");
        News.put("titel", "বাংলাদেশের শিক্ষাব্যবস্থা কি যুগোপযোগী হচ্ছে?");
        News.put("description", "নতুন কারিকুলাম চালুর পর শিক্ষার্থীদের মধ্যে মিশ্র প্রতিক্রিয়া। অনেকেই বলছে এটি তাদের চাপ কমালেও বাস্তবজীবনের সঙ্গে এখনো পুরোপুরি সংযুক্ত নয়। শিক্ষক-অভিভাবকরা বলছেন, আরও আধুনিকায়ন প্রয়োজন।");
        News.put("image", "https://sharebiz.net/wp-content/uploads/2023/09/761715_147.jpg");
        NewsList.add(News);

// News 4
        News = new HashMap<>();
        News.put("catagori", "entertainment");
        News.put("titel", " ‘পুষ্পা ২’ ট্রেলার ঝড় তুলেছে ইউটিউবে");
        News.put("description", "আল্লু অর্জুন অভিনীত ‘পুষ্পা ২’ এর ট্রেইলার মুক্তির পর ইউটিউবে মাত্র ২৪ ঘন্টায় ১০ কোটির বেশি ভিউ পেয়েছে। ভক্তরা বলছেন, সিনেমাটি আগের অংশকেও ছাপিয়ে যাবে। দক্ষিণী সিনেমার এই জোয়ার বলিউডকেও চাপে ফেলছে।");
        News.put("image", "https://etvbharatimages.akamaized.net/etvbharat/prod-images/17-11-2024/22920226_wb_nzdvkl.jpg");
        NewsList.add(News);

        // News 5
        News = new HashMap<>();
        News.put("catagori", "tech");
        News.put("titel", "Android 15 আনছে আরও শক্তিশালী ব্যাটারি অপটিমাইজেশন");
        News.put("description", "Google এর নতুন Android 15 ভার্সনে থাকছে আরও উন্নত ব্যাটারি ব্যবস্থাপনা, background app limiter এবং AI-based usage prediction।");
        News.put("image", "https://images.indianexpress.com/2024/05/android15-beta.jpg");
        NewsList.add(News);

// News 6
        News = new HashMap<>();
        News.put("catagori", "gaming");
        News.put("titel", "GTA VI রিলিজ ডেট নিশ্চিত, গেমারদের উচ্ছ্বাস");
        News.put("description", "Rockstar Games ঘোষণা দিয়েছে, GTA VI ২০২৫ সালের প্রথমার্ধে রিলিজ হবে। ট্রেইলারে মিয়ামি-স্টাইল map এবং দুইজন প্রোটাগনিস্ট দেখা গেছে।");
        News.put("image", "https://staticg.sportskeeda.com/editor/2024/01/f9cb5-17044579163993-1920.jpg");
        NewsList.add(News);

// News 7
        News = new HashMap<>();
        News.put("catagori", "education");
        News.put("titel", "এইচএসসি ২০২৫ নতুন নিয়মে হবে, বলল শিক্ষা মন্ত্রণালয়");
        News.put("description", "শিক্ষা মন্ত্রণালয় জানিয়েছে, ২০২৫ সালের এইচএসসি পরীক্ষায় থাকছে নতুন মূল্যায়ন পদ্ধতি ও কমানো হচ্ছে নৈর্ব্যক্তিক প্রশ্নের সংখ্যা।");
        News.put("image", "https://cdn.dhakatribune.com/uploads/2023/10/05/exam-edu.jpg");
        NewsList.add(News);

// News 8
        News = new HashMap<>();
        News.put("catagori", "international");
        News.put("titel", "চীন-তাইওয়ান উত্তেজনা: আবারও বিমান মহড়া চালালো চীন");
        News.put("description", "চীন আবারও তাইওয়ান সংলগ্ন এলাকায় বিমান মহড়া দিয়েছে। এই ঘটনার পর আন্তর্জাতিক মহলে উদ্বেগ বেড়েছে এবং তাইওয়ান সেনাবাহিনী সতর্কতায় রয়েছে।");
        News.put("image", "https://www.aljazeera.com/wp-content/uploads/2023/09/AP23171021231504-1694520154.jpg");
        NewsList.add(News);

// News 9
        News = new HashMap<>();
        News.put("catagori", "entertainment");
        News.put("titel", "সালমান খানের নতুন ছবি ‘টাইগার ৪’ ঘোষণা");
        News.put("description", "YRF ঘোষণা দিয়েছে টাইগার ফ্র্যাঞ্চাইজির চতুর্থ ছবি ‘টাইগার ৪’। সালমান খান আবারও গুপ্তচর রূপে পর্দায় ফিরছেন।");
        News.put("image", "https://filmfare.wwmindia.com/content/2023/nov/tiger3-20231106011021.jpg");
        NewsList.add(News);

// News 10
        News = new HashMap<>();
        News.put("catagori", "health");
        News.put("titel", "WHO: ২০২৫ সালে বিশ্বজুড়ে ডায়াবেটিস বাড়ার আশঙ্কা");
        News.put("description", "বিশ্ব স্বাস্থ্য সংস্থা জানাচ্ছে, খাদ্যাভ্যাস ও জীবনযাপনের পরিবর্তনের কারণে ২০২৫ সালে বিশ্বে ডায়াবেটিস রোগীর সংখ্যা উল্লেখযোগ্য হারে বাড়তে পারে।");
        News.put("image", "https://www.who.int/images/default-source/health-topics/diabetes/diabetes.jpg");
        NewsList.add(News);

    }
}