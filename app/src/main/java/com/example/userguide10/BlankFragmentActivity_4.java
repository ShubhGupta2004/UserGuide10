package com.example.userguide10;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentActivity_4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentActivity_4 extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    public static String url1 = "";
    public static String resp;
    public static GridView list;
    public static  CustomAdapter customAdapter;

    private int mPage;
    public static Context thisContext;

    public static BlankFragmentActivity_4 newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        BlankFragmentActivity_4 fragment = new BlankFragmentActivity_4();
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

        View view = inflater.inflate(R.layout.fragment_blank_activity_4, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text_fragment4);
        afterLoginActivity act = (afterLoginActivity) getActivity();
        assert act != null;
        String text = "Health ";
        textView.setText(text);
        url1="https://api.foursquare.com/v3/places/search?ll="+act.getLatitudeText()+"%2C"+act.getLongitudeText()+"&radius=100000&categories=15000&sort=DISTANCE&limit=39";
        list = (GridView) view.findViewById(R.id.list_fragment4);

        BlankFragmentActivity_4.BackTask backTask = new BlankFragmentActivity_4.BackTask();
        backTask.execute();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CustomJavaClass customJavaClass = (CustomJavaClass) customAdapter.getItem(i);
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(customJavaClass.getName());
                arrayList.add(customJavaClass.getFsqId());
                arrayList.add(customJavaClass.getImgLink());
                arrayList.add(customJavaClass.getDistance());
                arrayList.add(customJavaClass.getAddress());
                arrayList.add(customJavaClass.getLatitude());
                arrayList.add(customJavaClass.getLongitude());

                Intent intent = new Intent(getActivity(),moreDetailActivity.class);
                intent.putStringArrayListExtra("frag1",arrayList);
                startActivity(intent);
            }
        });
        return view;
    }
    public static class BackTask extends AsyncTask<String,Void,String> {

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
            ArrayList<CustomJavaClass> arr = DataExtracter.extractData(s);
            customAdapter = new CustomAdapter(thisContext, R.layout.custom_layout,arr);
            list.setAdapter(customAdapter);
        }
    }
}