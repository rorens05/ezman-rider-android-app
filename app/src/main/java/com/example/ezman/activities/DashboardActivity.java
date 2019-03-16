package com.example.ezman.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ezman.R;
import com.example.ezman.activities.fragments.CustomerFragment;
import com.example.ezman.activities.fragments.ProfileFragment;
import com.example.ezman.activities.fragments.TransactionFragment;

public class DashboardActivity extends AppCompatActivity {
    private static final String TAG = "DashboardActivity";
    FrameLayout frameLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            Log.d(TAG, "Menu Selected");
            switch (item.getItemId()) {
                case R.id.navigation_transactions:
                    selectedFragment = new TransactionFragment();
                    Log.d(TAG, "transaction fragment selected");
                    break;
                case R.id.navigation_customer:
                    selectedFragment = new CustomerFragment();
                    Log.d(TAG, "CustomerFragment fragment selected");
                    break;
                case R.id.navigation_profile:
                    selectedFragment = new ProfileFragment();
                    Log.d(TAG, "ProfileFragment fragment selected");
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        frameLayout = findViewById(R.id.fragment_container);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new TransactionFragment()).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new TransactionFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        back();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure want to quit?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void back(){
        super.onBackPressed();
    }
}
