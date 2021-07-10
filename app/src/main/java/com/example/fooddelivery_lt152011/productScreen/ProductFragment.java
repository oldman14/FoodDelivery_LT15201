package com.example.fooddelivery_lt152011.productScreen;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.devs.readmoreoption.ReadMoreOption;
import com.example.fooddelivery_lt152011.databinding.BottomSheetBinding;
import com.example.fooddelivery_lt152011.databinding.BottomsheetCartItemBinding;
import com.example.fooddelivery_lt152011.databinding.FragmentProductBinding;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;
import com.example.fooddelivery_lt152011.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;

public class ProductFragment extends Fragment implements OneItemClick, TypeBottomSheetApdapter.TypeBotSheetInterface {
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
    private List<ListTypeProduct> listTypeProduct;
    private ImageView imgFavorite;
    private TextView typeSelect;
    public BottomSheetDialog bottomSheetDialogType;
    public RadioGroup radioGroup;
    public TypeBottomSheetApdapter.TypeBotSheetInterface typeBotSheetInterface;
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
                if (typeProducts!=null){
                    fragmentProductBinding.setIsLoading(false);
                    recTypeAdapter = new RecTypeAdapter(typeProducts, getContext(), ProductFragment.this::onItemClick);
                    linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.scrollToPosition(0);
                    recyclerView.computeHorizontalScrollOffset();
                    fragmentProductBinding.setIsLoading(true);
                    recyclerView.setAdapter(recTypeAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                            super.onScrollStateChanged(recyclerView, newState);
                            int position = linearLayoutManager.findFirstVisibleItemPosition();
                            typeSelect.setText(listTypeProduct.get(position).getTypeName());
                        }
                    });
                } else {
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });

        mViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                View view = bottomsheetCartItemBinding.getRoot();
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomsheetLayout);
                if (cartItems.size()!=0){
                    if (bottomSheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }
            }
        });
        mViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                tv_price.setText(new DecimalFormat("##,###đ").format(aDouble));
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
                listTypeProduct = listTypeProducts;
                typeProAdapter = new TypeProAdapter(getContext(), R.layout.item_typeproduct, listTypeProducts);
                typeSelect.setText(listTypeProducts.get(0).getTypeName());
                typeSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShowBottomSheetType();
                    }
                });


            }
        });
        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBottomSheetType();
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
        imgFavorite = view.findViewById(R.id.btn_favorite_productfragment);
        bottomsheetLayoutItem = view.findViewById(R.id.bottomSheet_detailproduct);
        bottomsheetLayout = view.findViewById(R.id.bottom_sheet_cart_layout);
        tv_price = bottomsheetLayout.findViewById(R.id.priceCartBottomSheet);
        tv_quantityCart = view.findViewById(R.id.quantityCartItem);
        typeSelect  = view.findViewById(R.id.tv_pro_selected);
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
        return view;
    }
    public void ShowBottomSheetType(){
        bottomSheetDialogType = new BottomSheetDialog(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_type,null);
        bottomSheetDialogType.setContentView(view);
        RecyclerView recyclerView = view.findViewById(R.id.rec_bottomSheet_type);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        TypeBottomSheetApdapter typeBottomSheetApdapter  = new TypeBottomSheetApdapter(listTypeProduct, ProductFragment.this::itemClick, linearLayoutManager);
        recyclerView.setAdapter(typeBottomSheetApdapter);
        bottomSheetDialogType.show();
    }

    @Override
    public void itemClick(View v,int position) {

        typeSelect.setText(listTypeProduct.get(position).getTypeName());
        linearLayoutManager.scrollToPositionWithOffset(position, 10);
        bottomSheetDialogType.dismiss();
    }

    public class ProductHandleClick {
        Context context;
        public ProductHandleClick(Context context) {
            this.context = context;
        }
        public void addItemProduct(Product product,  int quantity, int sizeID ){
            Toast.makeText(context, sizeID+"", Toast.LENGTH_SHORT).show();
            mViewModel.addItemToCart(product, quantity, sizeID);
            bottomSheetDialog.dismiss();
        }
        public String convertString(int price){
            return new DecimalFormat("##,###đ").format(price);
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
        radioGroup = view.findViewById(R.id.radioGroupSize);
        mViewModel.setSize(product.getSizes().get(0));
        RadioButton radioButton = view.findViewById(R.id.btn_radio_size_nho);
        radioButton.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_radio_size_nho:
                        mViewModel.setSize(product.getSizes().get(0));
                        break;
                    case R.id.btn_radio_size_lon:
                        mViewModel.setSize(product.getSizes().get(1));
                        break;
                }
            }
        });
        mViewModel.getSize().observe(getViewLifecycleOwner(), new Observer<Size>() {
            @Override
            public void onChanged(Size size) {
                int quantity = mViewModel.getQuantityItem().getValue();
                mViewModel.setPriceProduct((quantity * product.getProductPrice())+(size.getSizePrice()*quantity));
            }
        });
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
                mViewModel.setPriceProduct((integer * product.getProductPrice())+mViewModel.getSize().getValue().getSizePrice());
            }
        });
        Button btn_chonMon = view.findViewById(R.id.btn_chonMon);
        mViewModel.getPriceProduct().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                String s = new DecimalFormat("##,###đ").format(integer);
                btn_chonMon.setText("Chọn món - "+s);
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
        bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull @org.jetbrains.annotations.NotNull View bottomSheet, int newState) {
            }
            @Override
            public void onSlide(@NonNull @org.jetbrains.annotations.NotNull View bottomSheet, float slideOffset) {
//                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}