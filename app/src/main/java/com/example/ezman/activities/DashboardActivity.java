package com.example.ezman.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
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
                    Log.d(TAG, "transaction fragment selected");
                    break;
                case R.id.navigation_profile:
                    selectedFragment = new ProfileFragment();
                    Log.d(TAG, "transaction fragment selected");
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
    }

}
