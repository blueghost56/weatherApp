package com.ways.os.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ways.os.entity.BundleTranser;
import com.ways.os.entity.Weather;
import com.ways.os.entity.XModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csl on 16/3/15.
 */
public class WeatherFragment extends Fragment implements ViewPager.OnPageChangeListener{

    public final static String BUNDLE="bundle";

    private ViewPager mViewPager;
    private MainActivity mainActivity;

    private List<WeatherItemFragment> fragmentList;
    private WeatherAdapter mWeatherAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_weather,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
       super.onActivityCreated(savedInstanceState);
        mainActivity=(MainActivity)getActivity();
       initView();
    }

    private void initView(){
        fragmentList=new ArrayList<>();

        mViewPager=(ViewPager)getView().findViewById(R.id.view_page_weather);
        mViewPager.addOnPageChangeListener(this);
     initData();


    }

    private void initData(){
        BundleTranser bundleTranser=(BundleTranser)getArguments().getSerializable(BUNDLE);
        List<? extends XModel> models=bundleTranser.getSerializables();
        for(XModel xModel : models) {
            Weather weather=(Weather)xModel;

            WeatherItemFragment weatherItemFragment = new WeatherItemFragment();
            Bundle bundle=new Bundle();
            bundle.putSerializable(WeatherItemFragment.BUNDLE_ITEM,weather);
            weatherItemFragment.setArguments(bundle);
            fragmentList.add(weatherItemFragment);
        }
        mWeatherAdapter=new WeatherAdapter(getFragmentManager());
        mViewPager.setAdapter(mWeatherAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
       Weather weather=(Weather)fragmentList.get(position).getArguments().getSerializable(WeatherItemFragment.BUNDLE_ITEM);
       mainActivity.mWebView.setBackgroundImage(weather);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



    class  WeatherAdapter extends FragmentStatePagerAdapter{

        public WeatherAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

}
