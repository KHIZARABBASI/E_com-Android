package com.example.fype_comsaler.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fype_comsaler.Models.UserProfileModel;
import com.example.fype_comsaler.Models.OnUserClickListener;
import com.example.fype_comsaler.R;

import java.util.ArrayList;

public class UserProfileAdopter extends RecyclerView.Adapter<UserProfileAdopter.ViewHolder> {

    private final OnUserClickListener listener;
    Context context;
    ArrayList<UserProfileModel> info;

    public UserProfileAdopter(Context context, ArrayList<UserProfileModel> info, OnUserClickListener listener) {
        this.context = context;
        this.info = info;
        this.listener=listener;
    }

    @NonNull
    @Override
    public UserProfileAdopter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profiles_layout, parent, false);
        return new ViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserProfileAdopter.ViewHolder holder, int position) {

        UserProfileModel userProfileModel = info.get(position);
//        holder.name.setText(dataModel.getName());
//        holder.username.setText(dataModel.getUsername());
//        holder.storename.setText(dataModel.getStoreName());
//        holder.password.setText(dataModel.getPassword());
//        holder.email.setText(dataModel.getEmail());
        holder.name.setText(userProfileModel.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onUserClick(info.get(holder.getAdapterPosition()));

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView name, username, password, storename, email;
        public ViewHolder(@NonNull View itemView, OnUserClickListener listener) {
            super(itemView);
//            name = itemView.findViewById(R.id.namedata);
//            username = itemView.findViewById(R.id.username);
//            password = itemView.findViewById(R.id.password);
//            storename = itemView.findViewById(R.id.storename);
//            email =itemView.findViewById(R.id.email);
            name= itemView.findViewById(R.id.name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){



                        }
                    }
            });

        }
    }
}
