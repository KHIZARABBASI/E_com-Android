package com.example.fype_comsaler.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fype_comsaler.UserActivity.HistoryActivity;
import com.example.fype_comsaler.R;
import com.example.fype_comsaler.UserActivity.CartActivity;
import com.example.fype_comsaler.UserActivity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class AccountFragment extends Fragment {
    ImageView cart;
    TextView name,email;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FirebaseAuth mAuth;
        CardView logout,history,changePassword;



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        mAuth = FirebaseAuth.getInstance();
        logout = view.findViewById(R.id.logout);
        cart = view.findViewById(R.id.cart);
        name = view.findViewById(R.id.nameProfile);
        email = view.findViewById(R.id.emailProfile);
        history=view.findViewById(R.id.history);
        changePassword = view.findViewById(R.id.changePassword);

        name.setText(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName());
        email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));

            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CartActivity.class));
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HistoryActivity.class));
            }
        });



        return  view;

    }
}