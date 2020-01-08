package com.example.mvvmrxjavamovieappexamplell3.api;

import com.example.mvvmrxjavamovieappexamplell3.model.Movies;

import io.reactivex.Observable;

public class ApiManager {
    private ApiInterface apiInterface;

    public ApiManager() {
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
    }

    public Observable<Movies> getMovie(String API_KEY){
        return apiInterface.getMovies(API_KEY);//reactive.io
    }
}
