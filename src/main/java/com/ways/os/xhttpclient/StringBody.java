package com.ways.os.xhttpclient;

import java.util.HashMap;

/**
 * Created by wwzy on 16/3/14.
 */
public class StringBody implements XHttpBody {
    private HashMap<String,String> params;
    public StringBody(){}
    public StringBody(HashMap<String,String> params){
        this.params=params;
    }

    @Override
    public byte[] getBytes() {
            return XHttpBodyUtils.convertParamsToBytes(this.params);
    }

}
