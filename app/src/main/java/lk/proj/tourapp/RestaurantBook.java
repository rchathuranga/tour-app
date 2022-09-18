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

import lk.proj.tourapp.adapter.Hotel;
import lk.proj.tourapp.adapter.Table;
import lk.proj.tourapp.dto.User;

public class RestaurantBook extends AppCompatActivity {

    Table table;
    User user;
    TextView lblHotelRegDetails;
    TextInputEditText txtHotelBookNoOfPeople, txtHBUserName, txtHBUserContactNo;
    Button btnHotelBook;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_book);

        db = FirebaseFirestore.getInstance();
        table = (Table) getIntent().getSerializableExtra("table");
        user = (User) getIntent().getSerializableExtra("user");

        txtHotelBookNoOfPeople = findViewById(R.id.txtHotelBookNoOfPeople);
        txtHBUserName = findViewById(R.id.txtHBUserName);
        txtHBUserContactNo = findViewById(R.id.txtHBUserContactNo);
        btnHotelBook = findViewById(R.id.btnHotelBook);
        lblHotelRegDetails = findViewById(R.id.lblHotelRegDetails);

        txtHBUserName.setText(user.getName());
        lblHotelRegDetails.setText(table.getRestaurantName());
        txtHBUserContactNo.setText(user.getContactNo());
        txtHotelBookNoOfPeople.setText("2");

        btnHotelBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String contactNo = txtHBUserContactNo.getText().toString();

                if (contactNo.isEmpty()) {
                    Toast.makeText(RestaurantBook.this, "ContactNo is Required",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (contactNo.length()!=10) {
                    Toast.makeText(RestaurantBook.this, "Invalid Contact No",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int noOfPeople = 0;
                try {
                    noOfPeople = Integer.parseInt(txtHotelBookNoOfPeople.getText().toString());

                    if (txtHotelBookNoOfPeople.getText().toString().isEmpty()) {
                        Toast.makeText(RestaurantBook.this, "No Of People is Required",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    Toast.makeText(RestaurantBook.this, "Please enter numeric value",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                user.setNoOfPeople(noOfPeople);
                user.setContactNo(contactNo);
                user.setRestaurantId(table.getTableId());


                db.collection("users").document(user.getUserId()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(RestaurantBook.this, "Restaurant Table Booked Successfully",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

            }
        });
    }
}