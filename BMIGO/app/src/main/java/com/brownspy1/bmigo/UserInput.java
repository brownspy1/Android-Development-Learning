package com.brownspy1.bmigo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserInput extends AppCompatActivity {
    EditText height_input,Age,Weight;
    Switch genderSwitch;
    ImageView minas,Pluse,Wminas,WPluse;
    Button calculatBMI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_input);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Age = findViewById(R.id.Age);
        Weight = findViewById(R.id.Weight);
        height_input = findViewById(R.id.height_input);
        minas = findViewById(R.id.minas);
        Pluse = findViewById(R.id.Pluse);
        Wminas = findViewById(R.id.Wminas);
        WPluse = findViewById(R.id.WPluse);
        calculatBMI = findViewById(R.id.CalculatBMI);
        genderSwitch = findViewById(R.id.genderSwitch);


        //set onclik for plus and minus
        minas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AgeText = Age.getText().toString();
                if (!AgeText.isEmpty()){
                    int cal_age = Integer.parseInt(AgeText);
                    if(cal_age>0)
                    {
                        cal_age--;
                        Age.setText(String.valueOf(cal_age));
                    }
                    else
                    {
                        Age.setError("Please enter your age");
                        return;
                    }
                }


            }
        });


//---------------Age plus------------------------------------------------------
        Pluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AgeText = Age.getText().toString();
                if (!AgeText.isEmpty()){
                    int cal_age = Integer.parseInt(AgeText);
                    cal_age++;
                    Age.setText(String.valueOf(cal_age));
                }else {
                    Age.setError("Please enter your age");
                    return;
                }

            }
        });


//---------------Weight    minus------------------------------------------------------
        //set onclik for Wplus and Wminus
        Wminas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String WeightText = Weight.getText().toString();
                if (!WeightText.isEmpty()){
                    int cal_weight = Integer.parseInt(WeightText);

                    if(cal_weight>0)
                    {
                        cal_weight--;
                        Weight.setText(String.valueOf(cal_weight));
                    }
                    else
                    {
                        Weight.setError("Please enter your weight");
                        return;
                    }
                }

            }
        });

//---------Weight plus------------------------------------------------------
        WPluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String WeightPText = Weight.getText().toString();
                if (!WeightPText.isEmpty()){
                    int cal_weight = Integer.parseInt(WeightPText);
                    cal_weight++;
                    Weight.setText(String.valueOf(cal_weight));
                }
            }
        });


        //if its false then its will be a men if is true its weman
        //calculason on next page


            calculatBMI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //check if the input is empty
                    String weightText = Weight.getText().toString();
                    String ageText = Age.getText().toString();
                    String heightText = height_input.getText().toString();

                    boolean hasError = false;

                    if (ageText.isEmpty()) {
                        Age.setError("Please enter your age");
                        hasError = true;
                    }
                    if (weightText.isEmpty()) {
                        Weight.setError("Please enter your weight");
                        hasError = true;
                    }
                    if (heightText.isEmpty()) {
                        height_input.setError("Please enter your height");
                        hasError = true;
                    }
                    if (hasError) {
                        return;
                    }


                    Intent calculateuserBMI = new Intent(UserInput.this, OutputPage.class);
                    //get data to send next page
                    int Age_send = Integer.parseInt(Age.getText().toString());
                    int Weight_send = Integer.parseInt(Weight.getText().toString());
                    float height_send = Float.parseFloat(height_input.getText().toString());
                    boolean genderSwitch_send = genderSwitch.isChecked();


                    //send data to next page
                    calculateuserBMI.putExtra("Age", Age_send);
                    calculateuserBMI.putExtra("Weight", Weight_send);
                    calculateuserBMI.putExtra("Height", height_send);
//                    Log.d("DEBUG", "Height send: " + height_send + " Height get :" + height_input.getText().toString());
                    calculateuserBMI.putExtra("Gender", genderSwitch_send);
                    startActivity(calculateuserBMI);
                }
            });


    }
}