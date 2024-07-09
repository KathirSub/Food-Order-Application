package com.android.monkeymeals.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.monkeymeals.R;
import com.android.monkeymeals.model.Menu;
import com.bumptech.glide.Glide;

import java.util.List;

public class PlaceYourOrderAdapter extends RecyclerView.Adapter<PlaceYourOrderAdapter.MyViewHolder> {

    private List<Menu> menuList;

    public PlaceYourOrderAdapter(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public void updateData(List<Menu> menuList) {
        this.menuList = menuList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlaceYourOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_order_recycler_row, parent, false);
        return  new MyViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return menuList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  menuName;
        TextView  menuPrice;
        TextView  menuQty;
        ImageView restaurantImage;

        public MyViewHolder(View view) {
            super(view);
            menuName = view.findViewById(R.id.menuFoodName);
            menuPrice = view.findViewById(R.id.menuFoodPrice);
            menuQty = view.findViewById(R.id.menuFoodQty);
            restaurantImage = view.findViewById(R.id.restaurantImage);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull PlaceYourOrderAdapter.MyViewHolder holder, int position) {
        holder.menuName.setText(menuList.get(position).getName());
        holder.menuPrice.setText("Price: Rs."+String.format("%.2f", menuList.get(position).getPrice()*menuList.get(position).getTotalInCart()));
        holder.menuQty.setText("Qty: " + menuList.get(position).getTotalInCart());
        Glide.with(holder.restaurantImage)
                .load(menuList.get(position).getUrl())
                .into(holder.restaurantImage);

    }
}
