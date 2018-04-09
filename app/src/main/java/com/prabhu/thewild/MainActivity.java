package com.prabhu.thewild;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private TextView text;
    private View included_searchbar;

    private AnimatedVectorDrawable searchToBar;
    private AnimatedVectorDrawable barToSearch;
    private float offset;
    private Interpolator interp;
    private int duration;
    private boolean expanded = false;

    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = this;

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        included_searchbar = (View)findViewById(R.id.included_searchbar);
        iv = (ImageView) included_searchbar.findViewById(R.id.search);
        text = (TextView) included_searchbar.findViewById(R.id.text);
        searchToBar = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_search_to_bar);
        barToSearch = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_bar_to_search);
        interp = AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in);
        duration = getResources().getInteger(R.integer.duration_bar);
        // iv is sized to hold the search+bar so when only showing the search icon, translate the
        // whole view left by half the difference to keep it centered
        offset = -71f * (int) getResources().getDisplayMetrics().scaledDensity;
        iv.setTranslationX(offset);
        text.setAlpha(0f);
        text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    expanded =true;
                    animate(iv);
                    Toast.makeText(mActivity, "unfocused", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ImageView mammals_img_iv = (ImageView)findViewById(R.id.mammals_imageView), reptiles_img_iv = (ImageView)findViewById(R.id.reptiles_imageView), amphibians_img_iv = (ImageView)findViewById(R.id.amphibians_imageView), invertebrates_img_iv = (ImageView)findViewById(R.id.invertebrates_imageView), birds_img_iv = (ImageView)findViewById(R.id.birds_imageView), fish_img_iv = (ImageView)findViewById(R.id.fish_imageView);

        Glide.with(mActivity).load(R.drawable.mammals).apply(new RequestOptions().centerCrop()).into(mammals_img_iv);
        Glide.with(mActivity).load(R.drawable.birds).apply(new RequestOptions().centerCrop()).into(birds_img_iv);
        Glide.with(mActivity).load(R.drawable.reptiles).apply(new RequestOptions().centerCrop()).into(reptiles_img_iv);
        Glide.with(mActivity).load(R.drawable.amphibians).apply(new RequestOptions().centerCrop()).into(amphibians_img_iv);
        Glide.with(mActivity).load(R.drawable.invertebrates).apply(new RequestOptions().centerCrop()).into(invertebrates_img_iv);
        Glide.with(mActivity).load(R.drawable.fish).apply(new RequestOptions().centerCrop()).into(fish_img_iv);

    }

    public void openTypeDetails(View view){

        switch (view.getId()){
            case R.id.mammals_imageView: Toast.makeText(mActivity, "Mammals clicked" , Toast.LENGTH_SHORT).show();
            break;
            case R.id.birds_imageView: Toast.makeText(mActivity, "Birds clicked" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.reptiles_imageView: Toast.makeText(mActivity, "reptiles clicked" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.amphibians_imageView: Toast.makeText(mActivity, "amphibians clicked" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.invertebrates_imageView: Toast.makeText(mActivity, "invertebrates clicked" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.fish_imageView: Toast.makeText(mActivity, "fish clicked" , Toast.LENGTH_SHORT).show();
                break;
            default : Toast.makeText(mActivity, "default executed" , Toast.LENGTH_SHORT).show();
                break;
        }

    }



    public void animate(View view) {
        Toast.makeText(mActivity, getResources().getResourceEntryName(view.getId()), Toast.LENGTH_SHORT).show();
        if (!expanded) {
            iv.setImageDrawable(searchToBar);
            searchToBar.start();
            iv.animate().translationX(0f).setDuration(duration).setInterpolator(interp);
            text.animate().alpha(1f).setStartDelay(duration - 100).setDuration(100).setInterpolator(interp);
            text.setVisibility(View.VISIBLE);
            expanded = !expanded;
        } else {
            if(view.getId() != text.getId()){
                iv.setImageDrawable(barToSearch);
                barToSearch.start();
                iv.animate().translationX(offset).setDuration(duration).setInterpolator(interp);
                text.setAlpha(0f);
                text.setVisibility(View.INVISIBLE);
                expanded = !expanded;
            }
        }
    }


}
