package com.ways.os.task;

import android.content.Context;
import android.os.AsyncTask;

import com.ways.os.db.WeatherContract;
import com.ways.os.entity.Weather;
import com.ways.os.helper.DebugUtils;
import com.ways.os.helper.XmlParser;
import com.ways.os.provider.WeatherProvider;
import com.ways.os.xhttpclient.Post;
import com.ways.os.xhttpclient.StringBody;
import com.ways.os.xhttpclient.XHttpClient;
import com.ways.os.xhttpclient.XResponse;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by wwzy on 16/3/17.
 */
//网络操作
public final class WeatherTask extends AsyncTask<String,Void,Weather> {
    final static String TAG=WeatherTask.class.getSimpleName();

    final static String URL="http://ws.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName";
    final static String PARAM="theCityName";
    private XHttpClient mClient;
    private Context context;
    public WeatherTask(Context context) {

        mClient=new XHttpClient();
        this.context=context;
    }

    @Override
    public Weather doInBackground(String... params) {
        Weather mWeather=null;
        HashMap<String,String> param=new HashMap<>();
        param.put(PARAM, params[0]);
        try {
            Post mPost = new Post.Builder()
                    .url(URL)
                    .body(new StringBody(param))
                    .create();

            XResponse response = mClient.newRequest(mPost).run();
            String strContent=response.body().string();
            mWeather= XmlParser.parserXml(strContent);
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
            //保存到数据库
            mWeather.update(this.context, WeatherProvider.CONTENT_URI, WeatherContract.CITYCODE_F+"=?", new String[]{mWeather.getCityCode()});
        }

        DebugUtils.dlog(TAG,mWeather.toString());

        return mWeather;
    }

    @Override
    public void onPostExecute(Weather weather){
        if(weather!=null){

        }

    }
}

