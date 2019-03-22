package com.example.ezman.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.ezman.R;
import com.example.ezman.libraries.GlobalVariables;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ImageView i = findViewById(R.id.im1);
        i.setImageBitmap(GlobalVariables.bitmap);
    }
}
