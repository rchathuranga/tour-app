package lk.proj.tourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import lk.proj.tourapp.dto.User;

public class ProfileActivity extends AppCompatActivity {
    User user;
    TextView lblProfileContactNo, lblProfileName, lblProfileId;
    TextView lblProfileEmail;
    Button btnLogout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();

        user = (User) getIntent().getSerializableExtra("user");

        lblProfileContactNo = findViewById(R.id.lblProfileContactNo);
        lblProfileEmail = findViewById(R.id.lblProfileEmail);
        lblProfileId = findViewById(R.id.lblProfileId);
        lblProfileName = findViewById(R.id.lblProfileName);
        btnLogout = findViewById(R.id.btnLogOut);

        lblProfileId.setText("ID: "+user.getUserId());
        lblProfileName.setText(user.getName());
        lblProfileEmail.setText(user.getEmail());
        lblProfileContactNo.setText(user.getContactNo());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}