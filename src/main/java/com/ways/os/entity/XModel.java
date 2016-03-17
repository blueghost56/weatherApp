package com.ways.os.entity;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import java.lang.reflect.Field;

/**
 * Created by csl on 16/3/16.
 */
public abstract class XModel {

    public void save(Context context,Uri uri){
       context.getContentResolver().insert(uri,wrapContentValues());
    }
    public void update(Context context,Uri uri,String where,String[] selectionArgs){
        context.getContentResolver().update(uri,wrapContentValues(), where, selectionArgs);
    }
    
    private ContentValues wrapContentValues(){
        Field[] fields=getClass().getDeclaredFields();
        ContentValues contentValues=new ContentValues();
        for(Field field:fields){
            field.setAccessible(true);
            try {
                contentValues.put(field.getName(),(String)field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return contentValues;
    }
}
