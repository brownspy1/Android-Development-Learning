package com.brownspy1.drowarinandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    NavigationView navigationView;
    View Drowbal_heder;
    ImageView heder_image;
    TextView heder_titel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.main);
        materialToolbar = findViewById(R.id.matrialtoolbar);
        navigationView = findViewById(R.id.navigasonviwe);
        Drowbal_heder = navigationView.getHeaderView(0);
        heder_image = Drowbal_heder.findViewById(R.id.imageView);
        heder_titel = Drowbal_heder.findViewById(R.id.textView1);


        heder_titel.setText("M.Mahir");
        //sync toolbar as e aton ber and make funsonal to click to open
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawerLayout,materialToolbar,R.string.close,R.string.open
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //funsonal toolbar menu
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.shere){
                    Toast.makeText(MainActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                };
                return false;
            }
        });

        inflat_fregment(R.id.content_frame,new home());
        //handel all drower menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String text = menuItem.getTitle().toString();
                if (text.equals("Home")){
                    inflat_fregment(R.id.content_frame,new home());
                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                } else if (text.equals("Contact")) {
                    inflat_fregment(R.id.content_frame,new contact());
                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                };
                drawerLayout.close();
                return true;
            }
        });




    }

    private void inflat_fregment(int frame_id, Fragment fregment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(frame_id,fregment);
        fragmentTransaction.commit();
    }

}