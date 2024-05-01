package com.example.fype_comsaler.Adopter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fype_comsaler.Models.PostModel;
import com.example.fype_comsaler.Models.ProfilePostsInterface;
import com.example.fype_comsaler.R;
import com.example.fype_comsaler.UserActivity.DetailActivity;

import java.util.ArrayList;

public class AllPostAdopter extends RecyclerView.Adapter<AllPostAdopter.ViewHolder> {

    final ProfilePostsInterface listner;
    Context context;
    ArrayList<PostModel> arrPost;


    public AllPostAdopter(Context context, ArrayList<PostModel> arrPost,ProfilePostsInterface listner) {
        this.listner = listner;
        this.context = context;
        this.arrPost = arrPost;
    }

    @NonNull
    @Override
    public AllPostAdopter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_profile_post_row,parent,false);

        return new ViewHolder(view, listner);
    }

    @Override
    public void onBindViewHolder(@NonNull AllPostAdopter.ViewHolder holder, int position) {

        PostModel postModel= arrPost.get(position);

        Glide.with(holder.profilePic.getContext()).load(postModel.getImg()).into(holder.profilePic);
        holder.name.setText(postModel.getName());
        holder.price.setText(postModel.getPrice());
        holder.description.setText(postModel.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("model",postModel);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price, description;
        ImageView profilePic;

        public ViewHolder(@NonNull View itemView, ProfilePostsInterface listner) {
            super(itemView);
            name = itemView.findViewById(R.id.nameItem);
            price = itemView.findViewById(R.id.priceItem);
            description = itemView.findViewById(R.id.descriptionItem);
            profilePic = itemView.findViewById(R.id.pPic);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listner != null){



                    }
                }
            });
        }
    }
}
