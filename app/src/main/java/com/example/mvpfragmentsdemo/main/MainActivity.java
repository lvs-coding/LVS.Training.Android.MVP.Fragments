package com.example.mvpfragmentsdemo.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.mvpfragmentsdemo.R;
import com.example.mvpfragmentsdemo.details.DetailsFragment;
import com.example.mvpfragmentsdemo.topmovies.TopMoviesFragment;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements TopMoviesFragment.OnMovieTitleClickedListener{

    @BindView(R.id.fl_fragment_container)
    FrameLayout flContainer;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showActionBar(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fl_fragment_container);
        if (fragment == null) {
            fragment = new TopMoviesFragment();
            fm.beginTransaction()
                    .add(R.id.fl_fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onMovieTitleClicked(String clickedMovieTitle) {
        Log.d("TAG",clickedMovieTitle);

        Bundle bundle = new Bundle();
        bundle.putString("clickedMovieTitle", clickedMovieTitle);

        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);

        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_fragment_container, detailsFragment)
                .addToBackStack(null)
                .commit();
        showActionBar(true);
    }

    // Show / Hide return button depending on the visible fragment
    private void showActionBar(boolean show) {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            if(show) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                return;
            }
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }
}
