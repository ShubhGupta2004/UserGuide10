package com.example.userguide10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    ArrayList<CustomJavaClass> listNew = new ArrayList<>();

    public CustomAdapter(Context context, int textViewResourceId, ArrayList<CustomJavaClass> objects) {
        super(context, textViewResourceId, objects);
        listNew = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.custom_layout, null);
        TextView text = (TextView) v.findViewById(R.id.textView);
        ImageView image = (ImageView) v.findViewById(R.id.imageView);

        text.setText(listNew.get(position).getName().toString());
        Picasso.get().load(listNew.get(position).getImgLink()).into(image);

        return v;
    }
}
