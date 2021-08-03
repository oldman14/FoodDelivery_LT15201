package com.example.fooddelivery_lt152011.productScreen;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.fooddelivery_lt152011.MainActivity;
import com.example.fooddelivery_lt152011.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class FavoriteFragment extends Fragment {
    BottomNavigationView bottomNavigationView;
    Context context;
    List<Product> productFavorite;
    private OneItemClick oneItemClick;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    public FavoriteFragment(Context context, List<Product> productFavorite, OneItemClick oneItemClick) {
        this.context = context;
        this.productFavorite = productFavorite;
        this.oneItemClick = oneItemClick;
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
        recyclerView = view.findViewById(R.id.rec_favorite);
        RecProductAdapter recProductAdapter = new RecProductAdapter(context, productFavorite, oneItemClick );
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.scrollToPosition(0);
        recyclerView.computeHorizontalScrollOffset();
        recyclerView.setAdapter(recProductAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }
}