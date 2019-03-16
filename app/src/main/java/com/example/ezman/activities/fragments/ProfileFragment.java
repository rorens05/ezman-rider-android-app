package com.example.ezman.activities.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ezman.R;
import com.example.ezman.libraries.GlobalVariables;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    TextView name;
    TextView email;
    TextView contact;
    TextView address;
    TextView gender;
    ImageView image;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");
        name = getView().findViewById(R.id.p_name);
        email = getView().findViewById(R.id.p_email);
        contact = getView().findViewById(R.id.p_contact);
        address = getView().findViewById(R.id.p_address);
        gender = getView().findViewById(R.id.p_gender);
        image = getView().findViewById(R.id.imageView3);

        name.setText(GlobalVariables.rider.name);
        email.setText(GlobalVariables.rider.email);
        contact.setText(GlobalVariables.rider.contact_no);
        address.setText(GlobalVariables.rider.address);
        gender.setText(GlobalVariables.rider.gender);

        if (GlobalVariables.rider.image.equalsIgnoreCase("")) {
            image.setBackgroundResource(R.drawable.logo);
        }else{
            Picasso.get()
                    .load(GlobalVariables.rider.image)
                    .resize(500, 500)
                    .centerCrop()
                    .into(image);
        }
    }
}
