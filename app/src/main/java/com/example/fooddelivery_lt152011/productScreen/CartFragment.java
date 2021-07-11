package com.example.fooddelivery_lt152011.productScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.DividerItemDecoration;


import com.example.fooddelivery_lt152011.R;
import com.example.fooddelivery_lt152011.databinding.BottomsheetCartItemBinding;
import com.example.fooddelivery_lt152011.databinding.FragmentCartBinding;
import com.example.fooddelivery_lt152011.productScreen.viewmodel.ProductViewModel;

import java.text.DecimalFormat;
import java.util.List;

public class CartFragment extends Fragment implements CartListAdapter.CartInterface {

    private static final String TAG = "CartFragment";
    ProductViewModel productViewModel;
    FragmentCartBinding fragmentCartBinding;
    BottomsheetCartItemBinding bottomsheetCartItemBinding;
//    NavController navController;

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
//        navController = Navigation.findNavController(view);
        final CartListAdapter cartListAdapter = new CartListAdapter(this);
        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        RelativeLayout bottomsheetLayout = view.findViewById(R.id.bottom_sheet_cart_layout);
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        productViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartListAdapter.submitList(cartItems);
                fragmentCartBinding.placeOrderButton.setEnabled(cartItems.size() > 0);
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