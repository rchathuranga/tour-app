package com.tourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tourapp.databinding.ActivityAdvisorDetailsBinding;

public class Advisor_Details extends AppCompatActivity {

    ActivityAdvisorDetailsBinding binding;
    Button hireButton;
    String name;
    private View decorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdvisorDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
            name =intent.getStringExtra("name");
            binding.adviserDetailsNameText.setText(name);
        }

        hireButton=findViewById(R.id.advisorHireButton);
        hireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert(name);
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
}