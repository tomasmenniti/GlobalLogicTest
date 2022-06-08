package com.example.globallogictest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.globallogictest.data.models.ItemsModel;
import com.example.globallogictest.R;
import com.example.globallogictest.databinding.ItemRecyclerItemsBinding;
import com.example.globallogictest.interfaces.ItemsInterface;

import java.util.ArrayList;

public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder>{

    private Context context;
    private ItemsInterface itemsInterface;
    private ArrayList<ItemsModel> arrayListItems;

    public ItemsRecyclerAdapter(Context context, ItemsInterface itemsInterface, ArrayList<ItemsModel> arrayListItems){
        this.context = context;
        this.itemsInterface = itemsInterface;
        this.arrayListItems = arrayListItems;
    }

    @NonNull
    @Override
    public ItemsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_items,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsRecyclerAdapter.ViewHolder holder, int position) {
        String title = arrayListItems.get(position).title;
        String description = arrayListItems.get(position).description;
        String imageUrl = arrayListItems.get(position).image;

        holder.binding.tvItemTitle.setText(title);
        holder.binding.tvItemDescription.setText(description);

        if (!imageUrl.isEmpty()) {
            Glide.with(context)
                    .load(imageUrl)
                    .centerCrop()
                    .into(holder.binding.imgItem);
        } else {
            Glide.with(context)
                    .load("https://www.optiportals.com/AsobcdOnline/App_Themes/Sibu/img/noimage.png")
                    .centerCrop()
                    .into(holder.binding.imgItem);
        }


        holder.binding.constraintItem.setOnClickListener(view -> itemsInterface.onItemClicked(position));
    }

    @Override
    public int getItemCount() {
        return arrayListItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecyclerItemsBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemRecyclerItemsBinding.bind(itemView);
        }
    }
}
