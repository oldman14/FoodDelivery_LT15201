package com.example.fooddelivery_lt152011.productScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.databinding.CartRowBinding;
import com.example.fooddelivery_lt152011.databinding.FragmentCartBinding;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;

public class CartItemAdapter extends  RecyclerView.Adapter<CartItemAdapter.CartHolder>{
    List<CartItem> cartItems;
    Context context;
    CartRowBinding cartRowBinding;
    public CartItemAdapter(List<CartItem> cartItems, Context context) {
        this.cartItems = cartItems;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.cart_row, parent, false);
        CartRowBinding cartRowBinding = CartRowBinding.inflate(layoutInflater, parent, false);
        return new CartHolder(view, cartRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CartItemAdapter.CartHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        String price = new DecimalFormat("###,###đ").format((cartItem.product.getProductPrice()+cartItem.size.getSizePrice())*cartItem.quantity);
        holder.tv_name.setText(cartItem.product.getProductName());
        holder.tv_price.setText(price);
        holder.tv_size.setText(cartItem.size.SizeName);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class CartHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_size, tv_price;
        CartRowBinding cartRowBinding;
        public CartHolder(@NonNull @NotNull View itemView, CartRowBinding cartRowBinding) {
            super(itemView);
            this.cartRowBinding = cartRowBinding;
            tv_name = (TextView) itemView.findViewById(R.id.productNameTextView);
            tv_size = itemView.findViewById(R.id.size_cartRow);
            tv_price = itemView.findViewById(R.id.productTotalPriceTextView);
        }
    }
}
