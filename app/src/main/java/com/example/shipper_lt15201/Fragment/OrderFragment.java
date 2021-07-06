package com.example.shipper_lt15201.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shipper_lt15201.HomeActivity;
import com.example.shipper_lt15201.LoginScreen.ModelShipper;
import com.example.shipper_lt15201.LoginScreen.SendOTPActivity;
import com.example.shipper_lt15201.LoginScreen.ShipperDAO;
import com.example.shipper_lt15201.ModelOrder.ModelOrder;
import com.example.shipper_lt15201.ModelOrder.OrderAdapter;
import com.example.shipper_lt15201.ModelOrder.OrderDAO;
import com.example.shipper_lt15201.R;

import java.util.ArrayList;

public class OrderFragment extends Fragment {
    RecyclerView rcv;
    ShipperDAO dao;
    OrderDAO orderDAO;
    ArrayList<ModelOrder> list;
    OrderAdapter adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        HomeActivity.toolbar.setVisibility( View.VISIBLE );
        HomeActivity.bottomNavigationView.setVisibility( View.VISIBLE );
        View view = inflater.inflate( R.layout.fragment_oder, container, false );
        rcv = view.findViewById( R.id.rcvOrder );
        dao = new ShipperDAO( getContext() );
        ModelShipper nameimg = dao.getShipperName( Integer.parseInt( SendOTPActivity.phone ) );
        list = new ArrayList<>();
        orderDAO = new OrderDAO( getActivity() );
        list = orderDAO.getItemOrder( nameimg.getShipID() );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getActivity(), LinearLayoutManager.VERTICAL, false );
        rcv.setLayoutManager( layoutManager );
        adapter = new OrderAdapter( getContext(), list );
        rcv.setAdapter( adapter );
        HomeActivity.btnswitch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.btnswitch.setChecked( true );
                if (list.get( OrderAdapter.pos ).getStatus().equals( "ĐANG GIAO" )) {
                    AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
                    builder.setTitle( "Food Delivery.VN" );
                    builder.setMessage( "Không thể tắt vì bạn đang có đơn hàng" );
                    builder.setCancelable( false );
                    builder.setNegativeButton( "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    } );
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        } );
        return view;
    }
}