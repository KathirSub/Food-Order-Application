package com.android.monkeymeals.model;

public class PopularModel {
    String RestaurantName ;
    String Price ;
    String FoodName ;
    int Image ;

    public PopularModel(String RestaurantName, String Price, String FoodName, int Image) {
        this.RestaurantName = RestaurantName;
        this.Price = Price;
        this.FoodName = FoodName;
        this.Image = Image;
    }


    //Getters
    public String getRestaurantName() {
        return RestaurantName;
    }

    public String getPrice() {
        return Price;
    }

    public String getFoodName() {
        return FoodName;
    }

    public int getImage() {
        return Image;
    }
}
