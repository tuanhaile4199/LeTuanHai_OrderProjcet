package com.example.order_letuanhai;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvAmount, tvPrice, tvItemAmount, tvNameWelcome;
    Button btnOrder;
    ListView lvOrder;
    List<Product> products;
    Product product1, product2, product3, product4, product5, product6;
    AdapterProduct adapterProduct1;
    View btnCart;
    Double totalPrice;
    int numberPosition = 0;
    Integer amount = 0;
    int amountItem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lvOrder = findViewById(R.id.lvOrder);
        btnCart = findViewById(R.id.btnCart);
        tvAmount = findViewById(R.id.tvAmount);
        btnOrder = findViewById(R.id.btnOrder);
        tvPrice = findViewById(R.id.tvPrice);
        tvItemAmount = findViewById(R.id.tvItemAmount);
        tvNameWelcome = findViewById(R.id.tvNameWelcome);

        products = new ArrayList<>();

        product1 = new Product("Pizza Panda", 0, 10.0);
        product2 = new Product("KFC Super", 0, 10.0);
        product3 = new Product("Bread Eggs", 0, 10.0);
        product4 = new Product("Coca Cola", 0, 10.0);
        product5 = new Product("Chicken Super", 0, 10.0);
        product6 = new Product("Cup Cake", 0, 10.0);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);


        adapterProduct1 = new AdapterProduct(products);
        lvOrder.setAdapter(adapterProduct1);


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amount == 0) {
                    Toast.makeText(getBaseContext(), "Ban chua chon mon an", Toast.LENGTH_SHORT).show();
                } else {
                    recreate();
                    Toast.makeText(getBaseContext(), "Thank you! Your order is sent to restaurant!", Toast.LENGTH_LONG).show();
                }
            }
        });


        lvOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                amount++;
                tvAmount.setText(amount.toString());
                totalPrice = amount * 10.0;
                tvPrice.setText(totalPrice.toString());

                amountItem = products.get(i).getAmount();
                amountItem++;
                products.get(i).setAmount(amountItem);

                numberPosition = i;
            }
        });


        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amount == 0) {
                    Toast.makeText(getBaseContext(), "Ban chua chon mon an", Toast.LENGTH_SHORT).show();
                } else {
                    Integer amountShowItem = products.get(numberPosition).getAmount();
                    Intent intent = new Intent(getBaseContext(), ShowProductActivity.class);
                    intent.putExtra("amount", amountShowItem.toString());
                    intent.putExtra("price", totalPrice.toString());
                    intent.putExtra("list", (Serializable) products);
                    startActivity(intent);
                }
            }
        });
    }
}