package com.prabhu.thewild;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.opengl.Visibility;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.prabhu.thewild.utils.NatGeo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Activity mActivity;
    Toolbar toolbar;

    String[] allAnimalNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = this;

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");

        final TextView toolbar_title_tv = (TextView)findViewById(R.id.toolbar_title);
        ImageButton search_btn = (ImageButton) findViewById(R.id.search_btn);


        NatGeo natGeo = NatGeo.INSTANCE;
        allAnimalNames = natGeo.getAllAnimalNames().toArray(new String[0]);


        View v= findViewById(R.id.search_bar_include);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity, R.layout.simple_dropdown_item, allAnimalNames);
        final AutoCompleteTextView search_auto_tv = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView);
        search_auto_tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final String selected_animal = (String) adapterView.getItemAtPosition(position);
                Toast.makeText(mActivity, selected_animal, Toast.LENGTH_SHORT).show();

                search_auto_tv.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        hideSoftKeyboard(search_auto_tv);
                        search_auto_tv.setVisibility(View.INVISIBLE);

                        Intent intent =new Intent(mActivity, DetailsActivity.class);
                        intent.putExtra("animal",selected_animal);
                        startActivity(intent);
                    }
                });
                toolbar_title_tv.animate().alpha(1.0f).setDuration(500);

            }
        });

        search_auto_tv.setAdapter(adapter);
        search_auto_tv.setVisibility(View.INVISIBLE);


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if(search_auto_tv.getVisibility() == View.VISIBLE){
                    search_auto_tv.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            hideSoftKeyboard(view);
                            search_auto_tv.setVisibility(View.INVISIBLE);
                        }
                    });
                    toolbar_title_tv.animate().alpha(1.0f).setDuration(500);
                }
                else {
                    toolbar_title_tv.animate().alpha(0.0f).setDuration(500);
                    search_auto_tv.animate().alpha(1.0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            search_auto_tv.setVisibility(View.VISIBLE);
                        }
                    });

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
            openTypeResults("Mammals");
            break;
            case R.id.birds_imageView: Toast.makeText(mActivity, "Birds clicked" , Toast.LENGTH_SHORT).show();
                openTypeResults("Birds");
                break;
            case R.id.reptiles_imageView: Toast.makeText(mActivity, "reptiles clicked" , Toast.LENGTH_SHORT).show();
                openTypeResults("Reptiles");
                break;
            case R.id.amphibians_imageView: Toast.makeText(mActivity, "amphibians clicked" , Toast.LENGTH_SHORT).show();
                openTypeResults("Amphibians");
                break;
            case R.id.invertebrates_imageView: Toast.makeText(mActivity, "invertebrates clicked" , Toast.LENGTH_SHORT).show();
                openTypeResults("Invertebrates");
                break;
            case R.id.fish_imageView: Toast.makeText(mActivity, "fish clicked" , Toast.LENGTH_SHORT).show();
                openTypeResults("Fish");
                break;
            default : Toast.makeText(mActivity, "default executed" , Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public void openTypeResults(String animalType){
        Intent intent = new Intent(mActivity, ResultsActivity.class);
        intent.putExtra("animalType",animalType);
        //startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity).toBundle());
        startActivity(intent);
        //finish();

    }

    public static void hideSoftKeyboard(View view) {
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

}
