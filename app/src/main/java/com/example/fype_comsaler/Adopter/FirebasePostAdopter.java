package com.example.fype_comsaler.Adopter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fype_comsaler.Models.PostModel;
import com.example.fype_comsaler.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FirebasePostAdopter extends FirebaseRecyclerAdapter<PostModel, FirebasePostAdopter.ViewHolder> {
    public FirebasePostAdopter(@NonNull FirebaseRecyclerOptions<PostModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FirebasePostAdopter.ViewHolder viewHolder, int i, @NonNull PostModel postModel) {

//        PostModel postModel= arrPost.get(position);
//        viewHolder.profilePic.setImageURI(postModel.getImg());
        viewHolder.name.setText(postModel.getName());
        viewHolder.price.setText(postModel.getPrice());
        viewHolder.description.setText(postModel.getDescription());
    }

    @NonNull
    @Override
    public FirebasePostAdopter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_profile_post_row
                ,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {

        TextView name,price, description;
        ImageView profilePic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameItem);
            price = itemView.findViewById(R.id.priceItem);
            description = itemView.findViewById(R.id.descriptionItem);
            profilePic = itemView.findViewById(R.id.pPic);
        }
    }
}
