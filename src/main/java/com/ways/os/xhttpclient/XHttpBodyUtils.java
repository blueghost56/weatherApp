package com.ways.os.xhttpclient;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by wwzy on 16/3/14.
 */
public final class XHttpBodyUtils {
    final static  String EQUAL_SYMBOL="=";
    final static  String CONNECTOR="&";
    public static byte[] convertParamsToBytes(HashMap<String,String> params){
        if(params==null|params.size()==0)return new byte[0];
        StringBuilder sb=new StringBuilder();
        Set<Map.Entry<String,String>> entries=params.entrySet();

        for(Map.Entry<String,String> entry:entries){
           sb.append(entry.getKey()).append(EQUAL_SYMBOL).append(entry.getValue()).append(CONNECTOR);

        }

        sb.deleteCharAt(sb.lastIndexOf(CONNECTOR));
     return sb.toString().getBytes();
    }
}
