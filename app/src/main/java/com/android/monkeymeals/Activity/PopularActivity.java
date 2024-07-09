package com.android.monkeymeals.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.monkeymeals.R;
import com.android.monkeymeals.adapters.PopularAdapter;
import com.android.monkeymeals.model.PopularModel;

import java.util.ArrayList;

public class PopularActivity extends AppCompatActivity {

    Button restaurantButton ;

    ArrayList<PopularModel> popularModels = new ArrayList<>();
    int[] FoodImages = {R.drawable.mcburger, R.drawable.pizza,R.drawable.hotdog,R.drawable.chickenbucket,R.drawable.pop_2,
    R.drawable.crispychickenburger,R.drawable.chickentaco,R.drawable.beefdevilpizza};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Popular Items From Our Restaurants");

        restaurantButton = findViewById(R.id.CheckoutRestaurant);

        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopularActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        RecyclerView recyclerView = findViewById(R.id.popularRecyclerView);

        setUpRestaurantModels();

        PopularAdapter adapter = new PopularAdapter(this,popularModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    private void setUpRestaurantModels() {
        String[] RestaurantName = getResources().getStringArray(R.array.RestaurantName);
        String[] FoodName = getResources().getStringArray(R.array.FoodName);
        String[] Price = getResources().getStringArray(R.array.Price);

        for (int i = 0 ; i < RestaurantName.length; i++) {
            popularModels.add(new PopularModel(RestaurantName[i],FoodName[i],Price[i],FoodImages[i]));

        }

    }

}