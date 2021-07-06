package com.example.shipper_lt15201.ModelOrder;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shipper_lt15201.Fragment.IForderFragment;
import com.example.shipper_lt15201.LoginScreen.ModelShipper;
import com.example.shipper_lt15201.LoginScreen.SendOTPActivity;
import com.example.shipper_lt15201.LoginScreen.ShipperDAO;
import com.example.shipper_lt15201.R;

import java.util.ArrayList;

import static com.example.shipper_lt15201.R.drawable.tx;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    public static int pos;
    Context context;
    OrderDAO dao;
    ShipperDAO shipperDAO;
    ArrayList<ModelOrder> list;

    public OrderAdapter(Context context, ArrayList<ModelOrder> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.item_order, parent, false );
        ViewHolder holder = new ViewHolder( v );
        return holder;
    }
    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, int position) {
        holder.tvstatus.setText( list.get( position ).getStatus() );
        holder.khoangcach.setText( "5.0 Km" );
        holder.tvtotalMoney.setText( String.valueOf( list.get( position ).getTotalMoney() ) );
        holder.imgOrder.setImageResource( tx );

        holder.nameOrder.setText( "CAFE SŨA ĐÁ" );
        holder.imglocationTX.setImageResource( R.drawable.ic_ens_24 );
        holder.tvlocationTX.setText( "THE COFFEHOUSE THỦ ĐỨC" );
        holder.imglocationreceive.setImageResource( R.drawable.ic_baseline_location_on_24 );
        holder.tvlocationreceive.setText( list.get( position ).getAddress() );
        holder.viewCT.setImageResource( R.drawable.ic_next_24 );


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvstatus, tvtotalMoney, khoangcach, nameOrder, tvlocationTX, tvlocationreceive;
        ImageView imgOrder, imglocationTX, imglocationreceive, viewCT, imgcach;

        public ViewHolder(View itemView) {
            super( itemView );
            tvstatus = itemView.findViewById( R.id.tvstatus );
            tvtotalMoney = itemView.findViewById( R.id.tvtotalMoney );
            khoangcach = itemView.findViewById( R.id.khoangcach );
            nameOrder = itemView.findViewById( R.id.nameOrder );
            tvlocationTX = itemView.findViewById( R.id.tvlocationTX );
            tvlocationreceive = itemView.findViewById( R.id.tvlocationreceive );
            imgOrder = itemView.findViewById( R.id.imgOrder );
            imglocationTX = itemView.findViewById( R.id.imglocationTX );
            imglocationreceive = itemView.findViewById( R.id.imglocationreceive );
            viewCT = itemView.findViewById( R.id.viewCT );
            imgcach = itemView.findViewById( R.id.imgcach );


            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos = getAdapterPosition();
                    if (list.get( getAdapterPosition() ).getStatus().equals( "đang đợi tài xế lấy hàng" )) {
                        AlertDialog.Builder builder = new AlertDialog.Builder( context );
                        builder.setTitle( "Food Delivery.VN" );
                        builder.setMessage( "Xác Nhận Đã Lấy Hàng" );
                        builder.setCancelable( false );
                        builder.setPositiveButton( "XÁC NHẬN", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dao = new OrderDAO( context );
                                shipperDAO = new ShipperDAO( context );
                                ModelShipper nameimg = shipperDAO.getShipperName( Integer.parseInt( SendOTPActivity.phone ) );
                                list = dao.getItemOrder( nameimg.ShipID );
                                String orderID = list.get( getAdapterPosition() ).getOrderID();
                                ModelStatusOrder update = dao.systemService.updateStatus( orderID, "ĐANG GIAO" );
                                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                                Fragment myFragment = new IForderFragment();
                                activity.getSupportFragmentManager().beginTransaction().replace( R.id.frame_container, myFragment ).addToBackStack( null ).commit();
                            }
                        } );
                        builder.setNegativeButton( "CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        } );
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                    if (list.get( getAdapterPosition() ).getStatus().equals( "ĐANG GIAO" )) {
                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        Fragment myFragment = new IForderFragment();
                        activity.getSupportFragmentManager().beginTransaction().replace( R.id.frame_container, myFragment ).addToBackStack( null ).commit();

                    }

                }
            } );

        }
    }
}
