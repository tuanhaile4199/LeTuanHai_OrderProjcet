package com.example.order_letuanhai;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterProduct extends BaseAdapter {

    List<Product> products;

    public AdapterProduct(List<Product> products) {
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view1 = layoutInflater.inflate(R.layout.item_product, viewGroup, false);

        TextView tvName = view1.findViewById(R.id.tvName);
        TextView tvAmount = view1.findViewById(R.id.tvItemAmount);

        Integer amountItem = products.get(i).getAmount();
        tvName.setText(products.get(i).getName());
        tvAmount.setText(amountItem.toString());

        return view1;
    }

}
