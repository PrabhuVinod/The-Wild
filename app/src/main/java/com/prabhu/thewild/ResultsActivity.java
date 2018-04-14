package com.prabhu.thewild;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.prabhu.thewild.utils.NatGeo;
import com.prabhu.thewild.utils.NatGeoAnimal;

import java.util.ArrayList;
import java.util.Collections;

import static com.prabhu.thewild.MainActivity.hideSoftKeyboard;

public class ResultsActivity extends AppCompatActivity {

    Activity mActivity;
    Toolbar toolbar;
    ViewPager mPager;
    PagerAdapter mPagerAdapter;
    private ArrayList<NatGeoAnimal> animals;
    TextView match_count_tv, search_msg_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mActivity = this;
        NatGeo natGeo = NatGeo.INSTANCE;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        final TextView toolbar_title_tv = (TextView)findViewById(R.id.toolbar_title);
        ImageButton search_btn = (ImageButton) findViewById(R.id.search_btn);
        String[] allAnimalNames = natGeo.getAllAnimalNames().toArray(new String[0]);
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

        match_count_tv = findViewById(R.id.match_count_tv);
        search_msg_tv = findViewById(R.id.search_msg_tv);

        String animalType;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                animalType= null;
            } else {
                animalType= extras.getString("animalType");
            }
        } else {
            animalType= (String) savedInstanceState.getSerializable("animalType");
        }

        animals = natGeo.filterAnimalsByType(animalType);

        Collections.shuffle(animals);


        search_msg_tv.setText(animalType);
        match_count_tv.setText("1/"+animals.size());


        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPager.setPageMargin(convertDip2Pixels(mActivity,16));
        mPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.75f);
                page.setScaleY(normalizedposition / 2 + 0.75f);
            }
        });

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("onPageScrolled",":"+position);
            }

            @Override
            public void onPageSelected(int position) {
                match_count_tv.setText((position+1)+"/"+animals.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("PageScrollStateChanged",":"+state);
            }
        });
        mPager.setAdapter(mPagerAdapter);
    }


    public static int convertDip2Pixels(Context context, int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {


        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            final ScreenSlidePageFragment f = new ScreenSlidePageFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("animal",animals.get(position));
            f.setArguments(bundle);
            return f;
        }

        @Override
        public int getCount() {
            return animals.size();
        }
    }
}
