package com.example.userguide10;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentActivity_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentActivity_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PAGE = "ARG_PAGE";
    public static String url1 = "";
    public static String resp;
    public static ListView list;

    private int mPage;
    public static Context thisContext;

    public static BlankFragmentActivity_1 newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        BlankFragmentActivity_1 fragment = new BlankFragmentActivity_1();
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
        assert container != null;
        thisContext=container.getContext();

        View view = inflater.inflate(R.layout.fragment_blank_activity_1, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text_fragment1);
        afterLoginActivity act = (afterLoginActivity) getActivity();
        assert act != null;
        String text = "Education ";
        textView.setText(text);
        url1="https://api.foursquare.com/v3/places/search?ll="+act.getLatitudeText()+"%2C"+act.getLongitudeText()+"&radius=100000&categories=12009&sort=RATING&limit=20";
        list = (ListView) view.findViewById(R.id.list_fragment1);
        BackTask backTask = new BackTask();
        backTask.execute();
        return view;
    }

    public static class BackTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url1)
                    .get()
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "fsq3w6+LNbjowUkV13FSllJnBY+XU/fZiqWrmW8bPc2Cywg=")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                resp= Objects.requireNonNull(response.body()).string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("main",resp);
            return resp;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<String> arr = DataExtracter.extractdata(s);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(thisContext, android.R.layout.simple_list_item_1,arr);
            list.setAdapter(arrayAdapter);
        }
    }
}