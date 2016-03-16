package com.ways.os.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wwzy on 16/3/15.
 */
public class Cookie {
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedPreferences;
    public Cookie(){

    }
  public static class Builder{
      private Cookie mCookie;
      private Context mContext;
      private SharedPreferences mSharedPreferences;

      public Builder(Context context){
           mCookie=new Cookie();
          this.mContext=context;
      }
      public Builder name(String name){
          mSharedPreferences=mContext.getSharedPreferences(name,Context.MODE_PRIVATE);
          mCookie.mEditor=mSharedPreferences.edit();
          mCookie.mSharedPreferences=mSharedPreferences;
          return this;
      }
      public Cookie create(){
          return mCookie;
      }

   }

    public void save(){
      mEditor.commit();
    }

}
