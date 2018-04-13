package com.prabhu.thewild;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.prabhu.thewild.utils.NatGeo;
import com.prabhu.thewild.utils.NatGeoCallback;

public class SplashActivity extends AppCompatActivity {

    Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mActivity = this;

        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.splash_progress);
        progressBar.setVisibility(View.VISIBLE);

        final NatGeo natGeo = NatGeo.INSTANCE;

        final ImageView splash_logo=findViewById(R.id.splash_logo);
        ViewTreeObserver vto = splash_logo.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                splash_logo.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                natGeo.loadAllAnimals(mActivity, new NatGeoCallback() {
                    @Override
                    public void onSuccess(String resopnseJson) {
                        natGeo.responseToJSON(resopnseJson, "animals");

                        progressBar.setVisibility(View.GONE);

                        Intent intent = new Intent(mActivity, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        if(mActivity != null) {
                            //startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity).toBundle());  // animation causing crash
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(mActivity, "ERROR:"+error, Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });



    }

}
