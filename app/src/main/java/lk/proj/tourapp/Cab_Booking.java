package lk.proj.tourapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import lk.proj.tourapp.adapter.Cab;
import lk.proj.tourapp.adapter.Hotel;
import lk.proj.tourapp.dto.User;

public class Cab_Booking extends AppCompatActivity {

    TextView cabDriverName;
    TextInputEditText txtCabLocation, txtCabName, txtCabContact;
    Button btnHire;

    private Cab cab;
    private User user;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cab_booking);

        db = FirebaseFirestore.getInstance();
        cab = (Cab) getIntent().getSerializableExtra("cab");
        user = (User) getIntent().getSerializableExtra("user");

        cabDriverName = findViewById(R.id.cabDriverName);
        txtCabLocation = findViewById(R.id.txtCabLocation);
        txtCabName = findViewById(R.id.txtCabName);
        txtCabContact = findViewById(R.id.txtCabContact);
        btnHire = findViewById(R.id.btnCabHire);

        cabDriverName.setText(cab.getDriverName());

        txtCabName.setText(user.getName());
        txtCabContact.setText(user.getContactNo());

        btnHire.setOnClickListener(view -> {

            String contactNo = txtCabContact.getText().toString();
            if (contactNo.isEmpty()) {
                Toast.makeText(Cab_Booking.this, "Contact No is Required",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (contactNo.length()!=10) {
                Toast.makeText(Cab_Booking.this, "Invalid Contact No",
                        Toast.LENGTH_SHORT).show();
                return;
            }


            user.setContactNo(contactNo);
            user.setCabId(cab.getDriverId());

            db.collection("users").document(user.getUserId()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(Cab_Booking.this, "Cab Hired Successfully.",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

        });
    }
}