package com.ways.os.location;

import android.content.Context;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * Created by wwzy on 16/3/15.
 */
public class LocationManager {
    private LocationClient locationClient=null;

    public LocationManager(Context context,BDLocationListener weatherListener){
        locationClient=new LocationClient(context);
        locationClient.registerLocationListener(weatherListener);
        initLocation();
    }

    private void initLocation(){
        LocationClientOption option=new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        int span=0;
        option.setScanSpan(span);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        option.setIgnoreKillProcess(false);
        option.setEnableSimulateGps(false);
        locationClient.setLocOption(option);

    }



    public void startLocation(){
        if(!locationClient.isStarted())
        locationClient.start();
    }
    public void stopLocation(){
        if(locationClient.isStarted())
        locationClient.stop();
    }



}
