package com.example.mvpfragmentsdemo.details;

import rx.Observable;


public interface DetailsFragmentMVP {
    interface View {
        void updateData(ViewModel viewModel);
    }

    interface Presenter {
        void loadData(String clickedMovieTitle);
        void rxUnsubscribe();
        void setView(DetailsFragmentMVP.View view);
    }

    interface Model {
        Observable<ViewModel> result(String movieTitle);
    }
}
