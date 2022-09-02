package com.tourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tourapp.databinding.ActivityAdvisorDetailsBinding;
import com.tourapp.databinding.ActivityHotelDetailsBinding;

public class Hotel_Details extends AppCompatActivity {
    Button bookButton;
    String name;
    private View decorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        bookButton=findViewById(R.id.hotelBookButton);
        decorView=getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility==0) {
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        Intent intent = this.getIntent();
        if(intent != null){
            name =intent.getStringExtra("name");
            TextView viewById = findViewById(R.id.hotelDetailsNameText);
            viewById.setText(name);
        }

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Hotel_Details.this, Hotel_Book.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars(){
        return  View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }
}