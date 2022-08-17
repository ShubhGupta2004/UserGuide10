package com.example.userguide10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.OkHttpClient;import okhttp3.Request;
import okhttp3.Response;


public class moreDetailActivity extends AppCompatActivity {
    TextView name,distance,address;
    public static ImageView img;
    public static ImageView img1;
    public static ImageView img2;
    public static ImageView img3;

    Button map;
    public static String url1;
    public static ArrayList<String> arr = new ArrayList<>();
    public static ArrayList<String> st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_detail);
        name=findViewById(R.id.more_detail_name);
        distance=findViewById(R.id.more_detail_distance);
        address=findViewById(R.id.more_detail_add);
        img=findViewById(R.id.more_detail_image);
        img1=findViewById(R.id.more_detail_image1);
        img2=findViewById(R.id.more_detail_image2);
        img3=findViewById(R.id.more_detail_image3);
        map=findViewById(R.id.more_detail_map);



        st = getIntent().getStringArrayListExtra("frag1");

        name.setText("Name: "+st.get(0));
        distance.setText("Distance (in meters): " +st.get(3));
        address.setText("Address: "+st.get(4));
        url1="https://api.foursquare.com/v3/places/"+st.get(1)+"/photos";
        background bg = new background();
        bg.execute();

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.google.com/maps/search/?api=1&query="+st.get(5)+","+st.get(6);
                localMap(url);
            }
        });
    }

    public static class background extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            String res="";

            Request request = new Request.Builder()
                    .url(url1)
                    .get()
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "fsq3w6+LNbjowUkV13FSllJnBY+XU/fZiqWrmW8bPc2Cywg=")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                res= Objects.requireNonNull(response.body()).string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("main",res);
            return res;
        }

        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);
            Log.d("main1",unused);
            try {
                JSONArray arr1 = new JSONArray(unused);
                for(int i=0;i<arr1.length();i++){
                    JSONObject obj1 = arr1.getJSONObject(i);
                    arr.add(obj1.getString("prefix")+"600x600"+obj1.getString("suffix"));
                }
                Log.d("main1",arr.toString());
                if(arr.size()>=4) {
                    Picasso.get().load(arr.get(0)).into(img);
                    Picasso.get().load(arr.get(1)).into(img1);
                    Picasso.get().load(arr.get(2)).into(img2);
                    Picasso.get().load(arr.get(3)).into(img3);
                }else if(arr.size()>=3){
                    Picasso.get().load(arr.get(0)).into(img);
                    Picasso.get().load(arr.get(1)).into(img1);
                    Picasso.get().load(arr.get(2)).into(img2);
                }else {
                    Picasso.get().load(arr.get(0)).into(img);
                    Picasso.get().load(arr.get(1)).into(img1);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void localMap(String url) {
        Uri u = Uri.parse(url);
        Intent in = new Intent(Intent.ACTION_VIEW,u);
        startActivity(in);
    }
}