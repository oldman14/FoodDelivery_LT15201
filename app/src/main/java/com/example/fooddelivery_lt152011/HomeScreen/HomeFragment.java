package com.example.fooddelivery_lt152011.HomeScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;

import org.jetbrains.annotations.NotNull;


public class HomeFragment extends Fragment {
    ProductViewModel productViewModel;
    ProgressBar progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        productViewModel.getIsConnect().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean==true){
                    progressBar.setVisibility(View.VISIBLE);
                } else{
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        progressBar = view.findViewById(R.id.processBar_homefm);
        return view;
    }
}