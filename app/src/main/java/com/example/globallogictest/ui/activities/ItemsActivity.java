package com.example.globallogictest.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.globallogictest.data.models.ItemsModel;
import com.example.globallogictest.R;
import com.example.globallogictest.adapters.ItemsRecyclerAdapter;
import com.example.globallogictest.databinding.ActivityItemsBinding;
import com.example.globallogictest.interfaces.ItemsInterface;
import com.example.globallogictest.ui.viewmodels.ViewModel;

import java.util.ArrayList;

public class ItemsActivity extends AppCompatActivity implements ItemsInterface {

    private ArrayList<ItemsModel> arrayListItems = new ArrayList<>();

    private ItemsRecyclerAdapter adapterItemsRecycler;

    private ActivityItemsBinding binding;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        getItems();

        viewModel.getItemsLiveDataObserver().observe(this, response -> {
            binding.progressBar.setVisibility(View.GONE);

            if(response != null && !response.isEmpty()) {
                arrayListItems = response;
                showItems();
            }
            else{
                Toast toast = Toast.makeText(this, getResources().getString(R.string.service_error), Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
    }

    private void getItems() {
        binding.progressBar.setVisibility(View.VISIBLE);
        viewModel.getItems();
    }

    private void showItems() {
        adapterItemsRecycler = new ItemsRecyclerAdapter(this, this, arrayListItems);
        binding.recyclerItems.setLayoutManager(new LinearLayoutManager(ItemsActivity.this));
        binding.recyclerItems.setAdapter(adapterItemsRecycler);
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(this, ItemDetailsActivity.class);
        intent.putExtra("title", arrayListItems.get(position).title);
        intent.putExtra("description", arrayListItems.get(position).description);
        intent.putExtra("imageUrl", arrayListItems.get(position).image);
        startActivity(intent);
    }
}