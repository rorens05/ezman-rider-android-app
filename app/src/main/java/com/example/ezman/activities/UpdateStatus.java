package com.example.ezman.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ezman.R;
import com.example.ezman.libraries.GlobalVariables;

public class UpdateStatus extends AppCompatActivity {

    LinearLayout processing;
    LinearLayout buying;
    LinearLayout shipping;
    LinearLayout delivered;
    LinearLayout failed;

    ImageView si_processing;
    ImageView si_buying;
    ImageView si_shipping;
    ImageView si_delivered;
    ImageView si_failed;

    TextView si_processing_t;
    TextView si_buying_t;
    TextView si_shipping_t;
    TextView si_delivered_t;
    TextView si_failed_t;

    Button s_save;

    EditText s_note;

    String status = "Processing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);

        init();
        clearColor();
        selectSelectedStatus();
    }

    public void init(){
        processing = findViewById(R.id.ll_processing);
        buying = findViewById(R.id.ll_buying);
        shipping = findViewById(R.id.ll_shipping);
        delivered = findViewById(R.id.ll_delivering);
        failed = findViewById(R.id.ll_failed);

        si_processing = findViewById(R.id.si_processing);
        si_buying = findViewById(R.id.si_buying);
        si_shipping = findViewById(R.id.si_shipping);
        si_delivered = findViewById(R.id.si_delivered);
        si_failed = findViewById(R.id.si_failed);

        si_processing_t = findViewById(R.id.si_processing_t);
        si_buying_t = findViewById(R.id.si_buying_t);
        si_shipping_t = findViewById(R.id.si_shipping_t);
        si_delivered_t = findViewById(R.id.si_delivered_t);
        si_failed_t = findViewById(R.id.si_failed_t);

        s_note = findViewById(R.id.s_note);
        s_save = findViewById(R.id.s_save);

        s_note.setText(GlobalVariables.selectedTransaction.notice);

        s_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_note.setFocusable(true);
            }
        });

        processing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearColor();
                si_processing.setColorFilter(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
                si_processing_t.setTextColor(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary));
                status = "Processing";
            }
        });
        buying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearColor();
                si_buying.setColorFilter(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
                si_buying_t.setTextColor(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary));
                status = "Buying";
            }
        });
        shipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearColor();
                si_shipping.setColorFilter(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
                si_shipping_t.setTextColor(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary));
                status = "Shipping";
            }
        });
        delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearColor();
                si_delivered.setColorFilter(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
                si_delivered_t.setTextColor(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary));
                status = "Delivered";
            }
        });
        failed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearColor();
                si_failed.setColorFilter(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
                si_failed_t.setTextColor(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary));
                status = "Failed";
            }
        });

    }

    public void clearColor(){
        si_processing.setColorFilter(ContextCompat.getColor(this, R.color.colorIconGray), android.graphics.PorterDuff.Mode.SRC_IN);
        si_buying.setColorFilter(ContextCompat.getColor(this, R.color.colorIconGray), android.graphics.PorterDuff.Mode.SRC_IN);
        si_shipping.setColorFilter(ContextCompat.getColor(this, R.color.colorIconGray), android.graphics.PorterDuff.Mode.SRC_IN);
        si_delivered.setColorFilter(ContextCompat.getColor(this, R.color.colorIconGray), android.graphics.PorterDuff.Mode.SRC_IN);
        si_failed.setColorFilter(ContextCompat.getColor(this, R.color.colorIconGray), android.graphics.PorterDuff.Mode.SRC_IN);

        si_processing_t.setTextColor(ContextCompat.getColor(this, R.color.colorIconGray));
        si_buying_t.setTextColor(ContextCompat.getColor(this, R.color.colorIconGray));
        si_shipping_t.setTextColor(ContextCompat.getColor(this, R.color.colorIconGray));
        si_delivered_t.setTextColor(ContextCompat.getColor(this, R.color.colorIconGray));
        si_failed_t.setTextColor(ContextCompat.getColor(this, R.color.colorIconGray));
    }

    public void selectSelectedStatus(){
        switch(GlobalVariables.selectedTransaction.status){
            case "Processing":
                si_processing.setColorFilter(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
                si_processing_t.setTextColor(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary));
                break;
            case "Buying":
                si_buying.setColorFilter(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
                si_buying_t.setTextColor(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary));
                break;
            case "Shipping":
                si_shipping.setColorFilter(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
                si_shipping_t.setTextColor(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary));
                break;
            case "Delivered":
                si_delivered.setColorFilter(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
                si_delivered_t.setTextColor(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary));
                break;
            case "Failed":
                si_failed.setColorFilter(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
                si_failed_t.setTextColor(ContextCompat.getColor(UpdateStatus.this, R.color.colorPrimary));
                break;
        }
    }
}
