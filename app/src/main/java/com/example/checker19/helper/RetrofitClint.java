package com.example.checker19.helper;

import static com.example.checker19.BuildConfig.DEBUG;

import com.example.checker19.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClint {
    private static final String BASE_URL = "https://istlem.com/api/v1/";

    private static OkHttpClient provideClient() {


        HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor();
        mLoggingInterceptor.setLevel(DEBUG ? HttpLoggingInterceptor.Level.BODY :
                HttpLoggingInterceptor.Level.NONE);
        OkHttpClient.Builder client = new OkHttpClient.Builder();


        return client
                .addInterceptor(mLoggingInterceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .build();
                    return chain.proceed(request);
                })
                .build();
    }

    private static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static ApiService getApiService() {
        return provideRetrofit().create(ApiService.class);
    }

}

