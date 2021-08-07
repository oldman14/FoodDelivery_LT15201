package com.example.fooddelivery_lt152011.productScreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devs.readmoreoption.ReadMoreOption;
import com.example.fooddelivery_lt152011.HTTP_URL;
import com.example.fooddelivery_lt152011.MainActivity;
import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.databinding.BottomSheetBinding;
import com.example.fooddelivery_lt152011.databinding.FragmentProductBinding;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.FavoriteService;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class RecProductAdapter extends RecyclerView.Adapter<RecProductAdapter.ViewHolder>{

    public static Context context;
    public List<Product> productList;
    static HttpAdapter httpAdapter;
    public FragmentProductBinding fragmentProductBinding;
    static LifecycleOwner lifecycleOwner;
    public RecProductAdapter(Context context, List<Product> productList, LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.productList = productList;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rec_product, parent, false);
//        fragmentProductBinding = DataBindingUtil.inflate(
//                LayoutInflater.from(context), R.layout.fragment_product, parent, false);
//        View view = fragmentProductBinding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tv_proName.setText(product.getProductName());
        holder.tv_proNote.setText(product.getProductNote());
        holder.tv_proPrice.setText(new DecimalFormat("##,###đ").format(product.getProductPrice()));
        Picasso.get().load(product.ProductImage).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.oneItemClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_proName, tv_proNote, tv_proPrice;
        ImageView imageView;
        public static ProductViewModel mViewModel;
        public RadBtnAdapter radBtnAdapter;
        public RecyclerView recyclerViewRad;
        public ImageButton btn_minus, btn_plus;
        public TextView tv_price, tv_quantityCart, tv_quantityItem;
        public static BottomSheetDialog bottomSheetDialog;
        public BottomSheetBehavior bottomSheetBehavior;
        public ReadMoreOption readMoreOption;
        static FavoriteService favoriteService;
        public BottomSheetBinding bottomSheetBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ProductViewModel.class);
            httpAdapter = new HttpAdapter();
            httpAdapter.setBaseUrl( HTTP_URL.Final_URL );
            favoriteService = httpAdapter.create(FavoriteService.class);
            bottomSheetBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.bottom_sheet, (ViewGroup) itemView.getParent(), false);
            tv_proName = itemView.findViewById(R.id.tv_proName);
            tv_proNote =  itemView.findViewById(R.id.tv_proNote);
            tv_proPrice =  itemView.findViewById(R.id.tv_proPice);
            imageView = itemView.findViewById(R.id.img_product);
            readMoreOption = new ReadMoreOption.Builder(context)
                    .textLength(3, ReadMoreOption.TYPE_LINE) // OR
                    //.textLength(300, ReadMoreOption.TYPE_CHARACTER)
                    .moreLabel("Xem thêm")
                    .lessLabel("Rút gọn")
                    .moreLabelColor(R.color.placeholder_color)
                    .lessLabelColor(R.color.orange_light)
                    .labelUnderLine(true)
                    .expandAnimation(true)
                    .build();
        }

        public void oneItemClick(Product product) {
            mViewModel.setIsEditing(false);
            mViewModel.setProduct(product);
            bottomSheetBinding.setProduct(mViewModel);
            ProductHandleClick productHandleClick = new ProductHandleClick(context);
            bottomSheetBinding.setHandleClick(productHandleClick);
            View view = bottomSheetBinding.getRoot();
            ImageButton imageButton_favorute = view.findViewById(R.id.imgBtn_favourite);
            mViewModel.setSize(product.getSizes().get(0));
            mViewModel.setFavourite(false);
            mViewModel.getFavorite().observe(lifecycleOwner, new Observer<List<Product>>() {
                @Override
                public void onChanged(List<Product> products) {
                    for (int i = 0; i < products.size(); i++) {
                        if (product.getProductID() == products.get(i).getProductID()){
                            mViewModel.setFavourite(true);
                        }
                    }
                }
            });
            mViewModel.getSize().observe(lifecycleOwner, new Observer<Size>() {
                @Override
                public void onChanged(Size size) {
                    int quantity = mViewModel.getQuantityItem().getValue();
                    mViewModel.setPriceProduct((quantity * product.getProductPrice())+(size.getSizePrice()*quantity));
                }
            });

            radBtnAdapter = new RadBtnAdapter(product.getSizes(), context, mViewModel);
            recyclerViewRad = view.findViewById(R.id.recRadioButton);
            recyclerViewRad.setAdapter(radBtnAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerViewRad.setLayoutManager(layoutManager);
            btn_minus = view.findViewById(R.id.btn_minus_quantity);
            btn_plus = view.findViewById(R.id.btn_plus_quantity);
            tv_quantityItem = view.findViewById(R.id.tv_quantity_detail_product);
            TextView detail_product = view.findViewById(R.id.detail_product);
            readMoreOption.addReadMoreTo(detail_product, product.ProductNote);
            mViewModel.setQuantityItem(1);
            mViewModel.getFavoite().observe(lifecycleOwner, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (aBoolean==true){
                        imageButton_favorute.setImageResource(R.drawable.ic_favorite_24);
                    } else {
                        imageButton_favorute.setImageResource(R.drawable.ic_favorite_border_24);
                    }
                }
            });
            mViewModel.getQuantityItem().observe(lifecycleOwner, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    Log.d("TAG", "onChanged: "+integer);
                    tv_quantityItem.setText(integer.toString());
                    mViewModel.setPriceProduct((integer * product.getProductPrice())+mViewModel.getSize().getValue().getSizePrice()*integer);
                }
            });
            Button btn_chonMon = view.findViewById(R.id.btn_chonMon);
            mViewModel.getPriceProduct().observe(lifecycleOwner, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    String s = new DecimalFormat("##,###đ").format(integer);
                    btn_chonMon.setText("Chọn món - "+s);
                }
            });
            btn_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewModel.minusQuantity(false);
                }
            });
            btn_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewModel.plusQuantity();
                }
            });
            bottomSheetDialog = new BottomSheetDialog(context);
            if (view.getParent()!=null){
                ((ViewGroup)view.getParent()).removeView(view);
            }
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();
            bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        public static class ProductHandleClick {
            Context context;
            public ProductHandleClick(Context context) {
                this.context = context;
            }
            public void addItemProduct(Product product,  int quantity, Size size, int amount ){
                Log.d("TAG", "addItemProduct: "+context);
                if(mViewModel.getIsEditing().getValue()!=true){
                    if (quantity!=0){
                        mViewModel.addItemToCart(product, quantity, size, amount);
                        bottomSheetDialog.dismiss();
                    }
                } else {
                    if (quantity!=0){
                        mViewModel.changeQuantity(mViewModel.getCartItemMutable().getValue(),quantity);
                        bottomSheetDialog.dismiss();
                    } else {
                        mViewModel.removeItemFromCart(mViewModel.getCartItemMutable().getValue());
                        bottomSheetDialog.dismiss();
                    }
                }
            }
            public void changeFavorite(Product product){

                if (mViewModel.favourite.getValue()==true){
                    mViewModel.setFavourite(false);
                    if (favoriteService.deleteFav(MainActivity.UserID, product.ProductID)){
                        mViewModel.getFavorite().getValue();
                    } else {
                        Log.d("TAG", "Delete favorite thất bại");
                    }
                } else {
                    mViewModel.setFavourite(true);
                    if (favoriteService.insertFav(MainActivity.UserID, product.ProductID)==true){
                        mViewModel.getFavorite().getValue();
                    } else {
                        Log.d("TAG", "Insert favorite thất bại");
                    }
                }
            }
            public String convertString(int price){
                return new DecimalFormat("##,###đ").format(price);
            }
        }
    }
}
