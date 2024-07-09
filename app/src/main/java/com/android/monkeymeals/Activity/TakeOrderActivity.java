package com.android.monkeymeals.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.monkeymeals.R;
import com.android.monkeymeals.adapters.PlaceYourOrderAdapter;
import com.android.monkeymeals.model.Menu;
import com.android.monkeymeals.model.RestaurantModel;

public class TakeOrderActivity extends AppCompatActivity {


    private RecyclerView cartItemsRecyclerView;
    private TextView Subtotal, DeliveryChargeAmount, tvDeliveryCharge, totalAmount, PlaceOrderButton;


    private PlaceYourOrderAdapter placeYourOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_order);

        RestaurantModel restaurantModel = getIntent().getParcelableExtra("RestaurantModel");
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Checkout");
        actionBar.setDisplayHomeAsUpEnabled(true);


        Subtotal = findViewById(R.id.tvSubtotalAmount);
        DeliveryChargeAmount = findViewById(R.id.tvDeliveryChargeAmount);
        tvDeliveryCharge = findViewById(R.id.tvDeliveryCharge);
        totalAmount = findViewById(R.id.tvTotalAmount);
        PlaceOrderButton = findViewById(R.id.buttonPlaceYourOrder);

        cartItemsRecyclerView = findViewById(R.id.cartItemsRecyclerView);

        PlaceOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeOrderButton(restaurantModel);
            }
        });

        cartItemsRecyclerView(restaurantModel);
        calculateTotalAmount(restaurantModel);


    }

    private void calculateTotalAmount(RestaurantModel restaurantModel) {
        float subTotalAmount = 0f;

        for(Menu m : restaurantModel.getMenus()) {
            subTotalAmount += m.getPrice() * m.getTotalInCart();
        }

        Subtotal.setText("Rs"+String.format("%.2f", subTotalAmount));

            DeliveryChargeAmount.setText("Rs"+String.format("%.2f", restaurantModel.getDelivery_charge()));
            subTotalAmount += restaurantModel.getDelivery_charge();

        totalAmount.setText("Rs"+String.format("%.2f", subTotalAmount));
    }


    //Method to Load Register Activity after Placing Order
    private void takeOrderButton(RestaurantModel restaurantModel) {


        Intent intent = new Intent(TakeOrderActivity.this, RegisterActivity.class);

        startActivity(intent);
        finish();
    }

    private void cartItemsRecyclerView(RestaurantModel restaurantModel) {
        cartItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        placeYourOrderAdapter = new PlaceYourOrderAdapter(restaurantModel.getMenus());
        cartItemsRecyclerView.setAdapter(placeYourOrderAdapter);
    }

    @Override
    protected void onActivityResult(int param1, int param2, @Nullable Intent data) {

        if(param1 == 1000) {
            setResult(Activity.RESULT_OK);
            finish();
        }
        super.onActivityResult(param1, param2, data);
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
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }


}