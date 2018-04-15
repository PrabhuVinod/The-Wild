package com.prabhu.thewild.utils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prabhu.thewild.ErrorActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public enum NatGeo {
    INSTANCE;

    RequestQueue requestQueue;
    NatGeoAnimal[] animals;
    ArrayList<String> allAnimalNames = new ArrayList<String>();

    public void loadAllAnimals(final Activity mActivity, final NatGeoCallback natGeoCallback) {

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
                //natGeoCallback.onError(error.getMessage());
//                Log.e("error","natgeo");
                Intent intent = new Intent(mActivity, ErrorActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                if (mActivity != null) {
                    mActivity.startActivity(intent);
                    mActivity.finish();
                }
            }
        });
        requestQueue.add(stringRequest);
    }

    public NatGeoAnimal[] getAllAnimals() {
        return animals;
    }

    private void setAllAnimals(NatGeoAnimal[] animals) {
        this.animals = animals;
    }

    public ArrayList<String> getAllAnimalNames() {
        Collections.shuffle(allAnimalNames);
        return allAnimalNames;
    }

    public void responseToJSON(String responseJSON, String jsonType) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        if (jsonType.equals("animals")) {
            try {
                JSONObject animalsObj = new JSONObject(responseJSON);
                JSONArray animalsArr = animalsObj.getJSONArray("animals");

                if (animalsArr.length() > 0) {
                    animals = gson.fromJson(animalsArr.toString(), NatGeoAnimal[].class);

                    Log.d("animals size", String.valueOf(animals.length));
                    int i = 0;
                    for (NatGeoAnimal animal : animals) {
                        allAnimalNames.add(animal.getTitle());
                        i++;
                    }
                }
                setAllAnimals(animals);

            } catch (JSONException e) {
                //TODO: Open error screen
                e.printStackTrace();
            }
        }

    }

    public ArrayList<NatGeoAnimal> filterAnimalsByType(String type) {
        ArrayList<NatGeoAnimal> filteredAnimals = new ArrayList<>();
        for (NatGeoAnimal animal : animals) {
//            Log.e(animal.getTitle(),animal.getType());
            if (animal.getType().equals(type))
                filteredAnimals.add(animal);
        }
        return filteredAnimals;
    }

    public NatGeoAnimal getAnimalByName(String name) {
        for (NatGeoAnimal animal : animals) {
            if (animal.getTitle().equals(name))
                return animal;
        }
        return null;
    }

}
