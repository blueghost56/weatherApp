package com.ways.os.helper;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import com.ways.os.entity.BundleTranser;
import com.ways.os.entity.Weather;
import com.ways.os.provider.WeatherProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csl on 16/3/17.
 */
public class LoaderManager {
    private final static String TAG=LoaderManager.class.getSimpleName();

    public interface LoaderCallBack{
        public void onLoaderBefore();
        public void onLoaderFinished(BundleTranser bundleTranser);
    }
    private Context context;
    private LoaderCallBack mLoaderCallBack;

    public LoaderManager(Context context){
        this.context=context;

    }
    public void initLoader(LoaderCallBack loaderCallBack){
        this.mLoaderCallBack=loaderCallBack;
        new LoaderAsyncTask().execute();
    }

    class LoaderAsyncTask extends AsyncTask<Void,Void,BundleTranser>{

        @Override
        protected BundleTranser doInBackground(Void... params) {
            Cursor cursor=context.getContentResolver().query(WeatherProvider.CONTENT_URI,null,null,null,null,null);
            cursor.moveToFirst();
            String[]columnNames=cursor.getColumnNames();
            List<Weather> weathers=new ArrayList<>();

            while(cursor.moveToNext()){
                Weather mWeather=new Weather();

                for(String columnName:columnNames) {
                    String fieldValue= cursor.getString(cursor.getColumnIndex(columnName));
                    mWeather.setFieldValue(columnName,fieldValue);
                }

                DebugUtils.dlog(TAG,mWeather.toString());

                weathers.add(mWeather);
            }

            BundleTranser bundleTranser=new BundleTranser();
             bundleTranser.setSerializables(weathers);

            return bundleTranser;
        }

        @Override
        public void onPostExecute(BundleTranser bundleTranser){
            mLoaderCallBack.onLoaderFinished(bundleTranser);
        }
    }



}
