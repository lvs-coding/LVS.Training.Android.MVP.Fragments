package com.example.mvpfragmentsdemo.topmovies;

import android.util.Log;

import com.example.mvpfragmentsdemo.network.apimodeltopmovies.Result;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

public class TopMoviesModel implements TopMoviesFragmentMVP.Model{
    private Repository repository;

    public TopMoviesModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<ViewModel> result() {
        return repository.getResultData().map(new Func1<Result, ViewModel>() {
            @Override
            public ViewModel call(Result result) {
                return new ViewModel(result.getTitle());
            }
        });
    }
}
