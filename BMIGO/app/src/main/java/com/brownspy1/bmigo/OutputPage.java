package com.brownspy1.bmigo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputPage extends AppCompatActivity {
    RelativeLayout BMICalculatonCard;
    TextView BMI_outputDecimal,BMIOutputFloat,BMI_status_text;
    Button SaveBMIScore;
    MediaPlayer playsound;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_output_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BMICalculatonCard = findViewById(R.id.BMICalculatonCard);//downlode this layout
        BMI_outputDecimal = findViewById(R.id.BMI_outputDecimal);
        BMIOutputFloat = findViewById(R.id.BMIOutputFloat);
        SaveBMIScore = findViewById(R.id.SaveBMIScore);
        BMI_status_text = findViewById(R.id.BMI_status_text);


        //initialaiz card
        BMICalculatonCard.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            SaveBMIScore.setEnabled(true); // layout ready হলে button কাজ করুক
        });





        // Get the data from the Intent
        int age = getIntent().getIntExtra("Age", 0);
        int Weight = getIntent().getIntExtra("Weight",0);
        float Height = getIntent().getFloatExtra("Height",0);
        Boolean genderSwitch = getIntent().getBooleanExtra("genderSwitch",false);


        //Done calculation
        float Meters = (float) (Height * (0.3048));
        float BMI = Weight / (Meters * Meters);
        float BMI_sp = Math.round(BMI * 100) / 100f;

        //concat this calculation value in strinf and divaid to 2
        String ans = String.valueOf(BMI_sp);
        String[] parts = ans.split("\\.");
        String beforeDot = parts[0];
        String afterDot = (parts.length>1)?parts[1]:"00";

//        Log.d("DEBUG", "Height: " + Height + ", Weight: " + Weight+"BMI: "+BMI);

        BMI_outputDecimal.setText(beforeDot);
        BMIOutputFloat.setText("."+afterDot);


        //paly sound and show castomaiz text


        if (BMI >= 18.5 && BMI <= 24.9){
            playAudio("https://brownspy1.github.io/Deen/voice/perfact.mp3");
            BMI_status_text.setText("NORMAL BMI");
            BMI_status_text.setBackgroundResource(R.drawable.green);
        } else if (BMI >24.9 && BMI <= 29.9) {
            BMI_status_text.setText("OVERWEIGHT BMI");
            playAudio("https://brownspy1.github.io/Deen/voice/over.mp3");
            BMI_status_text.setBackgroundResource(R.drawable.rad);
        } else if (BMI>29.9 && BMI<=40) {
            BMI_status_text.setText("OBESE BMI");
            playAudio("https://brownspy1.github.io/Deen/voice/over.mp3");
            BMI_status_text.setBackgroundResource(R.drawable.rad);
        } else if (BMI > 40) {
            BMI_status_text.setText("EXTREME OBESE BMI");
            playAudio("https://brownspy1.github.io/Deen/voice/over.mp3");
            BMI_status_text.setBackgroundResource(R.drawable.rad);
        } else if (BMI < 18.5) {
            BMI_status_text.setText("UNDERWEIGHT BMI");
            playAudio("https://brownspy1.github.io/Deen/voice/under.mp3");
            BMI_status_text.setBackgroundResource(R.drawable.yellow);
        }

        SaveBMIScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BMICalculatonCard.setDrawingCacheEnabled(true);
                BMICalculatonCard.buildDrawingCache();
                Bitmap bitmap = Bitmap.createBitmap(BMICalculatonCard.getDrawingCache());
                BMICalculatonCard.setDrawingCacheEnabled(false);
                saveBitmapAsPNG(bitmap);
            }
        });



    }
    public Boolean iscon(){
        //chack internet connection
        ConnectivityManager conectonManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conectonManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()){
            return true;

        }else {
            return false;
        }
    }
    public void playAudio(String link){
        if (iscon()){
            playsound = new MediaPlayer();
            try {
                playsound.setDataSource(link);
                playsound.prepare();
                playsound.start();
            } catch (Exception e) {
                Log.e("TAG", "Error playing sound: " + e.getMessage());
            }
        }else {
            try {
                playsound = MediaPlayer.create(this, R.raw.nointernet);
                playsound.start();
            } catch (Exception e) {
                Log.e("TAG", "Error playing sound: " + e.getMessage());
            }
        }
    }
    public Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public void saveBitmapAsPNG(Bitmap bitmap) {
        if (bitmap == null) return;

        String filename = "BMI_Result_" + System.currentTimeMillis() + ".png";
        OutputStream fos;

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentResolver resolver = getContentResolver();
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, filename);
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/BMI");

                Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                if (imageUri != null) {
                    fos = resolver.openOutputStream(imageUri);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    if (fos != null) {
                        fos.flush();
                        fos.close();
                    }
                    Toast.makeText(this, "Saved to Gallery (BMI folder)", Toast.LENGTH_SHORT).show();
                }
            } else {
                File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "BMI");
                if (!directory.exists()) directory.mkdirs();
                File file = new File(directory, filename);
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
                Toast.makeText(this, "Saved to: " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Save failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}