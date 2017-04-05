package com.example.mvpfragmentsdemo.details;

import com.example.mvpfragmentsdemo.topmovies.*;
import com.example.mvpfragmentsdemo.topmovies.ViewModel;

import rx.Observable;

/**
 * Created by laurent on 4/3/17.
 */

public interface DetailsFragmentMVP {
    interface View {
        void updateData(com.example.mvpfragmentsdemo.topmovies.ViewModel viewModel);
    }

    interface Presenter {
        void loadData();
        void rxUnsubscribe();
        void setView(DetailsFragmentMVP.View view);
    }

    interface Model {
        Observable<ViewModel> result();
    }
}
