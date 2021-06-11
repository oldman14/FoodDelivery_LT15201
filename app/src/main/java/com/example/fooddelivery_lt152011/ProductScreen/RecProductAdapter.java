package com.example.fooddelivery_lt152011.ProductScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_lt152011.R;

import java.util.List;

public class RecProductAdapter extends RecyclerView.Adapter<RecProductAdapter.ViewHolder>{

    public Context context;
    public List<Product> productList;

    public RecProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rec_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tv_proName.setText(product.getProductName());
        holder.tv_proNote.setText(product.getProductNote());
        holder.tv_proPrice.setText(String.valueOf( product.getProductPrice()));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_proName, tv_proNote, tv_proPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_proName = (TextView) itemView.findViewById(R.id.tv_proName);
            tv_proNote = (TextView) itemView.findViewById(R.id.tv_proNote);
            tv_proPrice = (TextView) itemView.findViewById(R.id.tv_proPice);
        }
    }
}
