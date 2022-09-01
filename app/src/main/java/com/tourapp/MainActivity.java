package com.tourapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tourapp.databinding.ActivityMainBinding;
import com.tourapp.fragment.AdvisorFragment;
import com.tourapp.fragment.CabFragment;
import com.tourapp.fragment.HomeFragment;
import com.tourapp.fragment.HotelFragment;
import com.tourapp.fragment.TableFragment;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.navBar.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.adviser:
                    replaceFragment(new AdvisorFragment());
                    break;
                case R.id.hotel:
                    replaceFragment(new HotelFragment());
                    break;
                case R.id.table:
                    replaceFragment(new TableFragment());
                    break;
                case R.id.cab:
                    replaceFragment(new CabFragment());
                    break;

            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }


}