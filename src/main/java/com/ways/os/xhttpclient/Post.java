package com.ways.os.xhttpclient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by wwzy on 16/3/14.
 */
public class Post implements XRequest{
    private XResponse response;
    public Post(){
       response=new XResponse();
    }

    @Override
    public XResponse execute() {

        return this.response;
    }

    public static  class Builder{
        private URL url;
        private HttpURLConnection connection;
        private Post post;
        private final static  int MILLIES=1000;
        private HashMap<String,String> headerkV;
        private int connectionTimeout,readTimeout;
        public Builder(){
            init();
        }
        private void init(){
            post=new Post();

            this.connectionTimeout=30*MILLIES;
            this.readTimeout=15*MILLIES;
        }
        public Builder timeout(int cto,int rto){
            this.connectionTimeout=cto;
            this.readTimeout=rto;
            return this;
        }

        public Builder url(String urlPath) throws IOException{
            url=new URL(urlPath);

            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod(POST);

            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.addRequestProperty(MimeTypeUtils.CONTENT_TYPE, MimeTypeUtils.MIME_FORM);

            connection.setConnectTimeout(this.connectionTimeout);
            connection.setReadTimeout(this.readTimeout);

            return this;
        }

        public Builder body(XHttpBody body) throws IOException {
            DataOutputStream dos=new DataOutputStream(connection.getOutputStream());
            byte[] postDatas=body.getBytes();
            dos.write(postDatas, 0, postDatas.length);
            dos.flush();
            dos.close();


            post.response.responseCode=connection.getResponseCode();
            post.response.inputStream=connection.getInputStream();

            return this;
        }
        public Builder header(HashMap<String,String> kv){

            return  this;
        }
        public Post create(){
          return post;
        }
        private void setHeader(HashMap<String,String>kv){
             if(kv==null||kv.size()==0){

             }

        }

    }
}
