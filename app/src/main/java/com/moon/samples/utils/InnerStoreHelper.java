package com.moon.samples.utils;

import android.content.Context;

/**
 * author: moon
 * created on: 17/12/25 下午4:32
 * description:  内存存储的工具类， 如 data/data/包名/cache等
 */
public class InnerStoreHelper {


    /**
     * data/data/包名/cache 路径 （不带最后斜线 ）
     * @param context
     * @return
     */
    public static String getInnerCacheDir(Context context){
        return context.getCacheDir().getAbsolutePath();
    }

    /**
     * data/data/包名/files 路径 （不带最后斜线 ）
     * @param context
     * @return
     */
    public static String getInnerFilesDir(Context context){
        return context.getFilesDir().getAbsolutePath();
    }



}
