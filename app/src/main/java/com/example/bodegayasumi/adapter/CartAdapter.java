package com.example.bodegayasumi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bodegayasumi.R;
import com.example.bodegayasumi.dto.CartItem;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolderCart> {
    ArrayList<CartItem> cartArrayList;

    public CartAdapter(ArrayList<CartItem> cartArrayList) {
        this.cartArrayList = cartArrayList;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_cart, parent, false);
        return new ViewHolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCart holder, int position) {
        holder.tvName.setText(cartArrayList.get(position).getName());
        holder.tvPrice.setText((int) cartArrayList.get(position).getPrice());
        holder.tvQuantity.setText(cartArrayList.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    public class ViewHolderCart extends RecyclerView.ViewHolder {

        TextView tvName, tvPrice, tvQuantity;

        public ViewHolderCart(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textViewName);
            tvPrice = itemView.findViewById(R.id.textViewPrice);
            tvQuantity = itemView.findViewById(R.id.textViewQuantity);
        }
    }
}
