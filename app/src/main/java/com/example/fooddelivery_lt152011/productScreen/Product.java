package com.example.fooddelivery_lt152011.productScreen;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.example.fooddelivery_lt152011.R;
import com.squareup.picasso.Picasso;

import static android.media.audiofx.AcousticEchoCanceler.isAvailable;
import static com.example.fooddelivery_lt152011.BR.product;

public class Product {
    public int ProductID;
    public String ProductName;
    public int ProductPrice;
    public String ProductImage;
    public String ProductNote;
    public int TypeID;

    public Product(int productID, String productName, int productPrice, String productImage, String productNote, int typeID) {
        ProductID = productID;
        ProductName = productName;
        ProductPrice = productPrice;
        ProductImage = productImage;
        ProductNote = productNote;
        TypeID = typeID;
    }
    @BindingAdapter({"productImage"})
    public static void loadImage(ImageView imageView, String productImage){
        Picasso.get().load(productImage).into(imageView);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.getProductPrice(), getProductPrice()) == 0 &&
                getProductID()==(product.getProductID()) &&
                getProductImage().equals(product.getProductImage()) &&
                getProductImage().equals(product.getProductImage());
    }

    public static DiffUtil.ItemCallback<Product> itemCallback = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getProductID()==(newItem.getProductID());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    };
    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductNote() {
        return ProductNote;
    }

    public void setProductNote(String productNote) {
        ProductNote = productNote;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }
}
