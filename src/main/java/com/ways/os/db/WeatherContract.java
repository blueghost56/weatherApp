package com.ways.os.db;

/**
 * Created by wwzy on 16/3/15.
 */
public  class WeatherContract {
    public final static String DB_NAME="weahter.db";
    public final static int DB_VERSION=1;
    public final static String COMMA=",";

    public final static String UPDATE_SQL="DROP TABLE IF EXISTS "+WeatherContract.TABLE_NAME+";";


    public final static  String TABLE_NAME="WAYS_WEATHER";

    public static  final String[] fields={
            WeatherContract.PROVINCE_F,
            WeatherContract.CITY_F,
            WeatherContract.CITYCODE_F,
            WeatherContract.CITYPICTURE_F,
            WeatherContract.LASTUPDATE_F,
            WeatherContract.TEMPERATURE_F,
            WeatherContract.CONDITION_F,
            WeatherContract.WIND_F,
            WeatherContract.TRENDIMAGE10_F,
            WeatherContract.TRENDIMAGE11_F,
            WeatherContract.LIVE_F,
            WeatherContract.LIFEINDEX_F,
            WeatherContract.TEMPERATURE2_F,
            WeatherContract.CONDITION2_F,
            WeatherContract.WIND2_F,
            WeatherContract.TRENDIMAGE20_F,
            WeatherContract.TRENDIMAGE21_F,
            WeatherContract.TEMPERATURE3_F,
            WeatherContract.CONDITION3_F,
            WeatherContract.WIND3_F,
            WeatherContract.TRENDIMAGE30_F,
            WeatherContract.TRENDIMAGE31_F,
            WeatherContract.HISTORY_F
    };

    public final static  String  ID_F="_id";
    public final static  String PROVINCE_F="province"; //省
    public final static  String CITY_F="city";//市
    public final static  String CITYCODE_F="cityCode";//城市代码
    public final static  String CITYPICTURE_F="cityPicture";//城市图片
    public final static  String LASTUPDATE_F="lastUpdateTime";//最后更新时间
    public final static  String TEMPERATURE_F="temperature";//气温
    public final static  String CONDITION_F="condition";//概况
    public final static  String WIND_F="wind";//风向和风力
    public final static  String TRENDIMAGE10_F="trendImage10";//天气趋势开始图片名称
    public final static  String TRENDIMAGE11_F="trendImage11";//天气趋势结束图片名称
    public final static  String LIVE_F="live";//天气实况
    public final static  String LIFEINDEX_F="lifeIndex";//天气和生活指数
    public final static  String TEMPERATURE2_F="temperature2";//第二天的气温
    public final static  String CONDITION2_F="condition2";//第二天的概况
    public final static  String WIND2_F="wind2";//第二天的风力
    public final static  String TRENDIMAGE20_F="trendImage20";//第二天天气趋势开始图片名称
    public final static  String TRENDIMAGE21_F="trendImage21";//第二天天气趋势开始图片名称
    public final static  String TEMPERATURE3_F="temperature3";//第三天的气温；
    public final static  String CONDITION3_F="condition3";//第三天的概况
    public final static  String WIND3_F="wind3";//第三天的分离
    public final static  String TRENDIMAGE30_F="trendImage30";//第三天天气趋势开始图片名称
    public final static  String TRENDIMAGE31_F="trendImage31";//第三天天气趋势开始图片名称
    public final static  String HISTORY_F="history";//查询的城市或地区的介绍


}
