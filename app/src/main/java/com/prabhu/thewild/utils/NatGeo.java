package com.prabhu.thewild.utils;

import android.app.Activity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NatGeo {
    INSTANCE;

    RequestQueue requestQueue;
    NatGeoAnimal[] animals;


    public void loadAllAnimals(Activity mActivity, final NatGeoCallback natGeoCallback){

        requestQueue = Volley.newRequestQueue(mActivity);

        String url = "https://www.nationalgeographic.com/travel/bin/services/core/public/query/animals/profile.filteredsearch.json";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                natGeoCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO: Open error screen
                error.printStackTrace();
            }
        });
        requestQueue.add(stringRequest);
    }

    public NatGeoAnimal[] getAllAnimals() {
        return  animals;
    }
    private void setAllAnimals(NatGeoAnimal[] animals){
        this.animals = animals;
    }

    public void responseToJSON(String responseJSON, String jsonType){

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        if(jsonType.equals("animals")){
            try {
                JSONObject animalsObj = new JSONObject(responseJSON);
                JSONArray animalsArr= animalsObj.getJSONArray("animals");

                if (animalsArr.length() > 0) {
                    animals = gson.fromJson(animalsArr.toString(), NatGeoAnimal[].class);

                    Log.d("animals size",String.valueOf(animals.length));
                    for(NatGeoAnimal animal: animals){
                        Log.e("ANIMAL",animal.getTitle());
                    }
                }
                setAllAnimals(animals);

            } catch (JSONException e) {
                //TODO: Open error screen
                e.printStackTrace();
            }
        }

    }
}
