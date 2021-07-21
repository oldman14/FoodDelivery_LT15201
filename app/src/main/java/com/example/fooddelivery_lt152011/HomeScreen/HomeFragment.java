package com.example.fooddelivery_lt152011.HomeScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    ArrayList<ModelCoupon> list;
    CouponDAO couponDAO;
    CarouselView carouselView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        list=new ArrayList<>();
        couponDAO=new CouponDAO();
        list=couponDAO.listcoupon( "Đang Chạy" );
        carouselView=view.findViewById( R.id.carouselView );
        carouselView.setPageCount(list.size());
        carouselView.setImageListener( new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                Picasso.get().load( list.get( position).getCouponImagge() ).into( imageView );
            }
        } );
        return view;
    }
}