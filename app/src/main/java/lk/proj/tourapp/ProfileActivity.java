package lk.proj.tourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import lk.proj.tourapp.dto.User;

public class ProfileActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = (User) getIntent().getSerializableExtra("user");
    }
}