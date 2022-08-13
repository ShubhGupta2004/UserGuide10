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
                String s = obj2.getString("name").toString();
                JSONArray arr2 = obj2.getJSONArray("categories");
                JSONObject obj3 = arr2.getJSONObject(0).getJSONObject("icon");
                String img = obj3.getString("prefix")+"bg_120.png";


                list.add(new CustomJavaClass(s," ",img," "," "," "," "));
            }
            Log.d("main",JsonResponse+" response");
        }catch (Exception e){
            Log.d("main",e.getMessage());
        }
        return list;
    }
}
