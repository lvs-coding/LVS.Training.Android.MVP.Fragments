package com.example.mvpfragmentsdemo.topmovies;

import android.util.Log;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by laurent on 4/3/17.
 */

public class TopMoviesPresenter implements TopMoviesFragmentMVP.Presenter {

    private TopMoviesFragmentMVP.View view;
    private Subscription subscription = null;
    private TopMoviesFragmentMVP.Model model;

    public TopMoviesPresenter(TopMoviesFragmentMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {
        subscription = model.result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ViewModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (view != null) {
                            Log.d("ERROR","Error getting movies");
                            //view.showSnackbar("Error getting movies");
                        }
                    }

                    @Override
                    public void onNext(ViewModel viewModel) {
                        if (view != null) {
                            view.updateData(viewModel);
                        }
                    }
                });
    }

    @Override
    public void rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    @Override
    public void setView(TopMoviesFragmentMVP.View view) {
        this.view = view;
    }
}
