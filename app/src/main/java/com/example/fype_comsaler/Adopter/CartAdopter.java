package com.example.fype_comsaler.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fype_comsaler.Models.CartModel;
import com.example.fype_comsaler.Models.PostModel;
import com.example.fype_comsaler.Models.ProfilePostsInterface;
import com.example.fype_comsaler.R;

import java.util.ArrayList;

public class CartAdopter extends RecyclerView.Adapter<CartAdopter.ViewHolder> {


    Context context;
    ArrayList<CartModel> arrPost;


    public CartAdopter(Context context, ArrayList<CartModel> arrPost) {
        this.context = context;
        this.arrPost = arrPost;
    }

    public ArrayList<CartModel> getSelectedItem() {
        ArrayList<CartModel> cartItem = new ArrayList<>();
        for (int i=0; i<arrPost.size(); i++){
            if (arrPost.get(i).is_slected){
                cartItem.add(arrPost.get(i));
            }
        }
        return cartItem;
    }

    @NonNull
    @Override
    public CartAdopter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdopter.ViewHolder holder, int position) {

        CartModel postModel= arrPost.get(position);



        holder.name.setText(postModel.getProductName());
        holder.price.setText(String.valueOf(postModel.getProductPrice())); // Convert numerical value to string
        holder.qty.setText(String.valueOf(postModel.getProductQty())); // Convert numerical value to string

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (postModel.is_slected){
                    holder.mainCart.setBackgroundColor(context.getResources().getColor(R.color.row));
                    postModel.is_slected=false;

                }else {
                    holder.mainCart.setBackgroundColor(context.getResources().getColor(R.color.s_row));
                    postModel.is_slected=true;


                }
            }
        });
        // holder.description.setText(postModel); // You can uncomment and implement this line if needed

    }

    @Override
    public int getItemCount() {
        return arrPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price, qty;
        ImageView profilePic;
        LinearLayout mainCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameItemCart);
            price = itemView.findViewById(R.id.priceItemCart);
            qty = itemView.findViewById(R.id.quantityCart);
            profilePic = itemView.findViewById(R.id.pPicCart);
            mainCart = itemView.findViewById(R.id.main_cart_row);

        }
    }
}
