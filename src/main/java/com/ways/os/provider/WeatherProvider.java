package com.ways.os.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.ways.os.db.DBHelper;
import com.ways.os.db.WeatherContract;

/**
 * Created by wwzy on 16/3/15.
 */
public class WeatherProvider extends ContentProvider {
    private DBHelper dbHelper=null;
    private ContentResolver contentProvider=null;

    private static final String AUTHORITIES="com.ways.os.provider.WeatherProvider";
    private static final String ITEM_PATH="item";
    private static final String ITEM_POSTION_PATH="item/#";

    public final static String CONTENT_TYPE="vnd.android.cursor.dir/";
    public final static String CONTENT_TYPE_ITEM="vnd.android.cursor.item/";

    public final static Uri CONTENT_URI=android.net.Uri.parse("content://"+AUTHORITIES+"/item");


    public static final UriMatcher uriMatcher;
    private  static  final int ITEMS=1;
    private static  final int ITEM=2;

    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITIES,ITEM_PATH,ITEMS);
        uriMatcher.addURI(AUTHORITIES,ITEM_POSTION_PATH,ITEM);
    }

    @Override
    public boolean onCreate() {
        Context context=getContext();
        dbHelper=new DBHelper(context);
        contentProvider=context.getContentResolver();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase readDB=dbHelper.getReadableDatabase();
        switch (uriMatcher.match(uri)){
            case ITEMS:
                return  readDB.query(WeatherContract.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            case ITEM:
                long queryid=ContentUris.parseId(uri);
                String where=WeatherContract.ID_F+queryid;
                where+=!TextUtils.isEmpty(selection)?" and ("+selection+")" : "";
                return readDB.query(WeatherContract.TABLE_NAME,projection,where,selectionArgs,null,null,sortOrder);
            default:
                throw new IllegalArgumentException("Unknown URI "+uri);

        }

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){

            case ITEMS:
                return  CONTENT_TYPE;
            case ITEM:
                return CONTENT_TYPE_ITEM;
           default:
               throw new IllegalArgumentException("Unkown URI"+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase writeDB=dbHelper.getWritableDatabase();
        long id=0;
        switch (uriMatcher.match(uri)){
            case ITEMS:
                id=writeDB.insert(WeatherContract.TABLE_NAME,null,values);
                return ContentUris.withAppendedId(uri,id);
            case ITEM:
                id=writeDB.insert(WeatherContract.TABLE_NAME,null,values);
                String path=uri.toString();
                return Uri.parse(path.substring(0,path.lastIndexOf("/"))+id);
            default:
                throw new IllegalArgumentException("Unknown URI "+uri);

        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase writeDB=dbHelper.getWritableDatabase();
        int count=0;
        switch (uriMatcher.match(uri)){
            case ITEMS:
                count=writeDB.delete(WeatherContract.TABLE_NAME,selection,selectionArgs);
                break;
            case ITEM:
              long deleteid= ContentUris.parseId(uri);
                String where=WeatherContract.ID_F+"="+deleteid;
                where+=!TextUtils.isEmpty(selection)?" and("+selection+")":"";
                count=writeDB.delete(WeatherContract.TABLE_NAME,where,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI "+uri);
        }
        writeDB.close();
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase writeDB=dbHelper.getWritableDatabase();
        int count=0;
        switch (uriMatcher.match(uri)){
            case ITEMS:
                count=writeDB.update(WeatherContract.TABLE_NAME,values,selection,selectionArgs);
                break;
            case ITEM:
                long updateid=ContentUris.parseId(uri);
                String where=WeatherContract.ID_F+"="+updateid;
                where+=!TextUtils.isEmpty(selection)?" and ("+selection+")":"";
                count=writeDB.update(WeatherContract.TABLE_NAME,values,where,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI "+uri);

        }

        return count;
    }
}
