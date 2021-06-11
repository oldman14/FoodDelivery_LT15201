package com.example.fooddelivery_lt152011.ProductScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fooddelivery_lt152011.R;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {
    public Spinner spinner;
    public TypeProAdapter typeProAdapter;
    public RecTypeAdapter recTypeAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        typeProAdapter = new TypeProAdapter(getContext(),R.layout.item_typeproduct, getTypeProduct());
        spinner = view.findViewById(R.id.spin_product);
        spinner.setAdapter(typeProAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), typeProAdapter.getItem(position).getTypeName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.rec_product_fragment);
        recTypeAdapter = new RecTypeAdapter(getAll(), getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(recTypeAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }
    public List<TypeProduct> getAll(){
        List<TypeProduct> listType = getTypeProduct();
        List<Product> productList = getProduct();
        List<TypeProduct> productType = new ArrayList<>();

        for (int i = 0; i < listType.size(); i++) {
            List<Product> productList1 = new ArrayList<>();
            for (int j = 0; j < productList.size(); j++) {
                if (listType.get(i).TypeID==productList.get(j).TypeID){
                    productList1.add(productList.get(j));
                }
            }
            if (productList1.size()>0){
                productType.add(new TypeProduct(listType.get(i).getTypeID() ,listType.get(i).getTypeName(), productList1));
            }
        }
        return productType;
    }
    private List<TypeProduct> getTypeProduct() {
        List<TypeProduct> productType = new ArrayList<>();
        productType.add(new TypeProduct(0, "Cà phê"));
        productType.add(new TypeProduct(1, "Đá xoay vòng tròn"));
        productType.add(new TypeProduct(2, "Nước ép hoa đậu biếc"));
        productType.add(new TypeProduct(3, "Bánh xu cà na"));
        return productType;
    }
    private List<Product> getProduct(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(0, "Cà phê đen", 20000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè ",0));
        productList.add(new Product(1, "Cà phê sữa đá", 25000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè ",0));
        productList.add(new Product(2, "Bạc xỉu", 20000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè ",0));
        productList.add(new Product(3, "Đá xoay socola", 35000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè ",1));
        productList.add(new Product(4, "Đá xoay cà phê", 30000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè",1));
        productList.add(new Product(5, "Đá xoay ca cao", 50000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè",1));
        productList.add(new Product(4, "Nước ép cà phê", 30000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt các kiểu các kiểu nè",2));
        productList.add(new Product(5, "Nước ép ca cao", 50000,"https://product.hstatic.net/1000075078/product/cafe-den-da_18234c186f2f44f0a2d7ec1ce0e58158_master.jpg", "Một tách cà phê thơm ngào ngạt",2));
        return productList;
    }
}