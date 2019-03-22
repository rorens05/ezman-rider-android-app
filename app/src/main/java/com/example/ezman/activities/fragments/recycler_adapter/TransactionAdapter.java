package com.example.ezman.activities.fragments.recycler_adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ezman.R;
import com.example.ezman.activities.OrderInformationActivity;
import com.example.ezman.activities.UpdateStatus;
import com.example.ezman.libraries.GlobalVariables;
import com.example.ezman.models.Transaction;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    private static final String TAG = "TransactionAdapter";
    private List<Transaction> transactionList = new ArrayList<>();
    private Context context;

    public TransactionAdapter(List<Transaction> transactionList, Context context) {
        this.transactionList = transactionList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.transaction_list, viewGroup,
                false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final Transaction t = transactionList.get(i);
        int maxlength = 22;
        String name = t.customer.length() >= maxlength ? t.customer.substring(0, maxlength) + "..." : t.customer;
        String location = t.delivery_location.length() >= maxlength ? t.delivery_location.substring(0, maxlength)  + "..." : t.delivery_location;
        String items = t.items.length() >= maxlength ? t.items.substring(0, maxlength)  + "..." : t.items;

        myViewHolder.name.setText(name);
        myViewHolder.location.setText(location);
        myViewHolder.items.setText(items);
        myViewHolder.date.setText(t.short_date);
        myViewHolder.status.setText(t.status);
        if (t.status.equalsIgnoreCase("Failed")) {
            myViewHolder.status.setTextColor(ContextCompat.getColor(context, R.color.red));
        }else if(t.status.equalsIgnoreCase("Delivered")) {
            myViewHolder.status.setTextColor(ContextCompat.getColor(context, R.color.green));
        }

        if (t.image.equalsIgnoreCase("")) {
            myViewHolder.image.setBackgroundResource(R.drawable.logo);
        }else{
            Picasso.get()
                    .load(t.image)
                    .resize(150, 150)
                    .centerCrop()
                    .into(myViewHolder.image);
        }

        myViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, t.customer + " was clicked", Toast.LENGTH_SHORT).show();
                GlobalVariables.selectedTransaction = t;
                ((Activity) context).startActivityForResult(new Intent(context, OrderInformationActivity.class), 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView location;
        TextView items;
        TextView date;
        TextView status;

        ConstraintLayout container;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.tl_image);
            name = itemView.findViewById(R.id.tl_name);
            location = itemView.findViewById(R.id.tl_location);
            items = itemView.findViewById(R.id.tl_items);
            date = itemView.findViewById(R.id.tl_date);
            status = itemView.findViewById(R.id.tl_status);
            container = itemView.findViewById(R.id.tl_container);
        }
    }
}
