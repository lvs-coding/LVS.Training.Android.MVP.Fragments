package com.example.mvpfragmentsdemo.topmovies;

import rx.Observable;

/**
 * Created by laurent on 4/3/17.
 */

public interface TopMoviesFragmentMVP {
    interface View {
        void updateData(ViewModel viewModel);
    }

    interface Presenter {
        void loadData();
        void rxUnsubscribe();
        void setView(TopMoviesFragmentMVP.View view);
    }

    interface Model {
        Observable<ViewModel> result();
    }
}
