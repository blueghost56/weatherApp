package com.ways.os.weather;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.ways.os.entity.BundleTranser;
import com.ways.os.helper.LoaderManager;
import com.ways.os.location.LocationManager;
import com.ways.os.task.WeatherTask;
import com.ways.os.view.WebBrowser;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallBack,BDLocationListener,View.OnClickListener{

    private final static  String HTML_PATH="file:///android_asset/h5/weather.html";

    private LocationManager mLocationManger=null;


    private FloatingActionButton mFloatingActionButton;
    public WebBrowser mWebView;


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

        mWebView=(WebBrowser)findViewById(R.id.web_view_back);
        mWebView.loadUrl(HTML_PATH);

        new LoaderManager(this).initLoader(this);
    }



    private  void initFragment(BundleTranser bundleTranser){
        mCityFragment=new CityFragment();
        mWeatherFragment=new WeatherFragment();

        Bundle arguments=new Bundle();
         arguments.putSerializable(WeatherFragment.BUNDLE,bundleTranser);

       mWeatherFragment.setArguments(arguments);

       FragmentTransaction ft=getSupportFragmentManager().beginTransaction();

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
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fragment_open_enter,R.anim.fragment_open_exit);
        ft.replace(R.id.layout_container, mCityFragment);
        ft.commit();
    }

    @Override
    public void onLoaderBefore() {

    }

    @Override
    public void onLoaderFinished(BundleTranser bundleTranser) {

        if(bundleTranser.getSerializables().size()==0) {
            mLocationManger = new LocationManager(getApplicationContext(), this);
            mLocationManger.startLocation();
          return;
        }
        initFragment(bundleTranser);

    }
}
