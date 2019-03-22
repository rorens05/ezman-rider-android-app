package com.example.ezman.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.ezman.R;
import com.example.ezman.libraries.GlobalVariables;
import com.example.ezman.libraries.MyDrawView;

import java.io.ByteArrayOutputStream;

public class SignatureActivity extends AppCompatActivity {

    RelativeLayout drawView;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        drawView = findViewById(R.id.drawView);
        save = findViewById(R.id.signature_save_btn);
        MyDrawView myDrawView = new MyDrawView(this);
        drawView.addView(myDrawView);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setDrawingCacheEnabled(true);
                Bitmap b = drawView.getDrawingCache();
                //saveBitmap(b);
                GlobalVariables.bitmap = b;
                GlobalVariables.compressed_bitmap = imageToString(b);
                finish();
            }
        });
    }

    public String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        Log.d("TAG", Base64.encodeToString(imageBytes, Base64.DEFAULT));
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}
