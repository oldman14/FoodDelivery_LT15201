package com.example.fooddelivery_lt152011.productScreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_lt152011.HTTP_URL;
import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class RecProductAdapter extends RecyclerView.Adapter<RecProductAdapter.ViewHolder>{

    public Context context;
    public List<Product> productList;
    private OneItemClick oneItemClick;
    HttpAdapter httpAdapter;
    public RecProductAdapter(Context context, List<Product> productList,OneItemClick oneItemClick) {
        this.context = context;
        this.productList = productList;
        this.oneItemClick = oneItemClick;
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
        httpAdapter = new HttpAdapter();
        httpAdapter.setBaseUrl( HTTP_URL.Final_URL );        holder.tv_proName.setText(product.getProductName());
        holder.tv_proNote.setText(product.getProductNote());
        holder.tv_proPrice.setText(new DecimalFormat("##,###đ").format(product.getProductPrice()));
        Picasso.get().load(product.ProductImage).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "logposition: "+position);
                oneItemClick.onItemClick(product, httpAdapter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {
        TextView tv_proName, tv_proNote, tv_proPrice;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_proName = itemView.findViewById(R.id.tv_proName);
            tv_proNote =  itemView.findViewById(R.id.tv_proNote);
            tv_proPrice =  itemView.findViewById(R.id.tv_proPice);
            imageView = itemView.findViewById(R.id.img_product);
        }
    }

}
