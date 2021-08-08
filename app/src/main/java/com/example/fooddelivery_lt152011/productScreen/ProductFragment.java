package com.example.fooddelivery_lt152011.productScreen;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
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
import com.example.fooddelivery_lt152011.HomeScreen.CouponDAO;
import com.example.fooddelivery_lt152011.HomeScreen.Coupon_Adapter;
import com.example.fooddelivery_lt152011.HomeScreen.ModelCoupon;
import com.example.fooddelivery_lt152011.LoginScreen.DbHelper;
import com.example.fooddelivery_lt152011.LoginScreen.ModelUser;
import com.example.fooddelivery_lt152011.LoginScreen.UserDAO;
import com.example.fooddelivery_lt152011.MainActivity;
import com.example.fooddelivery_lt152011.MyOrder.InforOderFragment;
import com.example.fooddelivery_lt152011.MyOrder.OrderDAO;
import com.example.fooddelivery_lt152011.databinding.BottomSheetBinding;
import com.example.fooddelivery_lt152011.databinding.BottomsheetCartItemBinding;
import com.example.fooddelivery_lt152011.databinding.CartRowBinding;
import com.example.fooddelivery_lt152011.databinding.FragmentCartBinding;
import com.example.fooddelivery_lt152011.databinding.FragmentProductBinding;

import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.FavoriteService;
import com.example.fooddelivery_lt152011.networking.Service.OderService;
import com.example.fooddelivery_lt152011.productScreen.entities.Favorite;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ProductFragment extends Fragment  implements TypeBottomSheetApdapter.TypeBotSheetInterface, CartItemAdapter.CartItemInterface {
    public TypeProAdapter typeProAdapter;
    public  static   String oderID;
    public RecTypeAdapter recTypeAdapter;
    public  static TextView money;
    public static ProductViewModel productViewModel;
    public static FragmentCartBinding fragmentCartBinding;
    RecyclerView lvcoupon;
    Coupon_Adapter adapter;
    ArrayList<ModelCoupon> listcoupon;
    ArrayList<ModelCoupon> list;
    public ProductViewModel mViewModel;
    public BottomSheetBehavior bottomSheetBehavior;
    public BottomSheetBinding bottomSheetBinding;
    public RelativeLayout bottomsheetLayoutItem;
    public RelativeLayout bottomsheetLayout, bottomsheetOder;
    private LinearLayout linearLayoutBottom;
    double totalcoupon;
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
    public static AlertDialog dialog;
    public  static  TextView total;
    public ArrayList<ModelCoupon> lCoupon;
    CouponDAO couponDAO;
    public BottomSheetDialog bottomSheetDialogType;
    public RadioGroup radioGroup;
    public TypeBottomSheetApdapter.TypeBotSheetInterface typeBotSheetInterface;
    public RadBtnAdapter radBtnAdapter;
    public RecyclerView recyclerViewRad;
    BottomSheetBehavior bottomSheetBehaviorCartItem;
    UserDAO userDAO;
    ImageView back;
    OrderDAO orderDAO;
    DbHelper dbHelper;
    StoreViewModel storeViewModel;
    LocationViewModel locationViewModel;
    HttpAdapter httpAdapter;
    OderService oderService;
    FavoriteService favoriteService;
    Store store;
    InfoLocation infoLocation;
    public CartRowBinding cartRowBinding;
    List<Product> favoriteProduct;
    Fragment myFragmentFav;
    TextView streetName, address, phone, userName;
    double finalPrice;
    double discount;
    List<CartItem> cartItem;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity.navigationView.setVisibility(View.VISIBLE);
        MainActivity.toolbar_address.setVisibility(View.VISIBLE);
        userDAO = new UserDAO();
        orderDAO = new OrderDAO();
        couponDAO=new CouponDAO();
        lCoupon=new ArrayList<>();
        dbHelper = new DbHelper(getActivity());
        mViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        mViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<TypeProduct>>() {
            @Override
            public void onChanged(List<TypeProduct> typeProducts) {
                RecyclerView recyclerView = view.findViewById(R.id.rec_product_fragment);
                if (typeProducts!=null){
                    fragmentProductBinding.setIsLoading(false);
                    recTypeAdapter = new RecTypeAdapter(typeProducts, getContext(), getViewLifecycleOwner());
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
        mViewModel.getFavorite().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                favoriteProduct = products;
            }
        });
        mViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                bottomSheetBehaviorCartItem = BottomSheetBehavior.from(bottomsheetLayout);
                if (cartItems.size()!=0){
                    if (bottomSheetBehaviorCartItem.getState()!=BottomSheetBehavior.STATE_EXPANDED){
                        bottomSheetBehaviorCartItem.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                } else {
                    if (bottomSheetBehaviorCartItem.getState()==BottomSheetBehavior.STATE_EXPANDED){
                        bottomSheetBehaviorCartItem.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
        mViewModel.getIsOrder().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomsheetOder);
                if (aBoolean==true){
                    if (bottomSheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                    if (bottomSheetBehaviorCartItem.getState()==BottomSheetBehavior.STATE_EXPANDED){
                        bottomSheetBehaviorCartItem.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }
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
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                mViewModel.getFavorite().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
                    @Override
                    public void onChanged(List<Product> products) {
                        myFragmentFav = new FavoriteFragment(products);
                    }
                });
                activity.getSupportFragmentManager().beginTransaction().replace( R.id.frame_container, myFragmentFav ).addToBackStack( null ).commit();
                MainActivity.navigationView.setVisibility(View.GONE);
                MainActivity.toolbar_address.setVisibility(View.GONE);
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
        httpAdapter = new HttpAdapter();
        httpAdapter.setBaseUrl( HTTP_URL.Final_URL );
        oderService = httpAdapter.create(OderService.class);
        favoriteService = httpAdapter.create(FavoriteService.class);
        imgFavorite = view.findViewById(R.id.btn_favorite_productfragment);
        bottomsheetLayoutItem = view.findViewById(R.id.bottomSheet_detailproduct);
        bottomsheetLayout = view.findViewById(R.id.bottom_sheet_cart_layout);
        bottomsheetOder = view.findViewById(R.id.bottom_sheet_cart_order);
        tv_price = bottomsheetLayout.findViewById(R.id.priceCartBottomSheet);
        tv_quantityCart = view.findViewById(R.id.quantityCartItem);
        typeSelect  = view.findViewById(R.id.tv_pro_selected);
        bottomsheetOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new InforOderFragment();
                activity.getSupportFragmentManager().beginTransaction().replace( R.id.frame_container, myFragment ).addToBackStack( null ).commit();
                MainActivity.navigationView.setVisibility(View.GONE);
            }
        });
        bottomsheetLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    @Override
    public void EditItemClick(CartItem cartItem) {
        Product product = cartItem.getProduct();
        mViewModel.setIsEditing(true);
        mViewModel.setProduct(product);
        mViewModel.setCartItemMutable(cartItem);
        mViewModel.setQuantityItem(cartItem.quantity);
        mViewModel.setSize(cartItem.size);
        bottomSheetBinding.setProduct(mViewModel);
        RecProductAdapter.ViewHolder.ProductHandleClick productHandleClick = new RecProductAdapter.ViewHolder.ProductHandleClick(getContext());
        bottomSheetBinding.setHandleClick(productHandleClick);
        View view = bottomSheetBinding.getRoot();
        mViewModel.getSize().observe(getViewLifecycleOwner(), new Observer<Size>() {
            @Override
            public void onChanged(Size size) {
                int quantity = mViewModel.getQuantityItem().getValue();
                mViewModel.setPriceProduct((quantity * product.getProductPrice())+(size.getSizePrice()*quantity));
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
        imageButton_favorute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                if (integer==0){
                    btn_chonMon.setText("Xóa khỏi giỏ hàng");
                } else {
                    btn_chonMon.setText("Chọn món - "+s);
                }
            }
        });
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.minusQuantity(true);
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
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    //bottomsheet hiển thị chi tiết món
    public void oderSheet(){
        View view = fragmentCartBinding.getRoot();
        //phần coupon
        DbHelper dbHelper = new DbHelper(getContext());
        money=view.findViewById( R.id.money );
        ImageView vcoupon=view.findViewById( R.id.vcoupon );
        total=view.findViewById( R.id.total );
        address = view.findViewById(R.id.address_cart_order);
        streetName = view.findViewById(R.id.streetName);
        userName = view.findViewById(R.id.tv_userName_cartOrder);
        phone = view.findViewById(R.id.tv_phone_cartOrder);
        CartItemAdapter cartItemAdapter= new CartItemAdapter(mViewModel.getCart().getValue(), getContext(), mViewModel, ProductFragment.this::EditItemClick);
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

        storeViewModel = new ViewModelProvider(requireActivity()).get(StoreViewModel.class);
        storeViewModel.getStore().observe(getViewLifecycleOwner(), new Observer<Store>() {
            @Override
            public void onChanged(Store s) {
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
        userName.setText(dbHelper.getUser().getUserName());
        phone.setText("0"+String.valueOf(dbHelper.getUser().getUserPhone()));
        address.setText(infoLocation.getAddress());
        streetName.setText(locationViewModel.streetName.getValue());
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        productViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                String price = new DecimalFormat("##,###đ").format( productViewModel.getTotalPrice().getValue());
                totalcoupon=aDouble;
                fragmentCartBinding.orderTotalTextView.setText("TỔNG CỘNG : "+price);
                money.setText( String.valueOf(  price ));
                total.setText( String.valueOf(  price));
            }
        });
        productViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                if (cartItems.size()==0){
                    bottomSheetDialog.dismiss();
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                cartItem = cartItems;
            }
        });
        mViewModel.getDiscount().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                Log.d("TAG", "discount: "+aDouble);
                discount = aDouble;
                mViewModel.setFinalPrice(mViewModel.getTotalPrice().getValue()-discount);
            }
        });
        JSONArray jsonArray = new JSONArray();
        CartItemAdapter cartItemAdapters = new CartItemAdapter(cartItem, getContext(), mViewModel, ProductFragment.this::EditItemClick);
        recyclerView.setAdapter(cartItemAdapters);

        for (CartItem item: cartItem) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productID", item.product.ProductID);
                jsonObject.put("quantity", item.getQuantity());
                jsonObject.put("sizeID", item.size.SizeID);
                jsonObject.put("amount", item.amount);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
        }
        JSONObject oderObject = new JSONObject();
       oderID = UUID.randomUUID().toString().replace("-", "");
        Log.d("TAG", "FinalPrice: "+discount);
        try {
            ModelUser modelUser = userDAO.getUserNames(dbHelper.getUser().getUserPhone());
            oderObject.put("userID", modelUser.getUserID());
            oderObject.put("orderID", oderID);
            oderObject.put("storeID", store.StoreID);
            oderObject.put("address", infoLocation.getAddress());
            oderObject.put("lat", infoLocation.getLocation().getLatitude());
            oderObject.put("lng", infoLocation.getLocation().getLongitude());
            mViewModel.getFinalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
                @Override
                public void onChanged(Double aDouble) {
                    try {
                        oderObject.put("totalMoney", aDouble);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            oderObject.put("detailOrder", jsonArray);
        } catch (Exception e){
            Log.d("TAG", "onChanged: + 1 "+e);
        }
        placeOrderButton.setEnabled(cartItem.size() > 0);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oderService.insertOder(oderObject.toString())){
                    Log.d( "TAG", "onClick: "+oderObject.toString() );
                    Toast.makeText(getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                    mViewModel.setIsOrder(true);
                    MainActivity.navigationView.setVisibility(View.GONE);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    bottomSheetDialog.dismiss();
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new InforOderFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace( R.id.frame_container, myFragment ).addToBackStack( null ).commit();
                }
            }
        });
        vcoupon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog= new AlertDialog.Builder(getActivity()).create();
                final View view = View.inflate(getActivity(), R.layout.fragment_coupon_, null);
                lvcoupon=view.findViewById( R.id.lvcoupon );
                back=view.findViewById( R.id.back );
                couponDAO=new CouponDAO();
                listcoupon=new ArrayList<>();
                list=new ArrayList<>();
                listcoupon=couponDAO.listcoupon();
                for (int i=0; i<listcoupon.size();i++){
                    if (totalcoupon >= listcoupon.get( i ).getCouponCondition()){
                        list.add( listcoupon.get( i ) );
                        Log.d( "TAG", "onCreateView11: "+list.size() );
                    }
                }
                if( list.size()!=0){
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    lvcoupon.setLayoutManager(layoutManager);
                    adapter = new Coupon_Adapter(list,getContext());
                    lvcoupon.setAdapter(adapter);
                }
                if(list.size()==0){
                    final  android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(view.getContext());
                    builder.setMessage("Xin Lỗi.Bạn không đủ điều kiện để giảm giá").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog1, int which) {
                            dialog1.cancel();
                            dialog.dismiss();
                        }
                    });
                    final android.app.AlertDialog alertDialog =builder.create();
                    alertDialog.show();
                    ;
                }

                back.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                } );

                dialog.setView(view);
                dialog.show();
            }
        } );

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}