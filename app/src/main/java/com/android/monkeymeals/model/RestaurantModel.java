package com.android.monkeymeals.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/* REFERENCE CODE : https://guides.codepath.com/android/using-parcelable
https://stackoverflow.com/questions/1626667/how-to-use-parcel-in-android */

public class RestaurantModel implements Parcelable {

    private String name;
    private String address;
    private String image;
    private float delivery_charge;
    private List<Menu> menus;



    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDelivery_charge() {
        return delivery_charge;
    }


    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public static final Creator<RestaurantModel> CREATOR = new Creator<RestaurantModel>() {
        @Override
        public RestaurantModel createFromParcel(Parcel in) {
            return new RestaurantModel(in);
        }

        @Override
        public RestaurantModel[] newArray(int size) {
            return new RestaurantModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    protected RestaurantModel(Parcel in) {
        name = in.readString();
        address = in.readString();
        image = in.readString();
        delivery_charge = in.readFloat();
        menus = in.createTypedArrayList(Menu.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(image);
        dest.writeFloat(delivery_charge);
        dest.writeTypedList(menus);
    }
}
