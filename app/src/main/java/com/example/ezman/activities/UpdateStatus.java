package com.example.ezman.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ezman.R;
import com.example.ezman.libraries.GlobalVariables;
import com.example.ezman.libraries.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

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
    ProgressDialog progressDialog;

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
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait");
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
                startActivity(new Intent(UpdateStatus.this, SignatureActivity.class));
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
        s_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if(status.equalsIgnoreCase("Delivered")){
                    if (GlobalVariables.bitmap == null) {
                        Toast.makeText(UpdateStatus.this, "Please get the signature of the customer first", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }else{
                        update_delivered();
                    }
                }else{
                    update_status();
                }
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

    public void update_status(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GlobalVariables.UPDATE_STATUS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                        Toast.makeText(UpdateStatus.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                        GlobalVariables.selectedTransaction.status = status;
                        GlobalVariables.selectedTransaction.notice = s_note.getText().toString();
                        finish();
                    }else{
                        Toast.makeText(UpdateStatus.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(UpdateStatus.this, GlobalVariables.SOMETHING_WENT_WRONG, Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateStatus.this, GlobalVariables.CONNECTION_FAILURE, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("status", status);
                params.put("notice", s_note.getText().toString());
                params.put("id", GlobalVariables.selectedTransaction.id);
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void update_delivered(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GlobalVariables.UPDATE_DELIVERED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                        Toast.makeText(UpdateStatus.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                        GlobalVariables.selectedTransaction.status = status;
                        GlobalVariables.selectedTransaction.notice = s_note.getText().toString();
                        finish();
                    }else{
                        Toast.makeText(UpdateStatus.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(UpdateStatus.this, GlobalVariables.SOMETHING_WENT_WRONG, Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateStatus.this, GlobalVariables.CONNECTION_FAILURE, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("lat", GlobalVariables.latitude);
                params.put("long", GlobalVariables.longitute);
                params.put("notice", s_note.getText().toString());
                params.put("id", GlobalVariables.selectedTransaction.id);
                params.put("image", GlobalVariables.compressed_bitmap);
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        Log.d("TAG", Base64.encodeToString(imageBytes, Base64.DEFAULT));
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}
