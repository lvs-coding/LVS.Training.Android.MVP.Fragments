package com.example.mvpfragmentsdemo.details;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvpfragmentsdemo.R;
import com.example.mvpfragmentsdemo.root.App;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsFragment extends Fragment implements DetailsFragmentMVP.View {
    private final String TAG = DetailsFragment.class.getSimpleName();
    private ViewModel movieDetails;

    private String clickedMovieTitle = "";

    @BindView(R.id.tv_movie_title)
    TextView tvMovieTitle;

    @BindView(R.id.tv_movie_year)
    TextView tvMovieYear;

    @BindView(R.id.tv_movie_country)
    TextView tvMovieCountry;

    @BindView(R.id.tv_movie_genre)
    TextView tvMovieGenre;

    @BindView(R.id.tv_movie_imdb_rating)
    TextView tvImdbRating;

    @Inject
    DetailsFragmentMVP.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_details, container, false);

        ButterKnife.bind(this,view);

        Bundle bundle = this.getArguments();
        clickedMovieTitle = bundle.getString("clickedMovieTitle");

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((App) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadData(clickedMovieTitle);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.rxUnsubscribe();
    }

    @Override
    public void updateData(ViewModel viewModel) {
        this.movieDetails = viewModel;
        tvMovieTitle.setText(movieDetails.getTitle());
        tvMovieYear.setText(movieDetails.getYear());
        tvMovieCountry.setText(movieDetails.getCountry());
        tvMovieGenre.setText(movieDetails.getGenre());
        tvImdbRating.setText(movieDetails.getImdbRating());

    }
}
