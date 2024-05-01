package com.example.fype_comsaler.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fype_comsaler.Models.FirebaseOrderModel;
import com.example.fype_comsaler.Models.OrderHistoryModel;
import com.example.fype_comsaler.R;

import java.util.ArrayList;

public class OrderHistoryAdopter extends RecyclerView.Adapter<OrderHistoryAdopter.ViewHolder> {

    Context context;
    ArrayList<OrderHistoryModel> info;

    public OrderHistoryAdopter(Context context, ArrayList<OrderHistoryModel> info) {
        this.context = context;
        this.info = info;
    }

    @NonNull
    @Override
    public OrderHistoryAdopter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_history_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryAdopter.ViewHolder holder, int position) {

        OrderHistoryModel userProfileModel = info.get(position);


        holder.name.setText(userProfileModel.getProductName());
        holder.number.setText(userProfileModel.getOrderNo());
        holder.price.setText(userProfileModel.getProductPrice());
        holder.orderStatus.setText(userProfileModel.getCartId());

    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView name, number, price, orderStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            name = itemView.findViewById(R.id.namedata);
//            username = itemView.findViewById(R.id.username);
//            password = itemView.findViewById(R.id.password);
//            storename = itemView.findViewById(R.id.storename);
//            email =itemView.findViewById(R.id.email);


            name= itemView.findViewById(R.id.customerName);
            number = itemView.findViewById(R.id.orderNumber);
            price = itemView.findViewById(R.id.pricem);
            orderStatus= itemView.findViewById(R.id.orderStatus);


        }
    }
}
