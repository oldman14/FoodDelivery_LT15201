package com.example.fooddelivery_lt152011.productScreen;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.example.fooddelivery_lt152011.HTTP_URL;
import com.example.fooddelivery_lt152011.LoginScreen.DbHelper;
import com.example.fooddelivery_lt152011.LoginScreen.ModelUser;
import com.example.fooddelivery_lt152011.LoginScreen.UserDAO;
import com.example.fooddelivery_lt152011.MainActivity;
import com.example.fooddelivery_lt152011.MyOrder.OrderDAO;
import com.example.fooddelivery_lt152011.databinding.BottomSheetBinding;
import com.example.fooddelivery_lt152011.databinding.BottomsheetCartItemBinding;
import com.example.fooddelivery_lt152011.databinding.CartRowBinding;
import com.example.fooddelivery_lt152011.databinding.FragmentCartBinding;
import com.example.fooddelivery_lt152011.databinding.FragmentProductBinding;

import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.OderService;
import com.example.fooddelivery_lt152011.productScreen.entities.InfoLocation;
import com.example.fooddelivery_lt152011.productScreen.entities.Store;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.LocationViewModel;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;
import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.StoreViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ProductFragment extends Fragment implements OneItemClick, TypeBottomSheetApdapter.TypeBotSheetInterface {
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
    public RadBtnAdapter radBtnAdapter;
    public RecyclerView recyclerViewRad;
    public FragmentCartBinding fragmentCartBinding;
    UserDAO userDAO;
    OrderDAO orderDAO;
    DbHelper dbHelper;
    ProductViewModel productViewModel;
    StoreViewModel storeViewModel;
    LocationViewModel locationViewModel;
    HttpAdapter httpAdapter;
    OderService oderService;
    Store store;
    InfoLocation infoLocation;
    public CartRowBinding cartRowBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userDAO = new UserDAO();
        orderDAO = new OrderDAO();
        dbHelper = new DbHelper(getActivity());
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
                        public void onScrollStateChanged(@NonNull  RecyclerView recyclerView, int newState) {
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
        fragmentCartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container,false);
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
//                CartFragment fragment = new CartFragment();
//                 getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.frame_container, fragment, "productDetail")
//                .addToBackStack(null)
//                .commit();
                oderSheet();
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
        public void addItemProduct(Product product,  int quantity, Size size, int amount ){
            Toast.makeText(context, size+"", Toast.LENGTH_SHORT).show();
            mViewModel.addItemToCart(product, quantity, size, amount);
            bottomSheetDialog.dismiss();
        }
        public String convertString(int price){
            return new DecimalFormat("##,###đ").format(price);
        }
    }
    public void oderSheet(){
        View view = fragmentCartBinding.getRoot();
        CartItemAdapter cartItemAdapter= new CartItemAdapter(mViewModel.getCart().getValue(), getContext());
        RecyclerView recyclerView = view.findViewById(R.id.cartRecyclerView);
        recyclerView.setAdapter(cartItemAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        Button placeOrderButton = view.findViewById(R.id.placeOrderButton);
        if (view.getParent()!=null){
            ((ViewGroup)view.getParent()).removeView(view);
        }
        httpAdapter = new HttpAdapter();
        httpAdapter.setBaseUrl( HTTP_URL.Final_URL );
        oderService = httpAdapter.create(OderService.class);
        storeViewModel = new ViewModelProvider(requireActivity()).get(StoreViewModel.class);
        storeViewModel.getStore().observe(getViewLifecycleOwner(), new Observer<Store>() {
            @Override
            public void onChanged(Store s) {
                Log.d( "TAG", "Check store"+s.StoreName );
                store = s;
            }
        });
        locationViewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        locationViewModel.getLocation().observe(getViewLifecycleOwner(), new Observer<InfoLocation>() {
            @Override
            public void onChanged(InfoLocation location) {
                infoLocation = location;
            }
        });
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        productViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                JSONArray jsonArray = new JSONArray();

                for (CartItem item: cartItems) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("productID", item.product.ProductID);
                        jsonObject.put("quantity", item.getQuantity());
                        jsonObject.put("sizeID", item.size.SizeID);
                        jsonObject.put("amount", item.amount);// nãi e thêm chỗ này vs sửa mấy cái put ên dưới
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(jsonObject);
                }
                JSONObject oderObject = new JSONObject();
                UUID oderID = UUID.randomUUID();

//                int oderID = orderDAO.getNewID();
                try {
                    ModelUser modelUser = userDAO.getUserNames(dbHelper.getUser().getUserPhone());
                    oderObject.put("userID", modelUser.getUserID());
                    oderObject.put("orderID", oderID);
                    oderObject.put("storeID", store.StoreID);
                    oderObject.put("address", infoLocation.getAddress());
                    oderObject.put("lat", infoLocation.getLocation().getLatitude());
                    oderObject.put("lng", infoLocation.getLocation().getLongitude());
                    oderObject.put("totalMoney", productViewModel.getTotalPrice().getValue());
                    oderObject.put("detailOrder", jsonArray);
                } catch (Exception e){
                    Log.d("TAG", "onChanged: "+e);
                }

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("orderfood",oderObject.toString());
                placeOrderButton.setEnabled(cartItems.size() > 0);
                placeOrderButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d( "TAG", "Log oder: "+oderObject.toString() );
                        Log.d("TAG", "onClick: "+oderObject.toString());
                        if (oderService.insertOder(oderObject.toString())){
                            Toast.makeText(getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                            getActivity().getFragmentManager().popBackStack();
                        }
                    }
                });
            }
        });

        productViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                String price = new DecimalFormat("##,###đ").format(aDouble);
                fragmentCartBinding.orderTotalTextView.setText("Tổng cộng: " + price);
            }
        });

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
    @Override
    public void onItemClick(Product product) {
        mViewModel.setProduct(product);
//        fragmentProductBinding.setProduct(mViewModel);
        bottomSheetBinding.setProduct(mViewModel);
        ProductHandleClick productHandleClick = new ProductHandleClick(getContext());
        bottomSheetBinding.setHandleClick(productHandleClick);
        View view = bottomSheetBinding.getRoot();
        mViewModel.setSize(product.getSizes().get(0));
        mViewModel.getSize().observe(getViewLifecycleOwner(), new Observer<Size>() {
            @Override
            public void onChanged(Size size) {
                int quantity = mViewModel.getQuantityItem().getValue();
                mViewModel.setPriceProduct((quantity * product.getProductPrice())+(size.getSizePrice()*quantity));
                Toast.makeText(getContext(), ""+(quantity*size.getSizePrice()), Toast.LENGTH_SHORT).show();

            }
        });
        radBtnAdapter = new RadBtnAdapter(product.getSizes(), getContext(), mViewModel);
        recyclerViewRad = view.findViewById(R.id.recRadioButton);
        recyclerViewRad.setAdapter(radBtnAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewRad.setLayoutManager(layoutManager);
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
                mViewModel.setPriceProduct((integer * product.getProductPrice())+mViewModel.getSize().getValue().getSizePrice()*integer);
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