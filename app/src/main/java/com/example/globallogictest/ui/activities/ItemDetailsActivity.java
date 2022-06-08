package com.example.globallogictest.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.globallogictest.databinding.ActivityItemDetailsBinding;

public class ItemDetailsActivity extends AppCompatActivity {

    private String title, description, imageUrl;

    private ActivityItemDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgBack.setOnClickListener(view -> onBackPressed());

        getItemDetails();
        setItemDetails();
    }

    private void getItemDetails() {
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        imageUrl = getIntent().getStringExtra("imageUrl");
    }

    private void setItemDetails() {
        binding.txtItemTitle.setText(title);
        binding.txtItemDescription.setText(description);

        if (!imageUrl.isEmpty()) {
            Glide.with(ItemDetailsActivity.this)
                    .load(imageUrl)
                    .centerCrop()
                    .into(binding.imgItem);
        } else {
            Glide.with(ItemDetailsActivity.this)
                    .load("https://www.optiportals.com/AsobcdOnline/App_Themes/Sibu/img/noimage.png")
                    .centerCrop()
                    .into(binding.imgItem);
        }

    }
}