package com.ways.os.weather;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.ways.os.location.LocationManager;
import com.ways.os.task.WeatherTask;



public class MainActivity extends AppCompatActivity implements BDLocationListener,View.OnClickListener{

    private final static  int LOADMANAGER_ID=0;

    private LocationManager mLocationManger=null;

    private FloatingActionButton mFloatingActionButton;
    private static WeatherFragment mWeatherFragment;
    private static CityFragment mCityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         //初始数据
          init();

    }

    private void init(){
       mFloatingActionButton=(FloatingActionButton)findViewById(R.id.fab_weather);
        mFloatingActionButton.setOnClickListener(this);
        mLocationManger=new LocationManager(getApplicationContext(),this);

    }
    private  void initFragment(){
        mCityFragment=new CityFragment();
        mWeatherFragment=new WeatherFragment();

        Bundle argments=new Bundle();
       mWeatherFragment.setArguments(argments);

       FragmentTransaction ft=getFragmentManager().beginTransaction();

        ft.add(R.id.layout_container, mWeatherFragment);

        ft.addToBackStack(null);
        ft.commit();


    }

    @Override
    public void onResume(){
        super.onResume();
        if(mLocationManger!=null){
            mLocationManger.startLocation();
        }
    }
    @Override
    public void onPause(){
        super.onPause();
        if(mLocationManger!=null) {
            mLocationManger.stopLocation();
        }
    }


    @Override
    public void onReceiveLocation(BDLocation location) {
        //定位到的结果
        String currentCity=location.getCity();
        currentCity=currentCity.substring(0,currentCity.length()-1);

        new WeatherTask(this).execute(currentCity);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fragment_open_exit,R.anim.fragment_open_enter);
        ft.replace(R.id.layout_container, mCityFragment);
        ft.commit();
    }


}
