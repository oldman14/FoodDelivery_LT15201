package com.example.fooddelivery_lt152011.productScreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_lt152011.MainActivity;
import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.databinding.BottomSheetBinding;
import com.example.fooddelivery_lt152011.databinding.CartRowBinding;
import com.example.fooddelivery_lt152011.databinding.FragmentCartBinding;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;

public class CartItemAdapter extends  RecyclerView.Adapter<CartItemAdapter.CartHolder>{
    List<CartItem> cartItems;
    Context context;
    CartRowBinding cartRowBinding;
    ProductViewModel mViewModel;
    public CartItemInterface cartItemInterface;
    public BottomSheetBinding bottomSheetBinding;
    public CartItemAdapter(List<CartItem> cartItems, Context context, ProductViewModel mViewModel, CartItemInterface cartItemInterface) {
        this.cartItems = cartItems;
        this.context = context;
        this.mViewModel =  mViewModel;
        this.cartItemInterface = cartItemInterface;

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
        Log.d("TAG", "onBindViewHolder: "+cartItem.getProduct().getProductName()+cartItem.size.SizeName);
        String price = new DecimalFormat("###,###đ").format((cartItem.product.getProductPrice()+cartItem.size.getSizePrice())*cartItem.quantity);
        holder.tv_name.setText(cartItem.product.getProductName());
        holder.tv_price.setText(price);
        holder.tv_size.setText(cartItem.size.SizeName);
        holder.tv_quantity.setText("x"+String.valueOf(cartItem.quantity));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItemInterface.EditItemClick(cartItem);
            }
        });
    }
    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class CartHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_size, tv_price, tv_quantity;
        CartRowBinding cartRowBinding;
        public CartHolder(@NonNull @NotNull View itemView, CartRowBinding cartRowBinding) {
            super(itemView);
            this.cartRowBinding = cartRowBinding;
            tv_name = (TextView) itemView.findViewById(R.id.productNameTextView);
            tv_size = itemView.findViewById(R.id.size_cartRow);
            tv_price = itemView.findViewById(R.id.productTotalPriceTextView);
            tv_quantity = itemView.findViewById(R.id.quantity_cartRow);
        }
    }
    public interface CartItemInterface {
        void EditItemClick(CartItem cartItem);
    }
}
