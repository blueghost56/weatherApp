package com.ways.os.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ways.os.entity.Weather;

import java.lang.reflect.Field;

/**
 * Created by wwzy on 16/3/15.
 */
public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context,WeatherContract.DB_NAME, null, WeatherContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final  String sql=makeCreateTableSQL(Weather.class,WeatherContract.TABLE_NAME);
          db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL(WeatherContract.UPDATE_SQL);
      onCreate(db);
    }
    private  String makeCreateTableSQL(Class<?>klass,String tableName){
        Field[] fields=klass.getDeclaredFields();
          String sql=String.format("CREATE TABLE %s (_id integer primary key auto_increment,",tableName);

         StringBuilder sb=new StringBuilder(sql);
         for(Field field:fields){
             field.setAccessible(true);
             sb.append(field.getName()).append(" text").append(WeatherContract.COMMA);
         }
        sb.deleteCharAt(sb.lastIndexOf(WeatherContract.COMMA));
        sb.append(");");
       return sb.toString();
    }

}
