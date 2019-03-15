package com.example.ezman.activities.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ezman.R;
import com.example.ezman.activities.fragments.recycler_adapter.TransactionAdapter;
import com.example.ezman.libraries.GlobalVariables;
import com.example.ezman.libraries.MySingleton;
import com.example.ezman.models.Transaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class TransactionFragment extends Fragment {
    private static final String TAG = "TransactionFragment";
    RecyclerView recyclerView;
    TransactionAdapter transactionAdapter;
    ProgressDialog pd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_transactions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Orders");

        recyclerView = getView().findViewById(R.id.order_rv);
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading orders");
        pd.setCancelable(false);
        loadTransactions();

    }

    public void loadTransactions(){
        pd.show();
        GlobalVariables.transactionList = new ArrayList<>();
        String url = GlobalVariables.GET_TRANSACTIONS + "?id=" + GlobalVariables.rider.id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("data");

                    for (int i = 0; i < array.length(); i++) {
                        Transaction temp = new Transaction();
                        temp.id = array.getJSONObject(i).getString("id");
                        temp.customer_id = array.getJSONObject(i).getString("customer_id");
                        temp.sub_total = array.getJSONObject(i).getString("sub_total");
                        temp.shipping_fee = array.getJSONObject(i).getString("shipping_fee");
                        temp.status = array.getJSONObject(i).getString("status");
                        temp.notice = array.getJSONObject(i).getString("notice");
                        temp.delivery_location = array.getJSONObject(i).getString("delivery_location");
                        temp.delivered_at = array.getJSONObject(i).getString("delivered_at");
                        temp.items = array.getJSONObject(i).getString("items");
                        temp.rider_id = array.getJSONObject(i).getString("rider_id");
                        temp.location_x = array.getJSONObject(i).getString("location_x");
                        temp.location_y = array.getJSONObject(i).getString("location_y");
                        temp.customer = array.getJSONObject(i).getString("customer");
                        temp.image = array.getJSONObject(i).getString("image");
                        temp.time = array.getJSONObject(i).getString("time");
                        temp.short_date = array.getJSONObject(i).getString("short_date");
                        temp.date = array.getJSONObject(i).getString("date");
                        temp.total = array.getJSONObject(i).getString("total");
                        temp.actual_location = array.getJSONObject(i).getString("actual_location");
                        GlobalVariables.transactionList.add(temp);
                    }
                    loadRecyclerView();
                } catch (JSONException e) {
                    Toast.makeText(getActivity(), GlobalVariables.SOMETHING_WENT_WRONG, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


                pd.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), GlobalVariables.CONNECTION_FAILURE, Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);



    }

    public void loadRecyclerView() {

        Log.d(TAG, "initializing recyclerview adapter");
        Transaction t = new Transaction();
        t.customer = "laurence bautista";
        t.delivery_location = "Anda";
        t.items = "List of Items sample 1 sample 2";
        t.status = "Failed";
        t.short_date = "Mar 4";
        t.image = "http://192.168.43.194:3000/images/logo.png";
        GlobalVariables.transactionList.add(t);

        Collections.reverse(GlobalVariables.transactionList);
        transactionAdapter = new TransactionAdapter(GlobalVariables.transactionList, getActivity());
        recyclerView.setAdapter(transactionAdapter);
        Log.d(TAG, "adapter initialized ");

        Log.d(TAG, "Displaying Data");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}
