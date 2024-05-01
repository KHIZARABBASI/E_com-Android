package com.example.fype_comsaler.UserActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fype_comsaler.Adopter.UserProfileAdopter;
import com.example.fype_comsaler.Models.UserProfileModel;
import com.example.fype_comsaler.Models.OnUserClickListener;
import com.example.fype_comsaler.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements OnUserClickListener {
    RecyclerView allProfiles;
    DatabaseReference database;
    UserProfileAdopter adopter;
    ArrayList<UserProfileModel> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        allProfiles = findViewById(R.id.allProfiles);
        allProfiles.setHasFixedSize(true);
        database = FirebaseDatabase.getInstance().getReference().child("Admin").child("Users");
        info= new ArrayList<>();
        adopter = new UserProfileAdopter(this, info,this);

        allProfiles.setLayoutManager(new GridLayoutManager(this, 4));

        allProfiles.setAdapter(adopter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UserProfileModel userProfileModel = dataSnapshot.getValue(UserProfileModel.class);
                    info.add(userProfileModel);
                }
                adopter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onUserClick(UserProfileModel userData) {
        // Display user data in dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("User Details");
        builder.setMessage("User ID: " + userData.getUserId() +
                "\nName: " + userData.getName() +
                "\nUsername: " + userData.getUsername() +
                "\nStore Name: " + userData.getStoreName() +
                "\nEmail: " + userData.getEmail() );
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}