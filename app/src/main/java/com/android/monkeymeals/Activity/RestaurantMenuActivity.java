package com.android.monkeymeals.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.monkeymeals.R;
import com.android.monkeymeals.adapters.MenuListAdapter;
import com.android.monkeymeals.model.Menu;
import com.android.monkeymeals.model.RestaurantModel;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMenuActivity extends AppCompatActivity implements MenuListAdapter.MenuListClickListener {
    private List<Menu> menuList = null;
    private MenuListAdapter menuAdapter;
    private List<Menu> itemsInCartList;
    private int FoodItemsInCart = 0;
    private TextView CheckoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        RestaurantModel restaurantModel = getIntent().getParcelableExtra("RestaurantModel");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurantModel.getName());
        actionBar.setSubtitle(restaurantModel.getAddress());
        actionBar.setDisplayHomeAsUpEnabled(true);


        menuList = restaurantModel.getMenus();
        MenuRecyclerView();


         CheckoutButton = findViewById(R.id.buttonCheckout);
        CheckoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemsInCartList != null && itemsInCartList.size() <= 0) {
                    Toast.makeText(RestaurantMenuActivity.this, "No items in cart.", Toast.LENGTH_SHORT).show();
                    return;
                }
                restaurantModel.setMenus(itemsInCartList);
                Intent i = new Intent(RestaurantMenuActivity.this, TakeOrderActivity.class);
                i.putExtra("RestaurantModel", restaurantModel);
                startActivityForResult(i, 1000);
            }
        });
    }

    private void MenuRecyclerView() {
        RecyclerView recyclerView =  findViewById(R.id.popularRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        menuAdapter = new MenuListAdapter(menuList, this);
        recyclerView.setAdapter(menuAdapter);
    }

    @Override
    public void onAddToCartClick(Menu menu) {
        if(itemsInCartList == null) {
            itemsInCartList = new ArrayList<>();
        }
        itemsInCartList.add(menu);
        FoodItemsInCart = 0;

        for(Menu m : itemsInCartList) {
            FoodItemsInCart = FoodItemsInCart + m.getTotalInCart();
        }
        CheckoutButton.setText("Checkout (" + FoodItemsInCart +") items");
    }



    @Override
    public void onRemoveFromCartClick(Menu menu) {
        if(itemsInCartList.contains(menu)) {
            itemsInCartList.remove(menu);
            FoodItemsInCart = 0;

            for(Menu m : itemsInCartList) {
                FoodItemsInCart = FoodItemsInCart + m.getTotalInCart();
            }
            CheckoutButton.setText("Checkout (" + FoodItemsInCart +") items");
        }
    }
    @Override
    public void onUpdateCartClick(Menu menu) {
        if(itemsInCartList.contains(menu)) {
            int index = itemsInCartList.indexOf(menu);
            itemsInCartList.remove(index);
            itemsInCartList.add(index, menu);

            FoodItemsInCart = 0;

            for(Menu m : itemsInCartList) {
                FoodItemsInCart = FoodItemsInCart + m.getTotalInCart();
            }
            CheckoutButton.setText("Checkout (" + FoodItemsInCart +") items");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
            default:

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int param1, int param2, @Nullable Intent data) {
        super.onActivityResult(param1, param2, data);

        if(param1 == 1000 && param2 == Activity.RESULT_OK) {

            finish();
        }
    }
}