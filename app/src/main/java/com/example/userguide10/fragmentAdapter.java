package com.example.userguide10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class fragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private static String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3","Tab 4" };
    private static int imageResId[] = new int[] {R.drawable.when,R.drawable.how,R.drawable.when};
    private static Context context;

    public fragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return BlankFragmentActivity_1.newInstance(position+1);
        }else if(position==1){
            return BlankFrangmentActivity_2.newInstance(position+1);
        }else if(position==2){
            return BlankFragmentActivity_3.newInstance(position+1);
        }else if(position==3){
            return BlankFragmentActivity_4.newInstance(position+1);
        }else {
            return BlankFragment_1.newInstance(position + 1);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    public static View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView tv = (TextView) v.findViewById(R.id.textView);
        tv.setText(tabTitles[position]);
        ImageView img = (ImageView) v.findViewById(R.id.imgView);
        img.setImageResource(imageResId[position]);
        return v;
    }
}
