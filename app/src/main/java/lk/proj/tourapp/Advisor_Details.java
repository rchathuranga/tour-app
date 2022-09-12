package lk.proj.tourapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import lk.proj.tourapp.adapter.Advisor;
import lk.proj.tourapp.databinding.ActivityAdvisorDetailsBinding;
import lk.proj.tourapp.dto.User;

public class Advisor_Details extends AppCompatActivity {

    ActivityAdvisorDetailsBinding binding;
    Button hireButton;
    String advisorId;
    private View decorView;
    FirebaseFirestore db;
    TextView name;
    TextView hiredCount;
    TextView goodReviews;
    TextView badReviews;
    TextView contactNo;
    TextView email;
    ImageView proPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdvisorDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        name = findViewById(R.id.adviserDetailsNameText);
        hiredCount = findViewById(R.id.hiredCountText);
        goodReviews = findViewById(R.id.goodReviesCoutTex);
        badReviews = findViewById(R.id.badReviewsCoutText);
        email = findViewById(R.id.advisorGmailText);
        contactNo = findViewById(R.id.advisorContactNoText);
        proPic = findViewById(R.id.advisorProPic);

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0) {
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        Intent intent = this.getIntent();
        advisorId = intent.getStringExtra("advisorId");
        User user = (User) intent.getSerializableExtra("user");

        db = FirebaseFirestore.getInstance();
        getAdvisors(advisorId);

        hireButton = findViewById(R.id.advisorHireButton);
        hireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserSelectedAdvisor(user, advisorId);
            }
        });

        if (Objects.equals(user.getAdvisorId(), advisorId)) {
            hireButton.setEnabled(false);
        }
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

    private void getAdvisors(String id) {

        db.collection("advisors").document(id).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            System.out.println(task.getResult().getData());

                            name.setText(task.getResult().getData().get("name").toString());
                            contactNo.setText(task.getResult().getData().get("contactNo").toString());
                            badReviews.setText(task.getResult().getData().get("badReview").toString());
                            goodReviews.setText(task.getResult().getData().get("goodReview").toString());
                            hiredCount.setText(task.getResult().getData().get("hiredCount").toString());
                            Picasso.get().load(task.getResult().getData().get("imageUrl").toString()).into(proPic);
                            email.setText(task.getResult().getData().get("email").toString());


                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }


    public void updateUserSelectedAdvisor(User user, String advisorId) {
        user.setAdvisorId(advisorId);
        db.collection("users").document(user.getUserId()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Advisor_Details.this, "Hired Successfully.",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}





















