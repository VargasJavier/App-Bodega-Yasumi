package com.example.bodegayasumi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bodegayasumi.R;
import com.example.bodegayasumi.dto.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final List<Product> productList;
    final ProductAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Product product);
    }

    public ProductAdapter(List<Product> productList, ProductAdapter.OnItemClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.tvName.setText(productList.get(position).getNombre());
//        holder.tvDescription.setText(productList.get(position).getDescripcion());
//        holder.tvPrice.setText("s/ " + String.format("%.2f",productList.get(position).getPrecio()));
//        holder.tvMarca.setText(productList.get(position).getMarca());
        holder.bindData(productList.get(position));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvDescription, tvPrice, tvMarca;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvMarca = itemView.findViewById(R.id.tvMarca);
        }

        void bindData(final Product product){
            tvName.setText(product.getName());
            tvDescription.setText(product.getDescription());
            tvPrice.setText("s/ " + String.format("%.2f",product.getPrice()));
            tvMarca.setText(product.getBrand());
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    listener.onItemClick(product);
                }
            });
        }
    }
}
