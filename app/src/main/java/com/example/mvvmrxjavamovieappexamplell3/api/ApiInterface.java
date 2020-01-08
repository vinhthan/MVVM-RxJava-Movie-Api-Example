package com.example.mvvmrxjavamovieappexamplell3.api;

import com.example.mvvmrxjavamovieappexamplell3.model.Movies;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/popular")
    Observable<Movies> getMovies(@Query("api_key") String api_key);
}
