package com.prabhu.thewild;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = this;
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
}
