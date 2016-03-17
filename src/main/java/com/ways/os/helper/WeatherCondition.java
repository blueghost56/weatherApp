package com.ways.os.helper;

import android.content.Context;

import com.ways.os.db.WeatherContract;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by csl on 16/3/17.
 */
public final class WeatherCondition {
    private final  static  String TAG= WeatherContract.class.getSimpleName();
  private Context context;
    private final static String BUNDLE_TAG="item";
    private final  static String CONFIGURE_FILE="weather.xml";
    public WeatherCondition(Context context){
    this.context=context;
   }
    public void parser(String condition) throws IOException, XmlPullParserException {
        InputStream inputStream=this.context.getAssets().open(CONFIGURE_FILE);
        XmlPullParser xmlPullParser= XmlPullParserFactory.newInstance().newPullParser();
        xmlPullParser.setInput(inputStream,"UTF-8");
        int eventType=xmlPullParser.getEventType();
         while(eventType!=XmlPullParser.END_DOCUMENT){
            String name=xmlPullParser.getName();
             if(eventType==XmlPullParser.START_TAG){
                if(BUNDLE_TAG.equals(name)){
                }

             }else if(eventType==XmlPullParser.END_TAG){
                if(BUNDLE_TAG.equals(name)){


                }

             }else if(eventType==XmlPullParser.TEXT){
               DebugUtils.dlog(TAG,xmlPullParser.getText());
             }

             eventType=xmlPullParser.next();
         }
    }
}
