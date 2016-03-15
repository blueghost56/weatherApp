package com.ways.os.entity;

/**
 * Created by wwzy on 16/3/15.
 */
public class Weather{
    private String province; //省
    private String city;//市
    private String cityCode;//城市代码
    private String cityPicture;//城市图片
    private String temperature;//气温
    private String condition;//概况
    private String wind;//风向和风力
    private String trendImage10;//天气趋势开始图片名称
    private String trendImage11;//天气趋势结束图片名称
    private String live;//天气实况
    private String lifeIndex;//天气和生活指数
    private String temperature2;//第二天的气温
    private String condition2;//第二天的概况
    private String wind2;//第二天的风力
    private String trendImage20;//第二天天气趋势开始图片名称
    private String trendImage21;//第二天天气趋势开始图片名称
    private String temperature3;//第三天的气温；
    private String condition3;//第三天的概况
    private String wind3;//第三天的分离
    private String trendImage30;//第三天天气趋势开始图片名称
    private String trendImage31;//第三天天气趋势开始图片名称
    private String history;//查询的城市或地区的介绍

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityPicture() {
        return cityPicture;
    }

    public void setCityPicture(String cityPicture) {
        this.cityPicture = cityPicture;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getTrendImage10() {
        return trendImage10;
    }

    public void setTrendImage10(String trendImage10) {
        this.trendImage10 = trendImage10;
    }

    public String getTrendImage11() {
        return trendImage11;
    }

    public void setTrendImage11(String trendImage11) {
        this.trendImage11 = trendImage11;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getLifeIndex() {
        return lifeIndex;
    }

    public void setLifeIndex(String lifeIndex) {
        this.lifeIndex = lifeIndex;
    }

    public String getTemperature2() {
        return temperature2;
    }

    public void setTemperature2(String temperature2) {
        this.temperature2 = temperature2;
    }

    public String getCondition2() {
        return condition2;
    }

    public void setCondition2(String condition2) {
        this.condition2 = condition2;
    }

    public String getWind2() {
        return wind2;
    }

    public void setWind2(String wind2) {
        this.wind2 = wind2;
    }

    public String getTrendImage20() {
        return trendImage20;
    }

    public void setTrendImage20(String trendImage20) {
        this.trendImage20 = trendImage20;
    }

    public String getTrendImage21() {
        return trendImage21;
    }

    public void setTrendImage21(String trendImage21) {
        this.trendImage21 = trendImage21;
    }

    public String getTemperature3() {
        return temperature3;
    }

    public void setTemperature3(String temperature3) {
        this.temperature3 = temperature3;
    }

    public String getCondition3() {
        return condition3;
    }

    public void setCondition3(String condition3) {
        this.condition3 = condition3;
    }

    public String getWind3() {
        return wind3;
    }

    public void setWind3(String wind3) {
        this.wind3 = wind3;
    }

    public String getTrendImage30() {
        return trendImage30;
    }

    public void setTrendImage30(String trendImage30) {
        this.trendImage30 = trendImage30;
    }

    public String getTrendImage31() {
        return trendImage31;
    }

    public void setTrendImage31(String trendImage31) {
        this.trendImage31 = trendImage31;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", cityPicture='" + cityPicture + '\'' +
                ", temperature='" + temperature + '\'' +
                ", condition='" + condition + '\'' +
                ", wind='" + wind + '\'' +
                ", trendImage10='" + trendImage10 + '\'' +
                ", trendImage11='" + trendImage11 + '\'' +
                ", live='" + live + '\'' +
                ", lifeIndex='" + lifeIndex + '\'' +
                ", temperature2='" + temperature2 + '\'' +
                ", condition2='" + condition2 + '\'' +
                ", wind2='" + wind2 + '\'' +
                ", trendImage20='" + trendImage20 + '\'' +
                ", trendImage21='" + trendImage21 + '\'' +
                ", temperature3='" + temperature3 + '\'' +
                ", condition3='" + condition3 + '\'' +
                ", wind3='" + wind3 + '\'' +
                ", trendImage30='" + trendImage30 + '\'' +
                ", trendImage31='" + trendImage31 + '\'' +
                ", history='" + history + '\'' +
                '}';
    }
}