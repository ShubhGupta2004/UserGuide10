package com.example.userguide10;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataExtracter {
    private DataExtracter(){

    }

    public static ArrayList<String> extractdata(String JsonResponse){
        ArrayList<String> list = new ArrayList<>();
        try{
            JSONObject obj1 = new JSONObject(JsonResponse);
            JSONArray arr1 = obj1.getJSONArray("results");
            for(int i=0;i<arr1.length();i++){
                JSONObject obj2 = arr1.getJSONObject(i);
                String s = obj2.getString("name").toString();
                list.add(s);
            }
            Log.d("main",JsonResponse+" response");
        }catch (Exception e){
            Log.d("main",e.getMessage());
        }
        return list;
    }
}
