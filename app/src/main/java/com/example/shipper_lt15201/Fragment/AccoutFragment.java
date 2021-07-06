package com.example.shipper_lt15201.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shipper_lt15201.LoginScreen.ModelShipper;
import com.example.shipper_lt15201.LoginScreen.SendOTPActivity;
import com.example.shipper_lt15201.LoginScreen.ShipperDAO;
import com.example.shipper_lt15201.R;

public class AccoutFragment extends Fragment {
ShipperDAO dao;
TextView tvsdt,tvNameShip;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_accout, container, false );
        tvsdt=view.findViewById( R.id.tvsdt );
        tvNameShip=view.findViewById( R.id.tvNameShip );
        tvsdt.setText( SendOTPActivity.phone );
        dao=new ShipperDAO( getContext() );
        ModelShipper nameimg=dao.getShipperName( Integer.parseInt( SendOTPActivity.phone ) );
        tvNameShip.setText(nameimg.getShipName());

        return view;
    }

}