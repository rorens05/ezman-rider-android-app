package com.example.ezman.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ezman.R;
import com.example.ezman.activities.fragments.CustomerInfoFragment;
import com.example.ezman.activities.fragments.TransactionFragment;
import com.example.ezman.libraries.GlobalVariables;

public class OrderInformationActivity extends AppCompatActivity {

    TextView dateOrdered;
    TextView status;
    TextView location;
    TextView items;
    TextView subTotal;
    TextView shipping;
    TextView total;
    TextView note;

    Button start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_order_information);
        getSupportFragmentManager().beginTransaction().replace(R.id.customer_frame,
                new CustomerInfoFragment()).commit();

        init();
        loadInfo();
    }

    public void init(){
        dateOrdered = findViewById(R.id.oi_date_order);
        status = findViewById(R.id.oi_status);
        location = findViewById(R.id.oi_location);
        items = findViewById(R.id.oi_items);
        subTotal = findViewById(R.id.oi_sub_total);
        shipping = findViewById(R.id.oi_shipping);
        total = findViewById(R.id.oi_total);
        start = findViewById(R.id.io_start);
        note = findViewById(R.id.oi_note);

    }

    public void loadInfo(){
        dateOrdered.setText(GlobalVariables.selectedTransaction.date);
        status.setText(GlobalVariables.selectedTransaction.status);
        location.setText(GlobalVariables.selectedTransaction.delivery_location);
        items.setText(GlobalVariables.selectedTransaction.items);
        subTotal.setText("₱" + GlobalVariables.selectedTransaction.sub_total);
        shipping.setText("₱" + GlobalVariables.selectedTransaction.shipping_fee);
        total.setText("₱" + GlobalVariables.selectedTransaction.total);
        note.setText(GlobalVariables.selectedTransaction.notice);
        if (GlobalVariables.selectedTransaction.status.equalsIgnoreCase("Delivered")) {
            start.setBackgroundColor(ContextCompat.getColor(this, R.color.colorIconGray));start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OrderInformationActivity.this, "You cannot update the status of delivered orders", Toast.LENGTH_LONG).show();

                }
            });
        }else{
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(OrderInformationActivity.this, UpdateStatus.class), 1);
                    //startActivity(new Intent(OrderInformationActivity.this, UpdateStatus.class));

                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        init();
        loadInfo();
    }

}
