package com.prabhu.thewild;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.prabhu.thewild.utils.NatGeoAnimal;

public class ScreenSlidePageFragment extends Fragment {


    public static int convertDip2Pixels(Context context, int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Bundle args = getArguments();
        final NatGeoAnimal animal = args.getParcelable("animal");
//        Log.e("In Fragment",animal.getTitle());

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.slider_item, container, false);


        if (rootView != null) {
            TextView animal_title_tv = rootView.findViewById(R.id.animal_title_tv);
            TextView animal_type_tv = rootView.findViewById(R.id.animal_type_tv);
            TextView animal_diet_tv = rootView.findViewById(R.id.animal_diet_tv);
            ImageView animal_iv = rootView.findViewById(R.id.animal_iv);

            if (animal.getThumbnail().getUrl().equals("Unknown"))
                Glide.with(getActivity()).load(R.drawable.image_placeholder).apply(new RequestOptions().centerCrop()).into(animal_iv);
            else
                Glide.with(getActivity()).load(animal.getThumbnail().getUrl()).apply(new RequestOptions().centerCrop()).into(animal_iv);
            animal_title_tv.setText(animal.getTitle());
            animal_type_tv.setText("Type: " + animal.getType());
            animal_diet_tv.setText("Diet: " + animal.getDiet());


            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra("animal", animal.getTitle());
                    startActivity(intent);
                }
            });


        }

        return rootView;
    }
}