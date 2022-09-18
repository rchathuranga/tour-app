package lk.proj.tourapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

import lk.proj.tourapp.adapter.Hotel;
import lk.proj.tourapp.dto.User;

public class Hotel_Book extends AppCompatActivity {
    TextView lblHotelName;
    TextInputEditText txtCheckIn, txtCheckOut, txtNoOfPeople, txtName, txtContact;
    Button btnHotelBook;
    private View decorView;
    private Hotel hotel;
    private User user;
    FirebaseFirestore db;

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

        db = FirebaseFirestore.getInstance();
        hotel = (Hotel) getIntent().getSerializableExtra("hotel");
        user = (User) getIntent().getSerializableExtra("user");

        lblHotelName = findViewById(R.id.hotelRegDetails);
        txtCheckIn = findViewById(R.id.txtHotelBookCheckIn);
        txtCheckOut = findViewById(R.id.txtHotelBookCheckOut);
        txtNoOfPeople = findViewById(R.id.txtHotelBookNoOfPeople);
        txtName = findViewById(R.id.txtHBUserName);
        txtContact = findViewById(R.id.txtHBUserContactNo);
        btnHotelBook = findViewById(R.id.btnHotelBook);

        lblHotelName.setText(hotel.getHotelName());
        txtName.setText(user.getName());
        txtContact.setText(user.getContactNo());

        final Date todayDate = new Date();

        String date = new SimpleDateFormat("dd-MM-yyyy").format(todayDate);


        txtCheckIn.setText(date);
        txtNoOfPeople.setText("2");


        btnHotelBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String checkIn = txtCheckIn.getText().toString();
                String checkOut = txtCheckOut.getText().toString();

                if (checkIn.isEmpty()) {
                    Toast.makeText(Hotel_Book.this, "CheckIn Date is Required",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!checkIn.matches("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$")) {
                    Toast.makeText(Hotel_Book.this, "Please enter valid date (dd-MM-yyyy)",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (checkOut.isEmpty()) {
                    Toast.makeText(Hotel_Book.this, "CheckOut Date is Required",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!checkOut.matches("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$")) {
                    Toast.makeText(Hotel_Book.this, "Please enter valid date (dd-MM-yyyy)",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int noOfPeople = 0;
                try {
                    noOfPeople = Integer.parseInt(txtNoOfPeople.getText().toString());

                    if (txtNoOfPeople.getText().toString().isEmpty()) {
                        Toast.makeText(Hotel_Book.this, "No Of People is Required",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    Toast.makeText(Hotel_Book.this, "Please enter numeric value",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                user.setCheckIn(checkIn);
                user.setCheckOut(checkOut);
                user.setNoOfPeople(noOfPeople);
                user.setHotelId(hotel.getHotelId());

                db.collection("users").document(user.getUserId()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Hotel_Book.this, "Hotel Booking Success.",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
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