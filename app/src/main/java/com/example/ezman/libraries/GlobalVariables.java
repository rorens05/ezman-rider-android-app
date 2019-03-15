package com.example.ezman.libraries;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ezman.models.Rider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GlobalVariables {
    //constants
    public static final String SERVER = "http://192.168.43.194:3000/rider_api/";
    public static final String LOGIN_URL = SERVER + "login";
    public static final String UPDATE_LOCATION_URL = SERVER + "update_location";





    //observable
    public static String latitude = "0";
    public static String longitute = "0";
    public static Rider rider = new Rider();











    //messages
    public static final String FAILED = "failed";
    public static final String SUCCESS = "success";
    public static final String CONNECTION_FAILURE = "Cannot connect to the server";
    public static final String INVALID_LOGIN = "Invalid email and/or password combination";

    //useful functions

    public static void updateLocationOnServer(final Context context){
        StringRequest updateLocation = new StringRequest(Request.Method.POST, UPDATE_LOCATION_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jResponse = new JSONObject(response);
                    Toast.makeText(context, jResponse.getString("message"), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Unable to connect to the server while updating the location", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, ("Disconnected from the server"), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", rider.id);
                params.put("lat", latitude);
                params.put("long", longitute);

                return params;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(updateLocation);

    }
}
