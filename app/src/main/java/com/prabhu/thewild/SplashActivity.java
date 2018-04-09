package com.prabhu.thewild;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

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
        });

    }
}
