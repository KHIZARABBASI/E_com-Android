package com.example.fype_comsaler.Fragments;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fype_comsaler.Adopter.AllPostAdopter;
import com.example.fype_comsaler.Adopter.FirebasePostAdopter;
import com.example.fype_comsaler.Adopter.UserProfileAdopter;
import com.example.fype_comsaler.Models.PostModel;
import com.example.fype_comsaler.Models.ProfilePostsInterface;
import com.example.fype_comsaler.Models.UserProfileModel;
import com.example.fype_comsaler.Models.OnUserClickListener;
import com.example.fype_comsaler.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements OnUserClickListener, ProfilePostsInterface {
    RecyclerView allProfiles,allPosts;
    DatabaseReference database;
    String userId;
    UserProfileAdopter profileAdopter;
    ArrayList<UserProfileModel> info;
    ArrayList<PostModel> arrPost;
    AllPostAdopter postAdopter;
    DatabaseReference mReference;




    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        allProfiles = view.findViewById(R.id.allUsers);
        allPosts = view.findViewById(R.id.allPosts);

        allProfiles.setHasFixedSize(true);
        allPosts.setHasFixedSize(true);
        database = FirebaseDatabase.getInstance().getReference().child("Admin").child("Users");
        info= new ArrayList<>();
        profileAdopter = new UserProfileAdopter(getContext(), info, (OnUserClickListener) this);
        allProfiles.setLayoutManager(new GridLayoutManager(getContext(), 4));
        allProfiles.setAdapter(profileAdopter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UserProfileModel userProfileModel = dataSnapshot.getValue(UserProfileModel.class);
                    info.add(userProfileModel);
                }
                profileAdopter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


/////
        allPosts = view.findViewById(R.id.allPosts);
        arrPost = new ArrayList<>();

        postAdopter = new AllPostAdopter(getContext(),arrPost,this);

        allPosts.setHasFixedSize(true);
        allPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        mReference =
                FirebaseDatabase.getInstance().getReference().child("Admin").child("Users");

        allPosts.setAdapter(postAdopter);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UserProfileModel userProfileModel = dataSnapshot.getValue(UserProfileModel.class);
                    assert userProfileModel != null;
                    userId = userProfileModel.getUserId();
//                    Toast.makeText(getContext(), "user ID "+ userId, Toast.LENGTH_SHORT).show();
                    mReference.child(userId).child("Post")
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                                        PostModel postModel =
                                                dataSnapshot1.getValue(PostModel.class);
                                        arrPost.add(postModel);
                                    }
                                    postAdopter.notifyDataSetChanged();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                }

                postAdopter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
////////


        return view;
    }


    @Override
    public void onUserClick(UserProfileModel userData) {

        // Display user data in dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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

    @Override
    public void onUserClick(PostModel postModel) {

    }
}