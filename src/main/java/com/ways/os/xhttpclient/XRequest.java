package com.ways.os.xhttpclient;

/**
 * Created by wwzy on 16/3/14.
 */
public interface XRequest {
    public final  static String POST="POST";
    public final  static String GET="GET";
    public XResponse execute();
}
