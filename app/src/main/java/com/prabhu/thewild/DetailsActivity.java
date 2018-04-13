package com.prabhu.thewild;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.prabhu.thewild.utils.NatGeo;

import static com.prabhu.thewild.MainActivity.hideSoftKeyboard;

public class DetailsActivity extends AppCompatActivity {

    Activity mActivity;
    Toolbar toolbar;
    ViewPager mPager;
    PagerAdapter mPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mActivity = this;
        NatGeo natGeo = NatGeo.INSTANCE;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");

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
                String selected_animal = (String) adapterView.getItemAtPosition(position);
                Toast.makeText(mActivity, selected_animal, Toast.LENGTH_SHORT).show();

                search_auto_tv.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        hideSoftKeyboard(search_auto_tv);
                        search_auto_tv.setVisibility(View.INVISIBLE);
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

    }
}
