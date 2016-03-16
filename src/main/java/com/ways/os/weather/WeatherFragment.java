package com.ways.os.weather;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by wwzy on 16/3/15.
 */
public class WeatherFragment extends Fragment {
    private ViewPager mViewPager;
    private WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_weather,container,false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
       super.onActivityCreated(savedInstanceState);
       initView();
    }

    private void initView(){
        mViewPager=(ViewPager)getView().findViewById(R.id.view_page_weather);
        mWebView=(WebView)getView().findViewById(R.id.web_view_back);
        mWebView.loadUrl("http://www.baidu.com");
        mWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

}
