package com.example.fype_comsaler.UserActivity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fype_comsaler.Adopter.FirebaseHistoryAdopter;
import com.example.fype_comsaler.Adopter.OrderHistoryAdopter;
import com.example.fype_comsaler.Models.FirebaseOrderModel;
import com.example.fype_comsaler.Models.OrderHistoryModel;
import com.example.fype_comsaler.Models.UserProfileModel;
import com.example.fype_comsaler.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView orderHistory;
    DatabaseReference mReference;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String userId;
    OrderHistoryAdopter orderHistoryAdopter;
    ArrayList<OrderHistoryModel> arrFirebaseOrderHistory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        orderHistory = findViewById(R.id.orderHistory);
        orderHistory.setHasFixedSize(true);
        orderHistory.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        mReference =
                FirebaseDatabase.getInstance().getReference().child("OrderProduct").child(userId);
        arrFirebaseOrderHistory = new ArrayList<>();



        orderHistoryAdopter = new OrderHistoryAdopter(this, arrFirebaseOrderHistory);
        orderHistory.setAdapter(orderHistoryAdopter);
        final int[] count = {0};

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    OrderHistoryModel userProfileModel = dataSnapshot.getValue(OrderHistoryModel.class);
                    arrFirebaseOrderHistory.add(userProfileModel);
                    count[0] = count[0] +1;
                    assert userProfileModel != null;
                    Toast.makeText(HistoryActivity.this, "price"+ userProfileModel.getOrderNo(),
                            Toast.LENGTH_SHORT).show();
                }
                orderHistoryAdopter.notifyDataSetChanged();
                Toast.makeText(HistoryActivity.this, "count: " + count[0], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//        FirebaseRecyclerOptions<FirebaseOrderModel> options =
//                new FirebaseRecyclerOptions.Builder<FirebaseOrderModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child(
//                                        "Orders").child(userId),
//                                FirebaseOrderModel.class).build();
//
//        firebaseHistoryAdopter = new FirebaseHistoryAdopter(options);
//        orderHistory.setAdapter(firebaseHistoryAdopter);
//        firebaseHistoryAdopter.startListening();







//



    }
}