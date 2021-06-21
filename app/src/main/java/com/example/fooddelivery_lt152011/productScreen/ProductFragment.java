package com.example.fooddelivery_lt152011.productScreen;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fooddelivery_lt152011.databinding.BottomSheetBinding;
import com.example.fooddelivery_lt152011.databinding.FragmentProductBinding;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductListViewModel;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;
import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.TypeProductViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment implements OneItemClick{
    public Spinner spinner;
    public TypeProAdapter typeProAdapter;
    public RecTypeAdapter recTypeAdapter;
    public ProductViewModel mViewModel;
    public BottomSheetBehavior bottomSheetBehavior;
    public BottomSheetBinding bottomSheetBinding;
    public RelativeLayout bottomsheetLayout;
    private OneItemClick oneItemClick;
    public FragmentProductBinding fragmentProductBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);

        mViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<TypeProduct>>() {
            @Override
            public void onChanged(List<TypeProduct> typeProducts) {
                RecyclerView recyclerView = view.findViewById(R.id.rec_product_fragment);
                recTypeAdapter = new RecTypeAdapter(typeProducts, getContext(), ProductFragment.this::onItemClick);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.scrollToPosition(1);
                recyclerView.setAdapter(recTypeAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });
        TypeProductViewModel typeProductViewModel = new ViewModelProvider(requireActivity()).get(TypeProductViewModel.class);
        typeProductViewModel.getTypeProductReponse().observe(getViewLifecycleOwner(), new Observer<TypeResponse>() {
            @Override
            public void onChanged(TypeResponse typeResponse) {
                Log.d("TAG", "TypeProduct: "+typeResponse.getTypeProduct());
            }
        });
        ProductListViewModel productListViewModel = new ViewModelProvider(requireActivity()).get(ProductListViewModel.class);
        productListViewModel.getProductReponse().observe(getViewLifecycleOwner(), new Observer<ProductReponse>() {
            @Override
            public void onChanged(ProductReponse productReponse) {
                Toast.makeText(getContext(), "viewmodel", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onViewMOdel: "+productReponse.getProduct().get(0));

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentProductBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_product, container, false);
        bottomSheetBinding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet, container, false);
        View view = fragmentProductBinding.getRoot();
        //here data must be an instance of the class MarsDataProvider
//        fragmentProductBinding = FragmentProductBinding.inflate(inflater, container, false);
        typeProAdapter = new TypeProAdapter(getContext(),R.layout.item_typeproduct, getAllTypeProduct());
//        bottomsheetLayout = view.findViewById(R.id.bottomSheet_detailproduct);
//        bottomSheetBehavior = bottomSheetBehavior.from(bottomsheetLayout);
        //viewmodel
        spinner = view.findViewById(R.id.spin_product);
        spinner.setAdapter(typeProAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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
        fragmentProductBinding.setProduct(mViewModel);
        bottomSheetBinding.setProduct(mViewModel);
        View view = bottomSheetBinding.getRoot();
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        if (view.getParent()!=null){
            ((ViewGroup)view.getParent()).removeView(view);
        }
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

//        bottomSheetDialog.setCancelable(false);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
//        bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
//        bottomSheetBehavior.setPeekHeight(0);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull @org.jetbrains.annotations.NotNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull @org.jetbrains.annotations.NotNull View bottomSheet, float slideOffset) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

//        if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
//        }   else {
//            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//
//        }
        //        ProductDetailFragment fragment = new ProductDetailFragment();
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.frame_container, fragment, "productDetail")
//                .addToBackStack(null)
////                .commit();
//        if (bottomSheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
//            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        }
    }
}