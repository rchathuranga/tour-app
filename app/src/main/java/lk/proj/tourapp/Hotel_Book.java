package lk.proj.tourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import lk.proj.tourapp.adapter.Hotel;
import lk.proj.tourapp.dto.User;

public class Hotel_Book extends AppCompatActivity {
    TextView lblHotelName;
    TextInputEditText txtCheckIn, txtCheckOut, txtNoOfPeople, txtPrice, txtName, txtContact;
    private View decorView;
    private Hotel hotel;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_book);
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0) {
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        hotel = (Hotel) getIntent().getSerializableExtra("hotel");
        user = (User) getIntent().getSerializableExtra("user");

        lblHotelName = findViewById(R.id.hotelRegDetails);
        txtName = findViewById(R.id.txtHBUserName);
        txtContact = findViewById(R.id.txtHBUserContactNo);

        lblHotelName.setText(hotel.getHotelName());
        txtName.setText(user.getName());
        txtContact.setText(user.getContactNo());

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }
}