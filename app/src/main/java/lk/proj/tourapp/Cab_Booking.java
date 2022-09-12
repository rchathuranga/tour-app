package lk.proj.tourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import lk.proj.tourapp.adapter.Cab;
import lk.proj.tourapp.adapter.Hotel;
import lk.proj.tourapp.dto.User;

public class Cab_Booking extends AppCompatActivity {

    TextView cabDriverName;
    Button btnHire;

    private Cab cab;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cab_booking);


        cab = (Cab) getIntent().getSerializableExtra("cab");
        user = (User) getIntent().getSerializableExtra("user");

        cabDriverName = findViewById(R.id.cabDriverName);
        btnHire = findViewById(R.id.btnCabHire);

        cabDriverName.setText(cab.getDriverName());


        btnHire.setOnClickListener(view -> {

        });
    }
}