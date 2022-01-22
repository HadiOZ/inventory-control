package com.hadioz.inventorycontrol;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<Product> products;
    private Context context;

    public static String EXTRA_ID = "id";
    public static String EXTRA_PRODUCT_NAME = "name_product";

    public ProductAdapter(ArrayList<Product> data, Context context) {
        this.products = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.name.setText(products.get(position).getName());
        holder.count.setText(Integer.toString(products.get(position).getStock()));
        holder.itemView.setOnClickListener(v -> {
            Intent logIntent = new Intent(v.getContext(), ListLog.class);
            logIntent.putExtra(EXTRA_ID, products.get(position).getId());
            logIntent.putExtra(EXTRA_PRODUCT_NAME, products.get(position).getName());
            v.getContext().startActivity(logIntent);
        });

    }


    @Override
    public int getItemCount() {
        return (products != null) ? products.size() : 0;
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView name, count;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_product_name);
            count = (TextView) itemView.findViewById(R.id.tv_count);
        }
    }
}
