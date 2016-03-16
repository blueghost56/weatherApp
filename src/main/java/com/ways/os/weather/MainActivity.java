package com.ways.os.weather;

import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.ways.os.entity.Weather;
import com.ways.os.helper.XmlParser;
import com.ways.os.location.LocationManager;
import com.ways.os.provider.WeatherProvider;
import com.ways.os.xhttpclient.Post;
import com.ways.os.xhttpclient.StringBody;
import com.ways.os.xhttpclient.XHttpClient;
import com.ways.os.xhttpclient.XResponse;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;



public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks,BDLocationListener,View.OnClickListener{

    private final static String CONFIGURE="weather_default";
    private final static String HOST_CITY="host_city";
    private final static String CITY_NAME="city_name";
    private final static String CACH_DATA="cach_data";

    private final static  int LOADMANAGER_ID=0;

    private LocationManager mLocationManger=null;

    private boolean hasHostCity=false;
    private Set<String> cityNames=null;
    private boolean hasCacheData=false;

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


       SharedPreferences mSharedPerences=getSharedPreferences(CONFIGURE,Context.MODE_PRIVATE);

        hasHostCity=mSharedPerences.getBoolean(HOST_CITY, false);
        cityNames=mSharedPerences.getStringSet(CITY_NAME, null);
        hasCacheData=mSharedPerences.getBoolean(CACH_DATA, false);

         if(!hasHostCity||cityNames==null){
             mLocationManger=new LocationManager(getApplicationContext(),this);
             return;
         }


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
    public Loader onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onResume(){
        super.onResume();
        if(mLocationManger!=null) {
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
    public void onLoadFinished(Loader loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }


    @Override
    public void onReceiveLocation(BDLocation location) {
        //定位到的结果
        String currentCity=location.getCity();
        currentCity=currentCity.substring(0,currentCity.length()-1);
        new WeatherTask().execute(currentCity);

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fragment_open_exit,R.anim.fragment_open_enter);
        ft.replace(R.id.layout_container, mCityFragment);
        ft.commit();
    }

    class WeatherTask extends AsyncTask<String,Void,Weather>{
        private XHttpClient mClient;
        public WeatherTask() {

            mClient=new XHttpClient();
        }


        @Override
        public Weather doInBackground(String... params) {
            Weather mWeather=null;
            HashMap<String,String> param=new HashMap<>();
            param.put("theCityName", params[0]);
            try {
                Post mPost = new Post.Builder()
                        .url("http://ws.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName")
                        .body(new StringBody(param))
                        .create();
                XResponse response = mClient.newRequest(mPost).run();
                String strContent=response.body().string();
              mWeather=XmlParser.parserXml(strContent);
            }catch (IOException ioe){
                ioe.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

             if(mWeather!=null){
                Field[]fields=mWeather.getClass().getDeclaredFields();
                 ContentValues contentValues=new ContentValues();
                   for(Field field:fields){
                       field.setAccessible(true);
                       try {
                           contentValues.put(field.getName(),(String)field.get(mWeather));
                       } catch (IllegalAccessException e) {
                           e.printStackTrace();
                       }
                   }
                mWeather.save(MainActivity.this,WeatherProvider.CONTENT_URI);
             }

            return mWeather;
        }

        @Override
        public void onPostExecute(Weather weather){


        }
    }


}
