package com.example.order_letuanhai;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order_letuanhai.databinding.ShowProductBinding;
import com.example.order_letuanhai.databinding.ShowProductBindingImpl;

import java.io.Serializable;
import java.util.List;

public class ShowOrder extends AppCompatActivity {


    List<Product> products;
    AdapterProduct adapterProduct2;
    int count = 0;
    ListView lvShowOrder;
    TextView tvShowPrice;
    Button btnDelete;
    ImageView btnBack;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_product);

        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);
        tvShowPrice = findViewById(R.id.tvShowprice);

        Intent intent = getIntent();

        products = (List<Product>) intent.getSerializableExtra("list");

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getAmount() == 0) {
                products.remove(i);
                i = i - 1;
                count = i;
            }
        }

        tvShowPrice.setText(intent.getStringExtra("price"));
        adapterProduct2 = new AdapterProduct(products);
        lvShowOrder.setAdapter(adapterProduct2);
        adapterProduct2.notifyDataSetChanged();



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });


    }

}

