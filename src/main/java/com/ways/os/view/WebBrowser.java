package com.ways.os.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ways.os.entity.Weather;
import com.ways.os.helper.MediaManager;
import com.ways.os.helper.WeatherCondition;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by csl on 16/3/17.
 */
public class WebBrowser extends WebView {

    private MediaManager mMediaManager=null;
    private JavaScriptInterface javaScriptInterface=null;

    public WebBrowser(Context context){
        super(context);
        init();
    }
    public WebBrowser(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public WebBrowser(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setBackgroundImage(Weather weather){
        if(weather==null)return;
        String image= null;
        try {
            image = parserWeather(getContext(),weather);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        javaScriptInterface.setBackgroundImage(image);
        loadUrl("javascript:changeBackground('"+image+"')");

    }



    private  static  String parserWeather(Context context,Weather weather) throws IOException, XmlPullParserException {
     String condition=weather.getCondition();
       new WeatherCondition(context).parser(condition);
     return "raining.gif";
    }


    private void init(){
        mMediaManager=new MediaManager();

        setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }
        });

        getSettings().setJavaScriptEnabled(true);
        getSettings().setAllowFileAccess(true);
        getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        javaScriptInterface=new JavaScriptInterface();
        addJavascriptInterface(javaScriptInterface, "Android");
    }

    class JavaScriptInterface{
        private String backimage;
        public JavaScriptInterface(){}
        public void setBackgroundImage(String backgroundImage){
            this.backimage=backgroundImage;
        }
        @JavascriptInterface
        public String getBackgroundImage(){
            return this.backimage;
        }
    }
}
