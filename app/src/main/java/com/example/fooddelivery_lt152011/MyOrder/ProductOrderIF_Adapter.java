package com.example.fooddelivery_lt152011.MyOrder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_lt152011.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProductOrderIF_Adapter extends RecyclerView.Adapter<ProductOrderIF_Adapter.ViewHolder>{
    Context context;
    ArrayList<ModelDetailOrder> list;

    public ProductOrderIF_Adapter(Context context, ArrayList<ModelDetailOrder> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.product_item, parent, false );
        ViewHolder holder = new ViewHolder( v );
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductOrderIF_Adapter.ViewHolder holder, int position) {
        holder.productname.setText( list.get( position ).getProductName() );
        holder.productquantity.setText( String.valueOf( list.get( position ).getQuantity() ) );
        holder.productsize.setText( String.valueOf(  list.get( position ).getSizeName() ));
        holder.amounts.setText(String.valueOf(   list.get( position ).getAmount()) );
        Picasso.get().load( list.get( position ).getProductImage() ).into( holder.productimg );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productimg;
        TextView productname,amounts,productquantity,productsize;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super( itemView );
            productimg=itemView.findViewById( R.id.productimg );
            productname=itemView.findViewById( R.id.productname );
            amounts=itemView.findViewById( R.id.amounts );
            productquantity=itemView.findViewById( R.id.productquantity );
            productsize=itemView.findViewById( R.id.productsize );
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            } );
        }
    }
}
