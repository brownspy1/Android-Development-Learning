package com.brownspy1.recyclerviwe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    RecyclerView item_recyclere;
    ArrayList<HashMap<String,String>> Arraylist_items;
    HashMap<String,String> hashMap_items;
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
        item_recyclere = findViewById(R.id.recycal_viwe);

        Arraylist_items = new ArrayList<>();

        //item1
        hashMap_items = new HashMap<>();
        hashMap_items.put("itemType","Card");
        hashMap_items.put("id","1");
        hashMap_items.put("Text","Ami protom card");
        Arraylist_items.add(hashMap_items);

        //item2
        hashMap_items = new HashMap<>();
        hashMap_items.put("itemType","Contact");
        hashMap_items.put("id","2");
        hashMap_items.put("name","M.Mahir");
        hashMap_items.put("number","01728186433");
        Arraylist_items.add(hashMap_items);

        //item3
        hashMap_items = new HashMap<>();
        hashMap_items.put("itemType","Contact");
        hashMap_items.put("id","3");
        hashMap_items.put("name","M.Mahadi");
        hashMap_items.put("number","01732657219");
        Arraylist_items.add(hashMap_items);

        //item4
        hashMap_items = new HashMap<>();
        hashMap_items.put("itemType","Card");
        hashMap_items.put("id","4");
        hashMap_items.put("Text","HelloWorld");
        Arraylist_items.add(hashMap_items);

        my_castom_recyclereViwe myadapter = new my_castom_recyclereViwe();
        item_recyclere.setAdapter(myadapter);
        item_recyclere.setLayoutManager(new LinearLayoutManager(this));









    }
    //-----------------------------------------------Casom adaptar with castom  viwe holder

    private class my_castom_recyclereViwe extends RecyclerView.Adapter{
        int card_type = 1;
        int contact_type = 0;

        //item card holder
        private class item_card extends RecyclerView.ViewHolder{
            TextView id;
            TextView maintext;
            public item_card(@NonNull View itemView) {
                super(itemView);
                id = itemView.findViewById(R.id.Text_id);
                maintext = itemView.findViewById(R.id.mainText);
            }
        }

        //Contact card holder
        private class contact_item extends  RecyclerView.ViewHolder{
            ImageView contactImage;
            TextView name;
            TextView number;
            public contact_item(@NonNull View itemView) {
                super(itemView);

                contactImage = itemView.findViewById(R.id.contact_image);
                name = itemView.findViewById(R.id.contact_name);
                number = itemView.findViewById(R.id.contact_number);

            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            if (viewType == card_type){
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.item_card,parent,false);

                return new item_card(view);


            } else if (viewType == contact_type) {
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.contactcard,parent,false);

                return new contact_item(view);
            }

            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                if (getItemViewType(position) == card_type){
                    item_card myholder = (item_card) holder;
                    HashMap<String,String> Carditems = Arraylist_items.get(position);
                    String Text = Carditems.get("Text");
                    String id = Carditems.get("id");

                    myholder.maintext.setText(Text);
                    myholder.id.setText(id);

                } else if (getItemViewType(position) == contact_type) {

                    contact_item myholder = (contact_item) holder;
                    HashMap<String,String> Contact_Items = Arraylist_items.get(position);

                    String name = Contact_Items.get("name");
                    String number = Contact_Items.get("number");

                    myholder.name.setText(name);
                    myholder.number.setText(number);

                }
        }

        @Override
        public int getItemCount() {
            return Arraylist_items.size();
        }

        @Override
        public int getItemViewType(int position) {
            HashMap<String,String> hashMap = Arraylist_items.get(position);
            String itemType = hashMap.get("itemType");

            if (itemType.equals("Card")){
                return card_type;
            }else {
                return contact_type;
            }
        }
    }






}