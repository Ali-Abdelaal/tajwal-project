package com.example.android.tajawal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.tajawal.models.Hotel;
import com.example.android.tajawal.models.hotels;
import com.example.android.tajawal.netWork.getHotel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import static com.example.android.tajawal.MainActivity.highRate;
import static com.example.android.tajawal.MainActivity.lowRate;


public class HotelInfo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_info);

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://gchbib.de/tajawal/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getHotel getHotelData = retrofit2.create(getHotel.class);

        final Call<hotels> hotelListData = getHotelData.getHotel();

        hotelListData.enqueue(new Callback<hotels>() {
            @Override
            public void onResponse(Call<hotels> call, Response<hotels> response) {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.item_hotel_data);

                Intent intent = getIntent();
                String url = intent.getStringExtra("url_image_hotel");
             //   String url = intent.getStringExtra(url_image_hotel);
                String hotelAdress = intent.getStringExtra("adress");
                String Name = intent.getStringExtra("hotelName");
                double actualPrice = intent.getDoubleExtra("lowRate" , lowRate);
                double normalPrice = intent.getDoubleExtra("highRate" , highRate);


                ImageView imageView = linearLayout.findViewById(R.id.img_hotel);
                Picasso.get().load(url).noPlaceholder()
                        .resize(700, 700)
                        .into(imageView);

                TextView textView = (TextView) linearLayout.findViewById(R.id.hotel_name_data);
                textView.setText(Name);

                TextView textView1 = (TextView) linearLayout.findViewById(R.id.adress);
                textView1.setText(hotelAdress);

                TextView textView2 = (TextView) linearLayout.findViewById(R.id.high_rate);
                textView2.setText("normal Price : " + Double.toString(normalPrice) + "$");

                TextView textView3 = (TextView) linearLayout.findViewById(R.id.low_rate);
                textView3.setText("actual Price : " + Double.toString(actualPrice) +"$");


    /*
                int ho = intent.getIntExtra("hotelId", hotelId);
                TextView textView = (TextView) linearLayout.findViewById(R.id.hotel_name_data);
                textView.setText(Integer.toString(ho));
*/

            }

            @Override
            public void onFailure(Call<hotels> call, Throwable t) {

            }
        });


    }


}
