package com.example.fooddelivery_lt152011.productScreen;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fooddelivery_lt152011.HTTP_URL;
import com.example.fooddelivery_lt152011.MainActivity;
import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.databinding.BottomSheetBinding;
import com.example.fooddelivery_lt152011.databinding.FragmentProductBinding;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.FavoriteService;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DecimalFormat;
import java.util.List;

public class FavoriteFragment extends Fragment {
    BottomNavigationView bottomNavigationView;
    List<Product> productFavorite;
    private OneItemClick oneItemClick;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    ProductViewModel mViewModel;
    HttpAdapter httpAdapter;
    FavoriteService favoriteService;
    public BottomSheetBinding bottomSheetBinding;
    LifecycleOwner lifecycleOwner;
    public RelativeLayout bottomsheetLayoutItem;

    public FavoriteFragment(List<Product> productFavorite) {
        this.productFavorite = productFavorite;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        bottomNavigationView = getActivity().findViewById(R.id.b)
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        bottomSheetBinding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet, container, false);
        recyclerView = view.findViewById(R.id.rec_favorite);
        bottomsheetLayoutItem = view.findViewById(R.id.bottomSheet_detailproduct);
        httpAdapter = new HttpAdapter();
        httpAdapter.setBaseUrl( HTTP_URL.Final_URL );
        favoriteService = httpAdapter.create(FavoriteService.class);
        mViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        mViewModel.getQuantityItem().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("TAG", "onChanged: "+integer);
            }
        });
        RecProductAdapter recProductAdapter = new RecProductAdapter(getContext(), productFavorite, getViewLifecycleOwner());
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.scrollToPosition(0);
        recyclerView.computeHorizontalScrollOffset();
        recyclerView.setAdapter(recProductAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

}