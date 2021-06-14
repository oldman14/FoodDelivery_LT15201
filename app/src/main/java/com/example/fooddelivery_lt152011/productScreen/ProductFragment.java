package com.example.fooddelivery_lt152011.productScreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;
import com.example.fooddelivery_lt152011.R;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment implements OneItemClick{
    public Spinner spinner;
    public TypeProAdapter typeProAdapter;
    public RecTypeAdapter recTypeAdapter;
    ProductViewModel mViewModel;
    private OneItemClick oneItemClick;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        Log.d("TAG", "onCreateView: xcxczx ");

        mViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<TypeProduct>>() {
            @Override
            public void onChanged(List<TypeProduct> typeProducts) {
                Log.d("TAG", "onCreateView: " + typeProducts);
                RecyclerView recyclerView = view.findViewById(R.id.rec_product_fragment);
                recTypeAdapter = new RecTypeAdapter(typeProducts, getContext(), ProductFragment.this::onItemClick);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setAdapter(recTypeAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        typeProAdapter = new TypeProAdapter(getContext(),R.layout.item_typeproduct, getAllTypeProduct());

        //viewmodel


        spinner = view.findViewById(R.id.spin_product);
        spinner.setAdapter(typeProAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), typeProAdapter.getItem(position).getTypeName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
    private List<TypeProduct> getAllTypeProduct() {
        List<TypeProduct> productType = new ArrayList<>();
        productType.add(new TypeProduct(0, "Cà phê"));
        productType.add(new TypeProduct(1, "Đá xoay vòng tròn"));
        productType.add(new TypeProduct(2, "Nước ép hoa đậu biếc"));
        productType.add(new TypeProduct(3, "Bánh xu cà na"));
        return productType;
    }


    @Override
    public void onItemClick(Product product) {
        mViewModel.setProduct(product);
        ProductDetailFragment fragment = new ProductDetailFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, fragment, "productDetail")
                .addToBackStack(null)
                .commit();
    }
}