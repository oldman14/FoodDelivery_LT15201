package com.example.fooddelivery_lt152011.productScreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fooddelivery_lt152011.HTTP_URL;
import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.databinding.BottomsheetCartItemBinding;
import com.example.fooddelivery_lt152011.databinding.FragmentCartBinding;
import com.example.fooddelivery_lt152011.networking.Http.HttpAdapter;
import com.example.fooddelivery_lt152011.networking.Service.OderService;
import com.example.fooddelivery_lt152011.productScreen.entities.InfoLocation;
import com.example.fooddelivery_lt152011.productScreen.entities.Store;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.LocationViewModel;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.StoreViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CartFragment extends Fragment implements CartListAdapter.CartInterface {

    private static final String TAG = "CartFragment";
    ProductViewModel productViewModel;
    StoreViewModel storeViewModel;
    LocationViewModel locationViewModel;
    FragmentCartBinding fragmentCartBinding;
    BottomsheetCartItemBinding bottomsheetCartItemBinding;
    HttpAdapter httpAdapter;
    OderService oderService;
    Store store;
    InfoLocation infoLocation;
    RadioButton radioButton;
    RecyclerView recyclerView;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCartBinding = FragmentCartBinding.inflate(inflater, container, false);
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        httpAdapter = new HttpAdapter();
        httpAdapter.setBaseUrl( HTTP_URL.Final_URL );
        oderService = httpAdapter.create(OderService.class);
        final CartListAdapter cartListAdapter = new CartListAdapter(this);
        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        RelativeLayout bottomsheetLayout = view.findViewById(R.id.bottom_sheet_cart_layout);
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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(jsonObject);
                }
                JSONObject oderObject = new JSONObject();
                UUID oderID = UUID.randomUUID();
                try {
                    oderObject.put("OrderID", oderID);
                    oderObject.put("StoreID", store.StoreID);
                    oderObject.put("Address", infoLocation.getAddress());
                    oderObject.put("OrderLat", infoLocation.getLocation().getLatitude());
                    oderObject.put("OrderLong", infoLocation.getLocation().getLongitude());
                    oderObject.put("TotalMoney", productViewModel.getTotalPrice().getValue());
                    oderObject.put("detailOrder", jsonArray);
                } catch (Exception e){
                    Log.d(TAG, "onChanged: "+e);
                }
                cartListAdapter.submitList(cartItems);
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("orderfood",oderObject.toString());
                cartListAdapter.submitList(cartItems);
                fragmentCartBinding.placeOrderButton.setEnabled(cartItems.size() > 0);
                fragmentCartBinding.placeOrderButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onClick: "+oderObject.toString());
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

        fragmentCartBinding.placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void deleteItem(CartItem cartItem) {
        productViewModel.removeItemFromCart(cartItem);
    }

    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {
        productViewModel.changeQuantity(cartItem, quantity);
    }
}