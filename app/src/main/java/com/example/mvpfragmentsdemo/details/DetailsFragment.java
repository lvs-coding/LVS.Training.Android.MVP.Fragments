package com.example.mvpfragmentsdemo.details;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvpfragmentsdemo.R;


public class DetailsFragment extends Fragment {
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_details, container, false);

        Bundle bundle = this.getArguments();
        String clickedMovieTitle = bundle.getString("clickedMovieTitle");

        textView = (TextView)view.findViewById(R.id.textview);
        textView.setText(String.format("%s",clickedMovieTitle));

        return view;
    }
}
