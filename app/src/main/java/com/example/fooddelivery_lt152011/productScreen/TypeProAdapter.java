package com.example.fooddelivery_lt152011.productScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fooddelivery_lt152011.R;

import java.util.List;

public class TypeProAdapter extends ArrayAdapter<TypeProduct> {
    public TypeProAdapter(@NonNull Context context, int resource, @NonNull List<TypeProduct> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pro_selected, parent, false);
        TextView tv_typeproduct = (TextView) convertView.findViewById(R.id.tv_pro_selected);
        TypeProduct typeProduct = this.getItem(position);
        if (typeProduct!=null) {
            tv_typeproduct.setText(typeProduct.getTypeName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_typeproduct, parent, false);
        TextView tv_typeproduct = (TextView) convertView.findViewById(R.id.tv_typeproduct);
        TypeProduct typeProduct = this.getItem(position);
        if (typeProduct!=null) {
            tv_typeproduct.setText(typeProduct.getTypeName());
        }
        return convertView;
    }


}
