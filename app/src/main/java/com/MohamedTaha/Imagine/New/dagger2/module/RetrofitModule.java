package com.MohamedTaha.Imagine.New.dagger2.module;

import com.MohamedTaha.Imagine.New.dagger2.named.APIServiceBase;
import com.MohamedTaha.Imagine.New.dagger2.named.APIServiceCity;
import com.MohamedTaha.Imagine.New.dagger2.named.GSONBase;
import com.MohamedTaha.Imagine.New.dagger2.named.OkHttpBase;
import com.MohamedTaha.Imagine.New.dagger2.named.RetrofitBase;
import com.MohamedTaha.Imagine.New.dagger2.named.RetrofitCity;
import com.MohamedTaha.Imagine.New.dagger2.named.UrlBase;
import com.MohamedTaha.Imagine.New.dagger2.named.UrlCity;
import com.MohamedTaha.Imagine.New.rest.APIServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    private static final String TAG = "RetrofitModule";
    private static final String BASE_URL = "https://api.aladhan.com/v1/";
    private static final String BASE_URL_FOR_CITY = "http://ip-api.com/";

    @Provides
    @Singleton
    @UrlBase
    String provideBaseUrl() {
        return BASE_URL;
    }

    @Provides
    @Singleton
    @UrlCity
    String provideBaseUrlForCity() {
        return BASE_URL_FOR_CITY;
    }

    @Provides
    @Singleton
    @GSONBase
    Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }

    @Provides
    @Singleton
    @OkHttpBase
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS).build();
    }
    @Provides
    @Singleton
    @RetrofitBase
    Retrofit provideRetrofit(@GSONBase Gson gson, @UrlBase String base_url, @OkHttpBase OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    @RetrofitCity
    Retrofit provideRetrofitForCity(@GSONBase Gson gson, @UrlCity String base_url_for_city, @OkHttpBase OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(base_url_for_city)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    @APIServiceBase
    APIServices provideApiServices(@RetrofitBase Retrofit retrofit) {
        return retrofit.create(APIServices.class);
    }

    @Provides
    @Singleton
    @APIServiceCity
    APIServices provideApiServicesCity(@RetrofitCity Retrofit retrofit) {
               return retrofit.create(APIServices.class);
    }
}
