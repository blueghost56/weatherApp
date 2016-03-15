package com.ways.os.xhttpclient;

/**
 * Created by wwzy on 16/3/14.
 */
public class XHttpClient {
    private XHttpConfigure configure;
    private XRequest request;
    public XHttpClient(){

    }
    public XHttpClient(XHttpConfigure configure){
      this.configure=configure;
    }

    public XHttpClient newRequest(XRequest request){
          this.request=request;
        return this;
    }

    public XResponse run(){

        return this.request.execute();
    }

}
