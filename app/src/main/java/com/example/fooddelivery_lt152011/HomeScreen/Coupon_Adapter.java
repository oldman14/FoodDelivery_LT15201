package com.example.fooddelivery_lt152011.HomeScreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.productScreen.ProductFragment;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Coupon_Adapter extends RecyclerView.Adapter<Coupon_Adapter.ViewHolder> {
    public static double couponprice;
    ArrayList<ModelCoupon> lcoupon;
    Context context;
    public  static   double discount;
    public static  double Finalprice;

    public Coupon_Adapter(ArrayList<ModelCoupon> lcoupon, Context context) {
        this.lcoupon = lcoupon;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.item_coupon, parent, false );
        ViewHolder holder = new ViewHolder( v );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Coupon_Adapter.ViewHolder holder, int position) {
        holder.notecoupon.setText( lcoupon.get( position ).getCouponNote() );
        Picasso.get().load( lcoupon.get( position ).getCouponImagge() ).into( holder.imgcoupon );
    }

    @Override
    public int getItemCount() {
        return lcoupon.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgcoupon;
        TextView notecoupon;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super( itemView );
            imgcoupon = itemView.findViewById( R.id.imgcoupon );
            notecoupon = itemView.findViewById( R.id.notecoupon );
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    couponprice = lcoupon.get( getAdapterPosition() ).getCouponPrice();
                    Log.d( "TAG", "onClick123: " + couponprice );

                    discount= (ProductFragment.productViewModel.getTotalPrice().getValue() * (Coupon_Adapter.couponprice / 100));
                    Log.d( "TAG", "onClick: " + discount );
                    String price = new DecimalFormat( "##,###đ" ).format( ProductFragment.productViewModel.getTotalPrice().getValue() - discount );
//                    Finalprice=ProductFragment.productViewModel.getTotalPrice().getValue() - discount;

                    ProductFragment.finalPrice=ProductFragment.productViewModel.getTotalPrice().getValue() - discount;
                    Log.d( "TAG", "onClick123123123: "+  ProductFragment.finalPrice );
                    String money1 = new DecimalFormat( "##,###đ" ).format( ProductFragment.productViewModel.getTotalPrice().getValue() );
                    ProductFragment.total.setText( price );
                    ProductFragment.money.setText( money1 );
                    ProductFragment.fragmentCartBinding.orderTotalTextView.setText( "TỔNG CỘNG : " + price );
                    ProductFragment.dialog.dismiss();
                }
            } );
        }
    }
}
