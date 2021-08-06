package com.example.fooddelivery_lt152011.HomeScreen;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fooddelivery_lt152011.MyOrder.ProductOrderIF_Adapter;
import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.productScreen.OneItemClick;
import com.example.fooddelivery_lt152011.productScreen.Product;
import com.example.fooddelivery_lt152011.productScreen.ProductFragment;
import com.example.fooddelivery_lt152011.productScreen.RecProductAdapter;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    ArrayList<ModelCoupon> list;
    CouponDAO couponDAO;
    CarouselView carouselView;
    RecyclerView lvSelling;
    RecProductAdapter adapter;
    List<Product> productList;
    CountProductDAO countProductDAO;
    ProductViewModel mViewmodel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        list=new ArrayList<>();
        mViewmodel = new ViewModelProvider( requireActivity()).get(ProductViewModel.class);
        couponDAO=new CouponDAO();
        list=couponDAO.listcoupon();
        countProductDAO=new CountProductDAO();
        lvSelling=view.findViewById( R.id.lvSelling );
        carouselView=view.findViewById( R.id.carouselView );
        carouselView.setPageCount(list.size());
        carouselView.setImageListener( new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                Picasso.get().load( list.get( position).getCouponImagge() ).into( imageView );
            }
        } );
        productList=countProductDAO.systemService.countProduct().getCounts();
        Log.d( "TAG", "Log home: "+productList.get( 0 ).getSizes() );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        lvSelling.setLayoutManager(layoutManager);
        adapter = new RecProductAdapter( getContext(),productList,getViewLifecycleOwner());
        lvSelling.setAdapter(adapter);
        return view;
    }

}