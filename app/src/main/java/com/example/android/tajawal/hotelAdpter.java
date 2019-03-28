package com.example.android.tajawal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.tajawal.models.Hotel;



import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;

public class hotelAdpter extends ArrayAdapter<Hotel> {

    private  OnItemClickListener mlistener ;


    public interface OnItemClickListener {
        void onItemClick (int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mlistener = listener ;
    }



    public hotelAdpter(Context context, ArrayList<Hotel> mHotel) {

        super(context, 0, mHotel);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {

        Hotel hotelDataAdpterItem = getItem(position);
        View selectedView = convertView;

        if (selectedView == null) {

            selectedView = LayoutInflater.from(getContext()).inflate(R.layout.hotel_item, parent, false);

        }
        // text name of hotel
        TextView textView = (TextView) selectedView.findViewById(R.id.hotel_name);
        textView.setText(hotelDataAdpterItem.getSummary().getHotelName());

        //  image
        ImageView imageView = (ImageView) selectedView.findViewById(R.id.img);
        String imageUri = hotelDataAdpterItem.getImage().get(0).getUrl();
        Picasso.get().load(imageUri).noPlaceholder().error(R.drawable.ic_launcher_background)
                .resize(600, 600)
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if  ( mlistener != null)
                    mlistener.onItemClick(position);
            }
        });






        return selectedView;
    }





}
