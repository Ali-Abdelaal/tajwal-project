package com.example.android.tajawal.models;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hotel  {   // class implement all the data get from json
                        // value of json object
    @SerializedName("hotelId")
    private int hotelId ;

    @SerializedName("image")
    private List  <Image> image ;

    @SerializedName("location")
    private Location location ;

    @SerializedName("summary")
    private Summary summary;

    public Hotel() {
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }


    //Parciling part


}
