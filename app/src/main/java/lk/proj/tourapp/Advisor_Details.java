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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import lk.proj.tourapp.adapter.Advisor;
import lk.proj.tourapp.databinding.ActivityAdvisorDetailsBinding;

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
        binding=ActivityAdvisorDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        name=findViewById(R.id.adviserDetailsNameText);
        hiredCount=findViewById(R.id.hiredCountText);
        goodReviews=findViewById(R.id.goodReviesCoutTex);
        badReviews=findViewById(R.id.badReviewsCoutText);
        email=findViewById(R.id.advisorGmailText);
        contactNo=findViewById(R.id.advisorContactNoText);

        decorView=getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility==0) {
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        Intent intent = this.getIntent();
        if(intent != null){
            advisorId =intent.getStringExtra("advisorId");
        }
        db = FirebaseFirestore.getInstance();

            getAdvisors(advisorId);

        hireButton=findViewById(R.id.advisorHireButton);
        hireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert(advisorId);
            }
        });
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

    private void showAlert(String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("you hired "+name+" as your advisor")
                .setCancelable(false)
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setTitle("Succcess!")
                .setIcon(R.drawable.ic_baseline_sentiment_satisfied_alt_24);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void getAdvisors(String id){

        System.out.println("GET + "+id);
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
//                                    proPic.setImageBitmap();
                                    email.setText(task.getResult().getData().get("email").toString());


                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}





















