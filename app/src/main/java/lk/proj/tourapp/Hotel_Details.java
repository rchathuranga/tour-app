package lk.proj.tourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import lk.proj.tourapp.adapter.Hotel;
import lk.proj.tourapp.databinding.ActivityAdvisorDetailsBinding;
import lk.proj.tourapp.databinding.ActivityHotelDetailsBinding;
import lk.proj.tourapp.dto.User;

public class Hotel_Details extends AppCompatActivity {
    Button bookButton;
    TextView lblHotelName, lblAvailableRoom, lblRoomType, lblRating, lblDescription, lblLocation, lblEmail, lblContact;
    ImageView imgHotelImage;
    private View decorView;
    private Hotel hotel;
    private User user;

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

        lblHotelName = findViewById(R.id.hotelDetailsNameText);
        lblAvailableRoom = findViewById(R.id.lblAvailableRoom);
        lblRoomType = findViewById(R.id.lblRoomType);
        lblRating = findViewById(R.id.lblRating);
        lblDescription = findViewById(R.id.lblDescription);
        lblLocation = findViewById(R.id.lblLocation);
        lblEmail = findViewById(R.id.lblEmail);
        lblContact = findViewById(R.id.lblContact);
        imgHotelImage = findViewById(R.id.imgHotelImage);

        hotel = (Hotel) getIntent().getSerializableExtra("hotel");
        user = (User) getIntent().getSerializableExtra("user");

        lblHotelName.setText(hotel.getHotelName());
        lblAvailableRoom.setText(String.valueOf(hotel.getAvailableRooms()));
        lblRoomType.setText(hotel.getRoomTypes());
        lblRating.setText(hotel.getRating());
        lblDescription.setText(hotel.getDescription());
        lblLocation.setText(hotel.getLocation());
        lblEmail.setText(hotel.getEmail());
        lblContact.setText(hotel.getContactNo());
        Picasso.get().load(hotel.getImageUrl()).into(imgHotelImage);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hotel_Details.this, Hotel_Book.class);
                hotel.setBtnMoreInfoClickEvent(null);
                intent.putExtra("hotel", hotel);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        if (Objects.equals(user.getHotelId(), hotel.getHotelId())) {
            bookButton.setEnabled(false);
        }
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