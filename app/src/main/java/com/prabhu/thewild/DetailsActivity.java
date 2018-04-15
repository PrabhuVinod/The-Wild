package com.prabhu.thewild;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prabhu.thewild.utils.NatGeo;
import com.prabhu.thewild.utils.NatGeoAnimal;
import com.prabhu.thewild.utils.NatGeoPictures;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

import static com.prabhu.thewild.MainActivity.hideSoftKeyboard;

public class DetailsActivity extends AppCompatActivity {

    Activity mActivity;
    Toolbar toolbar;
    ViewPager mPager;
    TabLayout ll_dots;
    WebView about_value_wv;
    ProgressBar progressBar;

    SliderPagerAdapter sliderPagerAdapter;
    int page_position = 0;
    String animal;
    NatGeoAnimal animalObj;
    ArrayList<String> slider_image_list;
    ArrayList<String> credits_list;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mActivity = this;
        NatGeo natGeo = NatGeo.INSTANCE;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progressBar);

        final TextView toolbar_title_tv = (TextView) findViewById(R.id.toolbar_title);
        ImageButton search_btn = (ImageButton) findViewById(R.id.search_btn);
        String[] allAnimalNames = natGeo.getAllAnimalNames().toArray(new String[0]);
        View v = findViewById(R.id.search_bar_include);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity, R.layout.simple_dropdown_item, allAnimalNames);
        final AutoCompleteTextView search_auto_tv = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView);
        search_auto_tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final String selected_animal = (String) adapterView.getItemAtPosition(position);
//                Toast.makeText(mActivity, selected_animal, Toast.LENGTH_SHORT).show();

                search_auto_tv.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        hideSoftKeyboard(search_auto_tv);
                        search_auto_tv.setVisibility(View.INVISIBLE);

                        Intent intent = new Intent(mActivity, DetailsActivity.class);
                        intent.putExtra("animal", selected_animal);
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

                if (search_auto_tv.getVisibility() == View.VISIBLE) {
                    search_auto_tv.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            hideSoftKeyboard(view);
                            search_auto_tv.setVisibility(View.INVISIBLE);
                        }
                    });
                    toolbar_title_tv.animate().alpha(1.0f).setDuration(500);
                } else {
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


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                animal = null;
            } else {
                animal = extras.getString("animal");
            }
        } else {
            animal = (String) savedInstanceState.getSerializable("animal");
        }

//        Toast.makeText(mActivity, "Animal:"+animal, Toast.LENGTH_SHORT).show();

        animalObj = natGeo.getAnimalByName(animal);

        TextView common_name_value_tv, scientific_name_value_tv, type_value_tv, diet_value_tv, average_lifespan_value_tv, size_value_tv, weight_value_tv, red_list_value_tv;


        common_name_value_tv = findViewById(R.id.common_name_value_tv);
        scientific_name_value_tv = findViewById(R.id.scientific_name_value_tv);
        type_value_tv = findViewById(R.id.type_value_tv);
        diet_value_tv = findViewById(R.id.diet_value_tv);
        average_lifespan_value_tv = findViewById(R.id.average_lifespan_value_tv);
        size_value_tv = findViewById(R.id.size_value_tv);
        weight_value_tv = findViewById(R.id.weight_value_tv);
        red_list_value_tv = findViewById(R.id.red_list_value_tv);
        about_value_wv = findViewById(R.id.about_value_wv);

        common_name_value_tv.setText(animalObj.getTitle());
        scientific_name_value_tv.setText(animalObj.getScientificName());
        type_value_tv.setText(animalObj.getType());
        diet_value_tv.setText(animalObj.getDiet());
        average_lifespan_value_tv.setText(animalObj.getAverageLifeSpanInCaptivity());
        size_value_tv.setText(animalObj.getSize());
        weight_value_tv.setText(animalObj.getWeight());

        switch (animalObj.getStatusCode()) {
            case "lc":
                red_list_value_tv.setTextColor(Color.parseColor("#00A000"));
                break;
            case "nt":
                red_list_value_tv.setTextColor(Color.parseColor("#AFCA00"));
                break;
            case "vu":
                red_list_value_tv.setTextColor(Color.parseColor("#FABE00"));
                break;
            case "en":
                red_list_value_tv.setTextColor(Color.parseColor("#EB7800"));
                break;
            case "cr":
                red_list_value_tv.setTextColor(Color.parseColor("#D72800"));
                break;
            case "ew":
                red_list_value_tv.setTextColor(Color.GRAY);
                break;
            case "ex":
                red_list_value_tv.setTextColor(Color.parseColor("#191919"));
                break;
            default:
                red_list_value_tv.setTextColor(Color.BLACK);
                break;
        }
        red_list_value_tv.setText(animalObj.getStatus());

        init();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.slider_dots);
        tabLayout.setupWithViewPager(mPager, true);

        about_value_wv.getSettings().setJavaScriptEnabled(false);
        about_value_wv.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        new fetcher().execute(animalObj.getUrl());

    }

    private void init() {

        mPager = findViewById(R.id.vp_slider);
        ll_dots = findViewById(R.id.slider_dots);

        slider_image_list = new ArrayList<>();
        credits_list = new ArrayList<>();

        slider_image_list.add(animalObj.getThumbnail().getUrl());
        credits_list.add(animal);

        final RequestQueue requestQueue = Volley.newRequestQueue(mActivity);

        String url = animalObj.getUrl().replace(".html", "/").concat("_jcr_content/content/yourshot.gallery.json");

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                try {
                    JSONObject picturesObj = new JSONObject(response);
                    JSONArray picturesArr = picturesObj.getJSONArray("items");

                    if (picturesArr.length() > 0) {
                        NatGeoPictures[] temp = gson.fromJson(picturesArr.toString(), NatGeoPictures[].class);
                        for (NatGeoPictures pic : temp) {
                            slider_image_list.add(pic.getUrl().concat(pic.getSizes().get640()));
                            credits_list.add("Credit: " + pic.getCredit());
                        }
                    }
                } catch (JSONException e) {
                    //TODO: Open error screen
                    e.printStackTrace();
                }
                sliderPagerAdapter = new SliderPagerAdapter(mActivity, slider_image_list, credits_list);
                mPager.setAdapter(sliderPagerAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO: Open error screen
                error.printStackTrace();
                sliderPagerAdapter = new SliderPagerAdapter(mActivity, slider_image_list, credits_list);
                mPager.setAdapter(sliderPagerAdapter);
            }
        });


        requestQueue.add(stringRequest);


        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    class fetcher extends AsyncTask<String, Void, Element> {
        //HERE DECLARE THE VARIABLES YOU USE FOR PARSING
        private Element overview = null;

        @Override
        protected Element doInBackground(String... arg0) {
            Document doc = null;
            String url = arg0[0];
            try {
                doc = Jsoup.connect(url).get();
                overview = doc.select(".in-depth-content .par").last();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                progressBar.setVisibility(View.GONE);
            }

            return overview;
        }

        @Override
        protected void onPostExecute(Element result) {
            progressBar.setVisibility(View.GONE);
            about_value_wv.loadData(result.html(), "text/html; charset=utf-8", "UTF-8");
        }

    }


}
