package com.ways.os.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.ways.os.weather.CityFragment;
import com.ways.os.weather.WeatherFragment;

/**
 * Created by wwzy on 16/3/15.
 */
public class WeatherLayout extends RelativeLayout {
    private WeatherFragment weatherFragment;
    private CityFragment cityFragment;

    public WeatherLayout(Context context){
        super(context);
        init();
    }
    public WeatherLayout(Context context,AttributeSet attrs){
        super(context,attrs);
        init();
    }
    public WeatherLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){

    }
}
