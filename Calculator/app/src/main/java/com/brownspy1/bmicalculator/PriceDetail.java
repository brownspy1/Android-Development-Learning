package com.brownspy1.bmicalculator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PriceDetail extends AppCompatActivity {
    TextView ProductName,tk_sorcing,tk_chinisCuriar,tk_shipmentCharge,Product_Quntity;
    TextView sourcing_price_perUnit,CAC_perUnit,OverHade,tk_final_pricePerUnit,WholeCost,AproximateCost;
    TextView BackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_price_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //local variable for this activity text viwe id
        ProductName=findViewById(R.id.product_name);
        tk_sorcing=findViewById(R.id.sourching_price);
        tk_chinisCuriar=findViewById(R.id.chinis_curiarcarge);
        tk_shipmentCharge=findViewById(R.id.shipment_charge);
        Product_Quntity=findViewById(R.id.product_quntyty);
        sourcing_price_perUnit=findViewById(R.id.sourching_price_per);
        CAC_perUnit=findViewById(R.id.cac);
        OverHade=findViewById(R.id.overhead);
        tk_final_pricePerUnit=findViewById(R.id.final_price);
        WholeCost=findViewById(R.id.whole_cost);
        AproximateCost=findViewById(R.id.potential_profit);
        BackButton=findViewById(R.id.back_button);



        //get data from main activity
        String Name = getIntent().getStringExtra("productName");
        Float SpricePerUnit = getIntent().getFloatExtra("tk_sorcing", 0);
        Float ChinisCuriar = getIntent().getFloatExtra("tk_chinisCuriar", 0);
        Float ShipmentCharge = getIntent().getFloatExtra("tk_shipmentCharge", 0);
        int Quntity = getIntent().getIntExtra("Product_Quntity", 0);
        Float Sprice = getIntent().getFloatExtra("sourcing_price_perUnit", 0);
        Float CAC = getIntent().getFloatExtra("CAC_perUnit", 0);
        Float Overhead = getIntent().getFloatExtra("OverHade", 0);
        Float FinalPrice = getIntent().getFloatExtra("tk_final_pricePerUnit", 0);
        Float WholeCoste = getIntent().getFloatExtra("WholeCost", 0);
        Float AproximateCoste = getIntent().getFloatExtra("AproximateCost", 0);

        //set data to text view
        ProductName.setText(Name);
        tk_sorcing.setText(String.valueOf(SpricePerUnit));
        tk_chinisCuriar.setText(String.valueOf(ChinisCuriar));
        tk_shipmentCharge.setText(String.valueOf(ShipmentCharge));
        Product_Quntity.setText(String.valueOf(Quntity));
        sourcing_price_perUnit.setText(String.valueOf(Sprice));
        CAC_perUnit.setText(String.valueOf(CAC));
        OverHade.setText(String.valueOf(Overhead));
        tk_final_pricePerUnit.setText(String.valueOf(FinalPrice));
        WholeCost.setText(String.valueOf(WholeCoste));
        AproximateCost.setText(String.valueOf(AproximateCoste));



        BackButton.setOnClickListener(v -> {
            finish();
        });

    }
}