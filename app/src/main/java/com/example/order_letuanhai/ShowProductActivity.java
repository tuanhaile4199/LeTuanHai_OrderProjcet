package com.example.order_letuanhai;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowProductActivity extends AppCompatActivity {

    View imgBack;
    ListView lvShowOrder;
    List<Product> products;
    TextView tvPrice;
    AdapterProduct adapterProduct2;
    View btnDelete;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        imgBack = findViewById(R.id.btnBack);
        lvShowOrder = findViewById(R.id.lvShowOrder);
        tvPrice = findViewById(R.id.tvShowprice);
        products = new ArrayList<>();
        btnDelete = findViewById(R.id.btnDelete);

        Intent intent = getIntent();

        products = (List<Product>) intent.getSerializableExtra("list");

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getAmount() == 0) {
                products.remove(i);
                i = i - 1;
                count = i;
            }
        }


        adapterProduct2 = new AdapterProduct(products);
        lvShowOrder.setAdapter(adapterProduct2);

        String price = intent.getStringExtra("price");
        tvPrice.setText(price);
        adapterProduct2.notifyDataSetChanged();


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open(view);
            }
        });

    }


    public void open(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("DELELE YOUR ORDER");
        alertDialogBuilder.setMessage("Are you sure?");

        alertDialogBuilder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });

        alertDialogBuilder.setNegativeButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent clear_activity = new Intent(ShowProductActivity.this, MainActivity.class);
                clear_activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(clear_activity);
                finish();
            }
        });

        alertDialogBuilder.create().show();
    }
}


