package com.example.userguide10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentActivity_4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentActivity_4 extends Fragment {

    /// TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static BlankFrangmentActivity_2 newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        BlankFrangmentActivity_2 fragment = new BlankFrangmentActivity_2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_activity_4, container, false);
        TextView textView = (TextView) view;
        String text = "Fragment #" + mPage+"123";
        textView.setText(text);
        return view;
    }
}