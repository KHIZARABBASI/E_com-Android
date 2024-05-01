package com.example.fype_comsaler.UserActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fype_comsaler.Adopter.CartAdopter;
import com.example.fype_comsaler.Models.CartModel;
import com.example.fype_comsaler.Models.ProfilePostsInterface;
import com.example.fype_comsaler.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class CartActivity extends AppCompatActivity {

    DatabaseReference mReference;
    String userId;
    CartAdopter cartAdopter;
    ArrayList<CartModel> arrCart;
    public static ArrayList<CartModel> cartItemList;
    RecyclerView cartRecycler;
    TextView proceed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        cartRecycler = findViewById(R.id.cartRecycler);
        cartRecycler.setHasFixedSize(true);
        mReference= FirebaseDatabase.getInstance().getReference().child("Cart").child(userId);
        arrCart= new ArrayList<>();
        cartAdopter = new CartAdopter(this,arrCart);
        cartRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        cartRecycler.setAdapter(cartAdopter);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    CartModel cartModel = dataSnapshot.getValue(CartModel.class);
                    arrCart.add(cartModel);
//                    Toast.makeText(CartActivity.this, "name +"+ cartModel.getProductName() , Toast.LENGTH_SHORT).show();

                }

                cartAdopter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        proceed = findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItemList= cartAdopter.getSelectedItem();
//                Toast.makeText(CartActivity.this, "Size " + cartItemList.size(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CartActivity.this, OrderPlacingActivity.class));

            }
        });



    }




}