package com.ways.os.xhttpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by wwzy on 16/3/14.
 */
public class XResponse {
    private final static  String ENCOD_UTF8="UTF8";
    protected int responseCode;
    protected InputStream inputStream;
    private BufferedReader bufferedReader;
    public XResponse(){

    }
    public XResponse body(){
        if(inputStream==null)return this;

        try {
             bufferedReader=new BufferedReader(new InputStreamReader(inputStream,ENCOD_UTF8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }
    public String string() throws IOException {

         String line="";
         if(bufferedReader==null)return line;
        StringBuilder sb=new StringBuilder();
          while((line=bufferedReader.readLine())!=null){
              sb.append(line);
          }
        return sb.toString();
    }
}
