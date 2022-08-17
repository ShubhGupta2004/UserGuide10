package com.example.userguide10;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import com.squareup.picasso.Picasso;

public class DataExtracter {
    private DataExtracter(){

    }

    public static ArrayList<CustomJavaClass> extractData(String JsonResponse){
        ArrayList<CustomJavaClass> list = new ArrayList<>();
        try{
            JSONObject obj1 = new JSONObject(JsonResponse);
            JSONArray arr1 = obj1.getJSONArray("results");
            for(int i=0;i<arr1.length();i++){
                JSONObject obj2 = arr1.getJSONObject(i);
                //JSONObject obj3 = obj2.getJSONArray("categories").getJSONObject(0).getJSONObject("icon").getString("prefix");
                //String img = obj3.getString("prefix")+"bg_120.png";


                list.add(new CustomJavaClass(obj2.getString("name"),
                        obj2.getJSONObject("location").getString("formatted_address")+"\n"+obj2.getJSONObject("location").getString("country"),
                        obj2.getJSONArray("categories").getJSONObject(0).getJSONObject("icon").getString("prefix")+"bg_120.png",
                        obj2.getString("distance"),
                        obj2.getString("fsq_id"),
                        obj2.getJSONObject("geocodes").getJSONObject("main").getString("latitude"),
                        obj2.getJSONObject("geocodes").getJSONObject("main").getString("longitude")));
            }
            Log.d("main",JsonResponse+" response");
        }catch (Exception e){
            Log.d("main",e.getMessage());
        }
        return list;
    }
}
