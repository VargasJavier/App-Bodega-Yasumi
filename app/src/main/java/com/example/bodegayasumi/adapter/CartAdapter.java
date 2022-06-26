package com.example.bodegayasumi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bodegayasumi.MainActivity;
import com.example.bodegayasumi.R;
import com.example.bodegayasumi.dto.CartItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolderCart> {
    ArrayList<CartItem> cartArrayList;

    public CartAdapter(ArrayList<CartItem> cartArrayList) {
        this.cartArrayList = cartArrayList;
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_cart, parent, false);
        return new CartAdapter.ViewHolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolderCart holder, int position) {
        holder.bindData(cartArrayList.get(position));
    }



    public class ViewHolderCart extends RecyclerView.ViewHolder {

        TextView tvName, tvPrice, tvQuantity;
        ImageView ivProductImage;

        public ViewHolderCart(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textViewName);
            tvPrice = itemView.findViewById(R.id.textViewPrice);
            tvQuantity = itemView.findViewById(R.id.textViewQuantity);
            ivProductImage = itemView.findViewById(R.id.imageView7);
        }

        void bindData(CartItem item){
            tvName.setText(item.getName());
            tvPrice.setText("S/. " + String.format("%.2f",item.getPrice()));
            tvQuantity.setText("Cantidad: " + String.valueOf(item.getQuantity()));
            Picasso.get().load(MainActivity.URL_API + item.getImageName()).into(ivProductImage);
        }
    }
}
