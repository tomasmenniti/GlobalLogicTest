package com.example.globallogictest.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;

import com.example.globallogictest.data.models.ItemsModel;
import com.example.globallogictest.network.APIService;
import com.example.globallogictest.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel extends androidx.lifecycle.ViewModel {

    private MutableLiveData<ArrayList<ItemsModel>> getItemsLiveData;

    public ViewModel(){
        getItemsLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<ItemsModel>> getItemsLiveDataObserver() {
        return getItemsLiveData;
    }

    public void getItems() {
        APIService apiService = RetrofitInstance.getRetroClient().create(APIService.class);
        Call<ArrayList<ItemsModel>> call = apiService.getItems();
        call.enqueue(new Callback<ArrayList<ItemsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemsModel>> call, Response<ArrayList<ItemsModel>> response) {
                if(response.isSuccessful()){
                    getItemsLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemsModel>> call, Throwable t) {
                getItemsLiveData.postValue(null);
            }
        });
    }
}
