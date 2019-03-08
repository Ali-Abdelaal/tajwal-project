package com.example.android.tajawal.netWork;


import com.example.android.tajawal.models.hotels;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getHotel {
    @GET("hotels_exercise.json")
    Call<hotels> getHotel();
}
