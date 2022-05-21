package com.example.bodegayasumi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private final List<Product> productList;
    final ProductsAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Product product);
    }

    public ProductsAdapter(List<Product> productList, ProductsAdapter.OnItemClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            tvName.setText(product.getNombre());
            tvDescription.setText(product.getDescripcion());
            tvPrice.setText("s/ " + String.format("%.2f",product.getPrecio()));
            tvMarca.setText(product.getMarca());
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    listener.onItemClick(product);
                }
            });
        }
    }
}
