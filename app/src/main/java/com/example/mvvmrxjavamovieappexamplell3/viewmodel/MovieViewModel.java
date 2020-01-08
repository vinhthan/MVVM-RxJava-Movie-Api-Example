package com.example.mvvmrxjavamovieappexamplell3.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.mvvmrxjavamovieappexamplell3.api.ApiClient;
import com.example.mvvmrxjavamovieappexamplell3.api.ApiInterface;
import com.example.mvvmrxjavamovieappexamplell3.api.ApiManager;
import com.example.mvvmrxjavamovieappexamplell3.model.Constants;
import com.example.mvvmrxjavamovieappexamplell3.model.Movies;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class MovieViewModel extends ViewModel {
    private ApiManager apiManager;
    private ApiInterface apiInterface;

    public BehaviorSubject<Movies> moviesBehaviorSubject;
    private CompositeDisposable compositeDisposable;

    public MovieViewModel() {
        moviesBehaviorSubject = BehaviorSubject.create();
        compositeDisposable = new CompositeDisposable();
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        apiManager = new ApiManager();
    }

    @SuppressLint("CheckResult")
    public void getMovies(){
        apiManager.getMovie(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(movie ->{
                    moviesBehaviorSubject.onNext(movie);
                }, error ->{
                    Log.d("TAG", "getMovies: " + error);

                });
    }
}
