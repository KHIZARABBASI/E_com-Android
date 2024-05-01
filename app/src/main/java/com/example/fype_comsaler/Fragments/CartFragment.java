package com.example.fype_comsaler.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fype_comsaler.Adopter.AllPostAdopter;
import com.example.fype_comsaler.Models.PostModel;
import com.example.fype_comsaler.Models.ProfilePostsInterface;
import com.example.fype_comsaler.Models.UserProfileModel;
import com.example.fype_comsaler.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.function.Consumer;


public class CartFragment extends Fragment implements ProfilePostsInterface {
    RecyclerView postRecycler;
    ArrayList<PostModel> arrPost;
    AllPostAdopter postAdopter;
    DatabaseReference mReference;
    String userId;

    public CartFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cart, container, false);

        /*
        postRecycler = view.findViewById(R.id.post);
        arrPost = new ArrayList<>();

        postAdopter = new AllPostAdopter(getContext(),arrPost,this);

        postRecycler.setHasFixedSize(true);
        postRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mReference =
                FirebaseDatabase.getInstance().getReference().child("Admin").child("Users");

        postRecycler.setAdapter(postAdopter);

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
*/

        return  view;
    }

    @Override
    public void onUserClick(PostModel postModel) {

    }
}