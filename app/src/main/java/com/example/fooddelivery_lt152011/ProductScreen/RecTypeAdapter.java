package com.example.fooddelivery_lt152011.ProductScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_lt152011.R;

import java.util.List;

public class RecTypeAdapter extends RecyclerView.Adapter<RecTypeAdapter.ViewHolder>{
    private List<TypeProduct> typeProducts;
    private Context context;

    public RecTypeAdapter(List<TypeProduct> typeProducts, Context context) {
        this.typeProducts = typeProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_rec_typeproduct, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TypeProduct typeProduct = typeProducts.get(position);
        holder.tv_title.setText(typeProduct.getTypeName());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.recProduct.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(typeProduct.getProducts().size());

        RecProductAdapter recProductAdapter = new RecProductAdapter(context, typeProduct.getProducts());
        holder.recProduct.setLayoutManager(layoutManager);
        holder.recProduct.setAdapter(recProductAdapter);

    }

    @Override
    public int getItemCount() {
        return typeProducts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title;
        private RecyclerView recProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title_typepro);
            recProduct = itemView.findViewById(R.id.rec_typepro);
        }
    }
}
