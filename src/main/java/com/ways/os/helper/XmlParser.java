package com.ways.os.helper;

import com.ways.os.db.WeatherContract;
import com.ways.os.entity.Weather;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wwzy on 16/3/16.
 */
public final class XmlParser {

  public static Weather parserXml(String string) throws XmlPullParserException, IOException, NoSuchFieldException, IllegalAccessException {
     XmlPullParser pullParser= XmlPullParserFactory.newInstance().newPullParser();
       pullParser.setInput(new StringReader(string));
       int eventType=pullParser.getEventType();
      Weather weather=new Weather();
      List<String> textList=new ArrayList<>();
       while(eventType!=XmlPullParser.END_DOCUMENT){

           if(eventType==XmlPullParser.START_TAG){

           }else if(eventType==XmlPullParser.END_TAG){

           }else if(eventType==XmlPullParser.TEXT){
               String text=pullParser.getText();
                if(!"  ".equals(text)){
                  textList.add(text);
                }
           }

           eventType=pullParser.next();
       }

      for(int i=0;i<WeatherContract.fields.length;i++){
         String text=textList.get(i);
         Field field= Weather.class.getDeclaredField(WeatherContract.fields[i]);
          field.setAccessible(true);
          field.set(weather,text);
      }

      return weather;
  }

}
