package com.example.android.tajawal.models;

import java.util.List;

public class hotels  {  //model  of java object to implement key:value data come from json

    private List<Hotel> hotel ;

    public hotels() {
    }

    public List<Hotel> getHotel() {
        return hotel;
    }

    public void setHotel(List<Hotel> hotel) {
        this.hotel = hotel;
    }


}
