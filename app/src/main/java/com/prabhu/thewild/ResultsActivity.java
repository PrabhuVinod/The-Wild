package com.prabhu.thewild;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.prabhu.thewild.utils.NatGeo;
import com.prabhu.thewild.utils.NatGeoAnimal;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    Activity mActivity;
    ViewPager mPager;
    PagerAdapter mPagerAdapter;
    private ArrayList<NatGeoAnimal> animals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mActivity = this;

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


        NatGeo natGeo = NatGeo.INSTANCE;
        animals = natGeo.filterAnimalsByType(animalType);

        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }





    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ScreenSlidePageFragment();
        }

        @Override
        public int getCount() {
            return animals.size();
        }
    }
}
