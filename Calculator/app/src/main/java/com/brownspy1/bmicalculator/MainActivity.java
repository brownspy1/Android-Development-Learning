package com.brownspy1.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView submit;
    EditText Pname,Pprice,ChinisCuriar,Shippingcost,ProductQuntyty,CAC,Overhed,ProfitWant;

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


        Pname = findViewById(R.id.in_name);
        Pprice = findViewById(R.id.in_price);
        ChinisCuriar = findViewById(R.id.in_chinis);
        Shippingcost = findViewById(R.id.ShipingCuriarcarge);
        ProductQuntyty = findViewById(R.id.quantity);
        CAC = findViewById(R.id.CAC);
        Overhed = findViewById(R.id.OverheadCost);
        ProfitWant = findViewById(R.id.profitpercent);
        submit = findViewById(R.id.submit_button);





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Pname.getText().toString().trim().isEmpty() ||
                        Pprice.getText().toString().trim().isEmpty() ||
                        ChinisCuriar.getText().toString().trim().isEmpty() ||
                        Shippingcost.getText().toString().trim().isEmpty() ||
                        ProductQuntyty.getText().toString().trim().isEmpty() ||
                        CAC.getText().toString().trim().isEmpty() ||
                        Overhed.getText().toString().trim().isEmpty() ||
                        ProfitWant.getText().toString().trim().isEmpty() ){
                    // Show an error message or toast
                    Toast.makeText(MainActivity.this, "তুই কি কানা চো** দ্যাখ কোনডা য্যানো লিখো নাই~", Toast.LENGTH_LONG).show();
                    return;
                }
                //get all adisonal info
                String productName = Pname.getText().toString();
                //Done som calculation for price
                Float SorcingPrice = Float.parseFloat( Pprice.getText().toString());
                int quntyty = Integer.parseInt(ProductQuntyty.getText().toString());
                Float send_tk_sorcing = SorcingPrice * quntyty;


                //Done som calculation for SorcingPricePerUnit
                Float chinis_curiar = Float.parseFloat(ChinisCuriar.getText().toString());
                Float shipinTOBangladesh = Float.parseFloat(Shippingcost.getText().toString());
                Float send_tk_parProductQust = (send_tk_sorcing+ chinis_curiar + shipinTOBangladesh) / quntyty;

                //Done som calculation for productPrice
                Float cac = Float.parseFloat(CAC.getText().toString());
                Float overhead = Float.parseFloat(Overhed.getText().toString());
                Float profit = Float.parseFloat(ProfitWant.getText().toString());

              Float send_tk_FinalPrice = (send_tk_parProductQust + cac + overhead) * (1 + profit / 100);



                //calculat hole cost
                Float wholeCost = send_tk_parProductQust * quntyty;

                //calculat potential profit
                Float potentialProfit = (send_tk_FinalPrice*quntyty) - wholeCost;


                Intent Calculaton = new Intent(MainActivity.this, PriceDetail.class);
                Calculaton.putExtra("productName", productName);
                Calculaton.putExtra("tk_sorcing", SorcingPrice);
                Calculaton.putExtra("tk_chinisCuriar", chinis_curiar);
                Calculaton.putExtra("tk_shipmentCharge", shipinTOBangladesh);
                Calculaton.putExtra("Product_Quntity", quntyty);
                Calculaton.putExtra("sourcing_price_perUnit", send_tk_parProductQust);
                Calculaton.putExtra("CAC_perUnit", cac);
                Calculaton.putExtra("OverHade", overhead);
                Calculaton.putExtra("tk_final_pricePerUnit", send_tk_FinalPrice);
                Calculaton.putExtra("WholeCost", wholeCost);
                Calculaton.putExtra("AproximateCost", potentialProfit);
                startActivity(Calculaton);

            }
        });
    }

}