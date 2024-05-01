package com.example.fype_comsaler.UserActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.fype_comsaler.Models.CartModel;
import com.example.fype_comsaler.Models.PostModel;
import com.example.fype_comsaler.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;
import java.util.UUID;

public class DetailActivity extends AppCompatActivity {
    PostModel postModel;
    LinearLayout addToCart;
    TextView price, title, description;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        price = findViewById(R.id.price);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        img = findViewById(R.id.imgProduct);
        addToCart = findViewById(R.id.addToCart);

        Intent intent = getIntent();
        postModel = (PostModel) intent.getSerializableExtra("model");

        title.setText(postModel.getName());
        description.setText(postModel.getDescription());
        price.setText(postModel.getPrice());
        Glide.with(img.getContext()).load(postModel.getImg()).into(img);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showBottomSheet();
//                adddToCart();
            }
        });


    }

    private void showBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(DetailActivity.this).inflate(R.layout.bottom_sheet_layout
                , (LinearLayout) findViewById(R.id.bottom_sheet_lay), false);
        bottomSheetDialog.setContentView(view);

        TextView qty = view.findViewById(R.id.qty);
        Button btn = view.findViewById(R.id.continu);

        bottomSheetDialog.show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = qty.getText().toString();
                adddToCart(quantity);
            }
        });

    }

    private void adddToCart(String qty) {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Adding");
        progressDialog.setMessage("Item in Cart");
        progressDialog.show();

        String cartId = UUID.randomUUID().toString();

        CartModel cartModel = new CartModel(cartId,postModel.getName(), postModel.getImg(),
                postModel.getPrice(), qty, FirebaseAuth.getInstance().getUid(),null);
//        FirebaseFirestore.getInstance().collection("Cart").document(cartId).set(cartModel);

        FirebaseDatabase.getInstance().getReference().child("Cart").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(cartId).setValue(cartModel)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.cancel();
                        Toast.makeText(DetailActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();

                    }
                });


//        Toast.makeText(DetailActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();

    }
}