package com.ways.os.entity;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import java.lang.reflect.Field;

/**
 * Created by wwzy on 16/3/16.
 */
public abstract class XModel {

    public void save(Context context,Uri uri){
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
       context.getContentResolver().insert(uri,contentValues);
    }
}
