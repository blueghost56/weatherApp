package com.ways.os.weather;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ways.os.entity.Weather;

/**
 * Created by wwzy on 16/3/16.
 */
public class WeatherItemFragment extends Fragment {
    public final static String BUNDLE_ITEM="bundle_item";
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_weather_item,container,false);
    }
    @Override
    public void onActivityCreated(Bundle saveInstanceSate){
        super.onActivityCreated(saveInstanceSate);
        TextView tv=(TextView)getView().findViewById(R.id.tv);
        Weather item= (Weather)getArguments().getSerializable(BUNDLE_ITEM);

        tv.setText(item.toString());
    }

}
