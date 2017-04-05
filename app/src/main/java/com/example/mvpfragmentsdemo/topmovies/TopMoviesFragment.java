package com.example.mvpfragmentsdemo.topmovies;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mvpfragmentsdemo.R;
import com.example.mvpfragmentsdemo.root.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Arrays.asList;


public class TopMoviesFragment extends Fragment implements TopMoviesFragmentMVP.View {
    private OnMovieTitleClickedListener callback;
    private ArrayList<ViewModel> moviesList = new ArrayList<>();
    private ArrayAdapter<ViewModel> arrayAdapter;

    public interface OnMovieTitleClickedListener {
        void onMovieTitleClicked(String movieTitle);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            callback = (OnMovieTitleClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnListItemClickedListener");
        }
    }

    @BindView(R.id.lv_topmovies)
    ListView lvTopMovies;

    @Inject
    TopMoviesFragmentMVP.Presenter presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((App) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.rxUnsubscribe();
        moviesList.clear();
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_top_movies, container, false);

        ButterKnife.bind(this,view);

        arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,moviesList);
        lvTopMovies.setAdapter(arrayAdapter);

        lvTopMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String clickedMovieTitle = lvTopMovies.getItemAtPosition(position).toString();
                callback.onMovieTitleClicked(clickedMovieTitle);
            }
        });

        return view;
    }

    @Override
    public void updateData(ViewModel viewModel) {
        moviesList.add(viewModel);
        arrayAdapter.notifyDataSetChanged();

    }
}
