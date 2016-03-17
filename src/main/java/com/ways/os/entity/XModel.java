package com.ways.os.entity;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by csl on 16/3/16.
 */
public abstract class XModel implements Serializable{
    public void setFieldValue(String fieldName,String value){
        Field field= null;
        try {
            field = getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(this,value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
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
