package com.example.fooddelivery_lt152011.productScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RadBtnAdapter extends RecyclerView.Adapter<RadBtnAdapter.ViewHolder> {
    List<Size> listSizeProduct;
    Context context;
    ProductViewModel productViewModel;

    public RadBtnAdapter(List<Size> listSizeProduct, Context context, ProductViewModel productViewModel) {
        this.listSizeProduct = listSizeProduct;
        this.context = context;
        this.productViewModel = productViewModel;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.radio_button_custom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RadBtnAdapter.ViewHolder holder, int position) {
        Size size = listSizeProduct.get(position);
        holder.tv_size.setText(size.getSizeName());
        holder.tv_sizePrice.setText("+"+String.valueOf(size.getSizePrice()));
        if (productViewModel.getSize().getValue().getSizeName()==size.getSizeName()){
            holder.imageView.setImageResource(R.drawable.ic_check_circle);
        } else {
            holder.imageView.setImageResource(R.drawable.ic_outline_circle_24);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productViewModel.setSize(size);
                holder.imageView.setImageResource(R.drawable.ic_check_circle);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listSizeProduct!=null){
            return listSizeProduct.size();
        } return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_size;
        TextView tv_sizePrice;
        ImageView imageView;
        ProductViewModel productViewModel;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_size = itemView.findViewById(R.id.size_radioBtn);
            tv_sizePrice = itemView.findViewById(R.id.priceSize);
            imageView = itemView.findViewById(R.id.img_check_radio);
        }
    }
}
