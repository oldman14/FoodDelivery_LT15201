package com.example.shipper_lt15201.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shipper_lt15201.R;

public class NoLive_Fragment extends Fragment {
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view= inflater.inflate( R.layout.fragment_no_live, container, false );
    return view;
    }
}