package com.example.android.tajawal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.tajawal.models.Hotel;
import com.example.android.tajawal.models.Image;
import com.example.android.tajawal.models.Location;
import com.example.android.tajawal.models.Summary;

import com.example.android.tajawal.models.hotels;
import com.example.android.tajawal.netWork.getHotel;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements hotelAdpter.OnItemClickListener {
    getHotel mGetHotel;   //   object of getHotel(interface)  for (implement get request )
    ArrayList<Hotel> hotelDataList = new ArrayList<Hotel>(); // list to store data from response body
    //  which send to adpter
    // put extra data intent
   // public static int hotelId = 0;
    //public static String url_image_hotel ;
    //public static String hotelName ;
    //public static String adress ;
    public static int lowRate = 0;
    public static int highRate = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gchbib.de/tajawal/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mGetHotel = retrofit.create(getHotel.class);  //declration of mGetHotel
        getHotelsResponse();


    }


    public void getHotelsResponse() {

        // manipulate  response of get request foe mGetHotel object
        //no argument
        //void


        final Call<hotels> hotelList = mGetHotel.getHotel();  // implement getHotel message


        hotelList.enqueue(new Callback<hotels>() {
            @Override
            public void onResponse(Call<hotels> call, Response<hotels> response) {


                hotels hotelbody = response.body();   // body of response saved temp varaible


                for (int i = 0; i < hotelbody.getHotel().size(); i++) {
                    Hotel hotelData = new Hotel(); //temp object to store data which
                    // extract from response boady
                    hotelData = hotelbody.getHotel().get(i);
                    hotelDataList.add(hotelData);   //add it to list which send to adpter
                }


                GridView gridView = (GridView) findViewById(R.id.List);
                hotelAdpter adpter = new hotelAdpter(MainActivity.this, hotelDataList);
                gridView.setAdapter(adpter);
                adpter.setOnItemClickListener(MainActivity.this);


            }

            @Override
            public void onFailure(Call<hotels> call, Throwable t) {
                Log.e("Erorr ahmed", t.getMessage());

                //add what you do when no connection

            }
        });


    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, HotelInfo.class);
        Hotel clickedItem = hotelDataList.get(position);
        intent.putExtra("url_image_hotel", clickedItem.getImage().get(0).getUrl());
        intent.putExtra("hotelName", clickedItem.getSummary().getHotelName());
        intent.putExtra("adress", clickedItem.getLocation().getAddress());
        intent.putExtra("lowRate", clickedItem.getSummary().getLowRate());
        intent.putExtra("highRate", clickedItem.getSummary().getHighRate());
        intent.putExtra("hotelId", clickedItem.getHotelId());
        startActivity(intent);


    }
}
