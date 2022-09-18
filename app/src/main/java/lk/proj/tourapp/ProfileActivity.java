package lk.proj.tourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import lk.proj.tourapp.dto.User;

public class ProfileActivity extends AppCompatActivity {
    User user;
    TextView lblProfileContactNo;
    TextView lblProfileEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = (User) getIntent().getSerializableExtra("user");

        lblProfileContactNo = findViewById(R.id.lblProfileContactNo);
        lblProfileEmail = findViewById(R.id.lblProfileEmail);

        lblProfileEmail.setText(user.getEmail());
        lblProfileContactNo.setText(user.getContactNo());
    }
}