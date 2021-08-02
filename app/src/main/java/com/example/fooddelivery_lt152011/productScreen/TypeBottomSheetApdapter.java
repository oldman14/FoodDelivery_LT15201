package com.example.fooddelivery_lt152011.productScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_lt152011.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TypeBottomSheetApdapter extends RecyclerView.Adapter<TypeBottomSheetApdapter.ViewHolder> {
    List<ListTypeProduct> listTypeProducts;
    public TypeBotSheetInterface typeBotSheetInterface;
    LinearLayoutManager linearLayoutManager;

    public TypeBottomSheetApdapter(List<ListTypeProduct> listTypeProducts, TypeBotSheetInterface typeBotSheetInterface, LinearLayoutManager linearLayoutManager) {
        this.listTypeProducts = listTypeProducts;
        this.typeBotSheetInterface = typeBotSheetInterface;
        this.linearLayoutManager = linearLayoutManager;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_bottomsheet, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TypeBottomSheetApdapter.ViewHolder holder, int position) {
        ListTypeProduct itemTypeProduct = listTypeProducts.get(position);
        if (itemTypeProduct == null){
            return;
        }
        holder.tv_typeName.setText(itemTypeProduct.getTypeName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeBotSheetInterface.itemClick(v,position);
            }
        });
//        holder.img_typeImage.setImageResource(itemTypeProduct.);
    }

    @Override
    public int getItemCount() {
        if (listTypeProducts!=null){
            return listTypeProducts.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_typeName;
        ImageView img_typeImage;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_typeName = itemView.findViewById(R.id.tv_type_bottomSheet);
            img_typeImage = itemView.findViewById(R.id.img_type_bottomSheet);
        }
    }
    public interface TypeBotSheetInterface {
        void itemClick(View v, int position);
    }
}
