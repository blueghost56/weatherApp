package com.ways.os.helper;

import android.util.Log;

/**
 * Created by wwzy on 16/3/17.
 */
public final class DebugUtils {
    private final static Boolean DEBUG=true;

    public static  void dlog(String tag,String msg){
        if(DEBUG) {
            Log.i(tag, msg);
        }
    }
}
