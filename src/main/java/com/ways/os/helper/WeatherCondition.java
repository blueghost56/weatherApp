package com.ways.os.helper;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by csl on 16/3/17.
 */
public final class WeatherCondition {
  private Context context;
    private final static String BUNDLE_TAG="item";
    private final  static String CONFIGURE_FILE="weather.xml";
    public WeatherCondition(Context context){
    this.context=context;
   }
    public void parser() throws IOException, XmlPullParserException {
        XmlPullParser xmlPullParser=this.context.getAssets().openXmlResourceParser(CONFIGURE_FILE);
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

             }

             eventType=xmlPullParser.next();
         }
    }
}
