package com.android.monkeymeals.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.monkeymeals.R;
import com.android.monkeymeals.model.PopularModel;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MyViewHolder> {

    Context context;
    ArrayList<PopularModel> popularModels;
    public PopularAdapter(Context context, ArrayList<PopularModel> popularModels) {
        this.context = context;
        this.popularModels = popularModels;

    }

    @NonNull
    @Override
    public PopularAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.popular_recycler_row,parent,false);
        return new PopularAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.MyViewHolder holder, int position) {
        holder.tvRestaurantName.setText(popularModels.get(position).getRestaurantName());
        holder.tvFoodName.setText(popularModels.get(position).getFoodName());
        holder.tvPrice.setText(popularModels.get(position).getPrice());
        holder.imageView.setImageResource(popularModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return popularModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvRestaurantName,tvFoodName,tvPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.MealImage);
            tvRestaurantName = itemView.findViewById(R.id.RestaurantName);
            tvFoodName = itemView.findViewById(R.id.MealName);
            tvPrice = itemView.findViewById(R.id.MealPrice);

        }
    }
}
