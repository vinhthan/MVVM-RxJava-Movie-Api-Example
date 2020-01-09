package com.example.mvvmrxjavamovieappexamplell3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mvvmrxjavamovieappexamplell3.adapter.MovieAdapter;
import com.example.mvvmrxjavamovieappexamplell3.model.Movies;
import com.example.mvvmrxjavamovieappexamplell3.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Movies.Results> mList;
    private MovieAdapter mAdapter;

    private CompositeDisposable compositeDisposable;
    private MovieViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compositeDisposable = new CompositeDisposable();
        model = new MovieViewModel();
        getMovies();

    }

    private void getMovies() {
        recyclerView = findViewById(R.id.recyclerView);
        mList = new ArrayList<>();
        mAdapter = new MovieAdapter(mList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        compositeDisposable.add(model.moviesBehaviorSubject.share()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(movieCall ->{
            mList.addAll(movieCall.getResults());
            if (mAdapter!=null){
                mAdapter.notifyDataSetChanged();
            }
        }));
        model.getMovies();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
