package com.example.fype_comsaler.Adopter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fype_comsaler.Models.FirebaseOrderModel;
import com.example.fype_comsaler.Models.PostModel;
import com.example.fype_comsaler.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FirebaseHistoryAdopter extends FirebaseRecyclerAdapter<FirebaseOrderModel, FirebaseHistoryAdopter.ViewHolder> {

    public FirebaseHistoryAdopter(@NonNull FirebaseRecyclerOptions<FirebaseOrderModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FirebaseHistoryAdopter.ViewHolder viewHolder, int i, @NonNull FirebaseOrderModel firebaseOrderModel) {
//        viewHolder.name.setText(firebaseOrderModel.getcN());
//        viewHolder.price.setText(firebaseOrderModel.getPrice());
//        viewHolder.ordernumber.setText(firebaseOrderModel.getOrderNum());
        Toast.makeText(viewHolder.itemView.getContext(), "new "+ firebaseOrderModel.getPrice(),
                Toast.LENGTH_SHORT).show();

    }

    @NonNull
    @Override
    public FirebaseHistoryAdopter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_row_layout
                ,parent,false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,price, ordernumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameItemorder);
            price = itemView.findViewById(R.id.priceItemorder);
            ordernumber = itemView.findViewById(R.id.numberItemorder);
        }
    }
}
/*
*
* public FirebaseHistoryAdopter(@NonNull FirebaseRecyclerOptions<PostModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FirebaseHistoryAdopter.ViewHolder viewHolder, int i, @NonNull PostModel postModel) {

//        PostModel postModel= arrPost.get(position);
//        viewHolder.profilePic.setImageURI(postModel.getImg());
        viewHolder.name.setText(postModel.getName());
        viewHolder.price.setText(postModel.getPrice());
        viewHolder.ordernumber.setText(postModel.getDescription());
    }

    @NonNull
    @Override
    public FirebaseHistoryAdopter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_row_layout
                ,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {

        TextView name,price, ordernumber;
//        ImageView profilePic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameItemorder);
            price = itemView.findViewById(R.id.priceItemorder);
            ordernumber = itemView.findViewById(R.id.numberItemorder);
//            profilePic = itemView.findViewById(R.id.pPic);
        }
    }
*
*
*
*
* */
