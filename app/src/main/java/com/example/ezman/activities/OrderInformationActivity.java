package com.example.ezman.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ezman.R;
import com.example.ezman.activities.fragments.CustomerInfoFragment;
import com.example.ezman.activities.fragments.TransactionFragment;

public class OrderInformationActivity extends AppCompatActivity {

    TextView dateOrdered;
    TextView status;
    TextView location;
    TextView items;
    TextView subTotal;
    TextView shipping;
    TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_order_information);


        getSupportFragmentManager().beginTransaction().replace(R.id.customer_frame,
                new CustomerInfoFragment()).commit();
    }




    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }



}
