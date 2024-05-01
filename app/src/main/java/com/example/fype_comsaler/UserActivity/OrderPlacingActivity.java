package com.example.fype_comsaler.UserActivity;

import static com.example.fype_comsaler.UserActivity.CartActivity.cartItemList;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fype_comsaler.Models.CartModel;
import com.example.fype_comsaler.Models.OrderModel;
import com.example.fype_comsaler.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

public class OrderPlacingActivity extends AppCompatActivity {
    String userId;
    int mainTotal = 0;
    DatabaseReference mReferance;
    TextView expance, delivery, totalCod;
    EditText name_e, number_e, adress_e, cityName_e, postalCode_e;
    String name, number, adress, cityName, postalCode;
    CardView placeOrder;
    OrderModel orderModel;
    ArrayList<OrderModel> arrOrderModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_placing);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        expance = findViewById(R.id.expance);
        delivery = findViewById(R.id.delivery);
        totalCod = findViewById(R.id.totalCod);
        placeOrder = findViewById(R.id.place_order);

        name_e = findViewById(R.id.name_order);
        number_e = findViewById(R.id.phone_order);
        adress_e = findViewById(R.id.adrees_ordder);
        cityName_e = findViewById(R.id.city_order);
        postalCode_e = findViewById(R.id.zip_order);

        userId = FirebaseAuth.getInstance().getUid();
        mReferance = FirebaseDatabase.getInstance().getReference();

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = name_e.getText().toString();
                number = number_e.getText().toString();
                adress = adress_e.getText().toString();
                cityName = cityName_e.getText().toString();
                postalCode = postalCode_e.getText().toString();

//                Toast.makeText(OrderPlacingActivity.this, "placesd", Toast.LENGTH_SHORT).show();
                placeOrder();
            }
        });


    }

    public static String getRendomNo(int min, int max) {

        return String.valueOf((new Random().nextInt(min - max) + 1) + min);
    }
    public static String getRandomNoAsString(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min; // Generates a random number between min and max (inclusive)
        return String.valueOf(randomNumber); // Converts the random number to a string
    }


    private void placeOrder() {

        String orderNumber = getRandomNoAsString(100000,999999);

        Toast.makeText(this, "loaded : "+orderNumber, Toast.LENGTH_SHORT).show();

        orderModel = new OrderModel(orderNumber,name,number,cityName,adress,
                String.valueOf(expance.getText().toString()),"200",null,"self",
                String.valueOf(Calendar.getInstance().getTimeInMillis()),"pending");
//
        mReferance.child("Orders").child(userId).child(orderNumber).setValue(orderModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(OrderPlacingActivity.this, "placed",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        for (int i = 0; i< cartItemList.size(); i++){
            CartModel cartModel = cartItemList.get(i);
            cartModel.setOrderNo(orderNumber);

            String id= UUID.randomUUID().toString();

            mReferance.child("OrderProduct").child(userId).child(orderNumber).setValue(cartModel)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(OrderPlacingActivity.this, "Order", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }







    @Override
    protected void onStart() {
        super.onStart();

        for (int i = 0; i < cartItemList.size(); i++) {

            CartModel cartModel = cartItemList.get(i);
            int price = Integer.parseInt(cartModel.getProductPrice());
            int qty = Integer.parseInt(cartModel.getProductQty());
            int total = price * qty;
            mainTotal = total + mainTotal;
        }

        expance.setText(String.valueOf(mainTotal));
        delivery.setText(String.valueOf(200));
        totalCod.setText(String.valueOf(mainTotal + 200));


    }
}