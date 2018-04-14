package com.prabhu.thewild;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class SliderPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    Activity activity;
    ArrayList<String> image_arraylist;
    ArrayList<String> credits_arraylist;

    public SliderPagerAdapter(Activity activity, ArrayList<String> image_arraylist, ArrayList<String> credits_arraylist) {
        this.activity = activity;
        this.image_arraylist = image_arraylist;
        this.credits_arraylist = credits_arraylist;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.details_slider_item, container, false);
        ImageView im_slider = (ImageView) view.findViewById(R.id.details_slider_image);
        TextView credits_tv = (TextView) view.findViewById(R.id.credits_tv);

        if(position<image_arraylist.size()){
            if(image_arraylist.get(position).equals("Unknown"))
                Glide.with(activity).load(R.drawable.image_placeholder).apply(new RequestOptions().centerCrop()).into(im_slider);
            else
                Glide.with(activity).load(image_arraylist.get(position)).apply(new RequestOptions().centerCrop()).into(im_slider);
        }
        else
            Glide.with(activity).load(R.drawable.image_placeholder).apply(new RequestOptions().centerCrop()).into(im_slider);

        if (position<credits_arraylist.size())
            credits_tv.setText(credits_arraylist.get(position));



        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return image_arraylist.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
