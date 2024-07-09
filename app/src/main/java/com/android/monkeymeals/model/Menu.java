package com.android.monkeymeals.model;

import android.os.Parcel;
import android.os.Parcelable;
 /* REFERENCE CODE : https://guides.codepath.com/android/using-parcelable
https://stackoverflow.com/questions/1626667/how-to-use-parcel-in-android */


public class Menu implements Parcelable {
    private String name;
    private float price;
    private int totalInCart;
    private String url;

    public int getTotalInCart() {
        return totalInCart;
    }

    public void setTotalInCart(int totalInCart) {
        this.totalInCart = totalInCart;
    }



    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };



    public float getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected Menu(Parcel parcel) {
        name = parcel.readString();
        price = parcel.readFloat();
        url = parcel.readString();
        totalInCart = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(price);
        dest.writeString(url);
        dest.writeInt(totalInCart);
    }
}
