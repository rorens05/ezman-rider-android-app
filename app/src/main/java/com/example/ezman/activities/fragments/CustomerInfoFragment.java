package com.example.ezman.activities.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ezman.R;
import com.example.ezman.activities.OrderInformationActivity;
import com.example.ezman.libraries.GlobalVariables;
import com.squareup.picasso.Picasso;

import static android.content.Context.TELEPHONY_SERVICE;

public class CustomerInfoFragment extends Fragment {

    TextView name;
    TextView email;
    TextView contact_no;
    TextView address;
    ImageView image;
    Button call;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_customer_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Order Information");
        name = getView().findViewById(R.id.ci_name);
        email = getView().findViewById(R.id.ci_email);
        contact_no = getView().findViewById(R.id.ci_contact_no);
        address = getView().findViewById(R.id.ci_address);
        image = getView().findViewById(R.id.ci_image);
        call = getView().findViewById(R.id.btn_call);

        name.setText(GlobalVariables.selectedTransaction.customer);
        email.setText(GlobalVariables.selectedTransaction.email);
        contact_no.setText(GlobalVariables.selectedTransaction.contact_no);
        address.setText(GlobalVariables.selectedTransaction.address);

        if (GlobalVariables.selectedTransaction.image.equalsIgnoreCase("")) {
            image.setBackgroundResource(R.drawable.logo);
        }else{
            Picasso.get()
                    .load(GlobalVariables.selectedTransaction.image)
                    .resize(150, 150)
                    .centerCrop()
                    .into(image);
        }

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTelephonyEnabled()){
                    startDialActivity(GlobalVariables.selectedTransaction.contact_no);
                }else{
                    Toast.makeText(getContext(), "Telephone is not available on this device", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startDialActivity(String phone){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phone));
        startActivity(intent);
    }

    private boolean isTelephonyEnabled(){
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(TELEPHONY_SERVICE);
        return telephonyManager != null && telephonyManager.getSimState()==TelephonyManager.SIM_STATE_READY;
    }
}
