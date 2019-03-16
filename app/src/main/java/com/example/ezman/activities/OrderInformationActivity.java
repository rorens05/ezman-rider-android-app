package com.example.ezman.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ezman.R;

public class OrderInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_order_information);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }



}
