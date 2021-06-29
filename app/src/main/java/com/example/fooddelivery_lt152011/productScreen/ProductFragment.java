package com.example.fooddelivery_lt152011.productScreen;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.devs.readmoreoption.ReadMoreOption;
import com.example.fooddelivery_lt152011.databinding.BottomSheetBinding;
import com.example.fooddelivery_lt152011.databinding.BottomsheetCartItemBinding;
import com.example.fooddelivery_lt152011.databinding.FragmentProductBinding;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductListViewModel;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;
import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.TypeProductViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment implements OneItemClick {
    public Spinner spinner;
    public TypeProAdapter typeProAdapter;
    public RecTypeAdapter recTypeAdapter;
    public ProductViewModel mViewModel;
    public BottomSheetBehavior bottomSheetBehavior;
    public BottomSheetBinding bottomSheetBinding;
    public RelativeLayout bottomsheetLayoutItem;
    public RelativeLayout bottomsheetLayout;
    private LinearLayout linearLayoutBottom;
    private OneItemClick oneItemClick;
    public TextView tv_price, tv_quantityCart, tv_quantityItem;
    public FragmentProductBinding fragmentProductBinding;
    private BottomsheetCartItemBinding bottomsheetCartItemBinding;
    public ImageButton btn_minus, btn_plus;
    public ReadMoreOption readMoreOption;
    public BottomSheetDialog bottomSheetDialog;
    public LinearLayoutManager linearLayoutManager;
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
                linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.scrollToPosition(1);
                recyclerView.setAdapter(recTypeAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);

            }
        });
        mViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                View view = bottomsheetCartItemBinding.getRoot();
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomsheetLayout);
                if (cartItems.size()!=0){
                    Log.d("TAG", "onChanged: "+cartItems.size());
                    if (bottomSheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }
            }
        });
        mViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                Log.d("TAG", "onChanged: "+aDouble);
                tv_price.setText(aDouble.toString()+"đ");
            }
        });
        mViewModel.getCartQuantity().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tv_quantityCart.setText(integer.toString()+" món trong giỏ hàng");
            }
        });
        mViewModel.getTypeProduct().observe(getViewLifecycleOwner(), new Observer<List<ListTypeProduct>>() {
            @Override
            public void onChanged(List<ListTypeProduct> listTypeProducts) {
                typeProAdapter = new TypeProAdapter(getContext(), R.layout.item_typeproduct, listTypeProducts);
                spinner = view.findViewById(R.id.spin_product);
                spinner.setAdapter(typeProAdapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        linearLayoutManager.scrollToPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
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
        bottomsheetCartItemBinding = DataBindingUtil.inflate(inflater, R.layout.bottomsheet_cart_item,container, false);
        View view = fragmentProductBinding.getRoot();
        bottomsheetLayoutItem = view.findViewById(R.id.bottomSheet_detailproduct);
//        btn_minus = view.findViewById(R.id.btn_minus_quantity);
//        btn_plus =  view.findViewById(R.id.btn_plus_quantity);
//        tv_quantityItem = view.findViewById(R.id.tv_quantity_detail_product);
        bottomsheetLayout = view.findViewById(R.id.bottom_sheet_cart_layout);
        tv_price = bottomsheetLayout.findViewById(R.id.priceCartBottomSheet);
        tv_quantityCart = view.findViewById(R.id.quantityCartItem);

        bottomsheetLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartFragment fragment = new CartFragment();
                 getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, fragment, "productDetail")
                .addToBackStack(null)
                .commit();
            }
        });
        //read more text view
        readMoreOption = new ReadMoreOption.Builder(getContext())
                .textLength(3, ReadMoreOption.TYPE_LINE) // OR
                //.textLength(300, ReadMoreOption.TYPE_CHARACTER)
                .moreLabel("Xem thêm")
                .lessLabel("Rút gọn")
                .moreLabelColor(R.color.placeholder_color)
                .lessLabelColor(R.color.orange_light)
                .labelUnderLine(true)
                .expandAnimation(true)
                .build();
        //here data must be an instance of the class MarsDataProvider
//        fragmentProductBinding = FragmentProductBinding.inflate(inflater, container, false);


//        bottomsheetLayout = view.findViewById(R.id.bottomSheet_detailproduct);
//        bottomSheetBehavior = bottomSheetBehavior.from(bottomsheetLayout);
        //viewmodel


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
    public class ProductHandleClick {
        Context context;

        public ProductHandleClick(Context context) {
            this.context = context;
        }
        public void addItemProduct(Product product,  int quantity){
            mViewModel.addItemToCart(product, quantity);
            linearLayoutManager.scrollToPosition(0);
            bottomSheetDialog.dismiss();
        }
    }
    @Override
    public void onItemClick(Product product) {
        mViewModel.setProduct(product);
        fragmentProductBinding.setProduct(mViewModel);
        bottomSheetBinding.setProduct(mViewModel);
        ProductHandleClick productHandleClick = new ProductHandleClick(getContext());
        bottomSheetBinding.setHandleClick(productHandleClick);
        View view = bottomSheetBinding.getRoot();
        btn_minus = view.findViewById(R.id.btn_minus_quantity);
        btn_plus = view.findViewById(R.id.btn_plus_quantity);
        tv_quantityItem = view.findViewById(R.id.tv_quantity_detail_product);
        TextView detail_product = view.findViewById(R.id.detail_product);
        readMoreOption.addReadMoreTo(detail_product, product.ProductNote);
        mViewModel.setQuantityItem(1);
        ImageButton imageButton_favorute = view.findViewById(R.id.imgBtn_favourite);
        mViewModel.getFavoite().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean==true){
                    imageButton_favorute.setImageResource(R.drawable.ic_favorite_24);
                } else {
                    imageButton_favorute.setImageResource(R.drawable.ic_favorite_border_24);
                }
            }
        });
        mViewModel.getQuantityItem().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tv_quantityItem.setText(integer.toString());
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.minusQuantity();
            }
        });
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.plusQuantity();
            }
        });
        bottomSheetDialog = new BottomSheetDialog(getContext());
        if (view.getParent()!=null){
            ((ViewGroup)view.getParent()).removeView(view);
        }
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

//        bottomSheetDialog.setCancelable(false);
        bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
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


    }
    public void updateQuantity(){
        tv_quantityItem.setText(mViewModel.quantity);
    }

}