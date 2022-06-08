package com.example.globallogictest.network;

import com.example.globallogictest.data.models.ItemsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("list")
    Call<ArrayList<ItemsModel>> getItems();

}
