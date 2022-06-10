package com.example.bodegayasumi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolderCart> {
    ArrayList<ProductCart> cartArrayList;

    public CartAdapter(ArrayList<ProductCart> cartArrayList) {
        this.cartArrayList = cartArrayList;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cart, null, false);
        return new ViewHolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolderCart holder, int position) {
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
