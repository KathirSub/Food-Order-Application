package com.android.monkeymeals.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.monkeymeals.DataBase.RestaurantDataBaseHelper;
import com.android.monkeymeals.R;

public class FrontPageActivity extends AppCompatActivity {

    RestaurantDataBaseHelper RDB;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage);

        getSupportActionBar().setTitle("Monkey Meals");
        nextButton = findViewById(R.id.next_button);


        RDB = new RestaurantDataBaseHelper(this);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*ADDS THE RESTAURANT INFORMATION INTO A SQL DATABASE*/
                Boolean checkRestaurant = RDB.checkRestaurant("PizzaHut");
                if (checkRestaurant == false) {
                    RDB.insertData("PizzaHut", "Wattala,Gampaha", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Pizza_Hut_1967-1999_logo.svg/1200px-Pizza_Hut_1967-1999_logo.svg.png");
                }
                checkRestaurant = RDB.checkRestaurant("Subway");
                if (checkRestaurant == false) {
                    RDB.insertData("Subway","Navamawatha,Colombo","https://www.subway.co.id/wp-content/uploads/2021/09/Thumbnail-Homepage.jpg");
                }
                checkRestaurant = RDB.checkRestaurant("McDonalds");
                if (checkRestaurant == false) {
                    RDB.insertData("McDonalds","Ratmalana, Gampaha","https://miro.medium.com/max/1200/1*SwiuCuVgv8O0wNYYfr3AAA.jpeg");
                }
                checkRestaurant = RDB.checkRestaurant("Burger King");
                if (checkRestaurant == false) {
                    RDB.insertData("Burger King","Wellawatte, Colombo","https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Burger_King_logo_%281999%29.svg/200px-Burger_King_logo_%281999%29.svg.png");
                }
                checkRestaurant = RDB.checkRestaurant("KFC");
                if (checkRestaurant == false) {
                    RDB.insertData("KFC","Pettah, Colombo","https://upload.wikimedia.org/wikipedia/en/thumb/b/bf/KFC_logo.svg/1200px-KFC_logo.svg.png");
                }
                checkRestaurant = RDB.checkRestaurant("TacoBell");
                if (checkRestaurant == false) {
                    RDB.insertData("TacoBell","Negombo, Gampaha","https://upload.wikimedia.org/wikipedia/en/thumb/b/b3/Taco_Bell_2016.svg/1200px-Taco_Bell_2016.svg.png");
                }
                checkRestaurant = RDB.checkRestaurant("PastaMania");
                if (checkRestaurant == false) {
                    RDB.insertData("PastaMania","Kotahena, Colombo","https://res.cloudinary.com/crunchbase-production/image/upload/c_lpad,h_170,w_170,f_auto,b_white,q_auto:eco,dpr_1/c7252d3beece870ede50");
                }
                checkRestaurant = RDB.checkRestaurant("Dominos Pizza");
                if (checkRestaurant == false) {
                    RDB.insertData("Dominos Pizza","Kollupitiya, Colombo","https://logos-world.net/wp-content/uploads/2021/08/Dominos-Logo-2012-present.png");
                }
                checkRestaurant = RDB.checkRestaurant("StarBucks");
                if (checkRestaurant == false) {
                    RDB.insertData("StarBucks","Kotahena, Colombo","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMi1fbtB1h3YiRo6aO-UL1J3cmD1ezzXtZsw&usqp=CAU");
                }
                checkRestaurant = RDB.checkRestaurant("Dinemore");
                if (checkRestaurant == false) {
                    RDB.insertData("Dinemore","Wattala, Gampaha","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1XmJUVNoQpswKvRHEA-wX-evbigYvscv1sw-Y-nxlrv1sQKL5f-8dWrWaKArvp9W-A-c&usqp=CAU");
                }

                Intent intent = new Intent(FrontPageActivity.this, PopularActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}