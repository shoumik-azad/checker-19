package com.example.checker19;

import com.example.checker19.model.GoogleResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("place/nearbysearch/json")
    Call<GoogleResponse> getGoogleResponse(@QueryMap Map<String, String> params);


}
