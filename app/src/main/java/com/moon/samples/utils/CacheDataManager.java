package com.moon.samples.utils;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;

import java.io.File;
import java.math.BigDecimal;

/**
 * author: moon
 * created on: 17/10/25 下午4:23
 * description:
 */
public class CacheDataManager {


    /**
     * 获取缓存大小（不包括图片）
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {

        long cacheSize = getFolderSize(context.getCacheDir());

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            cacheSize += getFolderSize(context.getExternalCacheDir());

        }

        return getFormatSize(cacheSize);
    }

    /**
     * 获取缓存大小（包括图片）
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSizeWithGlide(Context context) throws Exception {

        long cacheSize = getFolderSize(context.getCacheDir());

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            cacheSize += getFolderSize(context.getExternalCacheDir());

        }

        cacheSize += getFolderSize(new File(context.getCacheDir() + "/"+ InternalCacheDiskCacheFactory
                .DEFAULT_DISK_CACHE_DIR));

        return getFormatSize(cacheSize);
    }




    public static void clearAllCache(Context context) {

        //内部缓存
        UDebug.i("cacheDir del 结果" + deleteDir(context.getCacheDir()));

        //sd卡缓存
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            ;
            UDebug.i("sd卡 dir del 结果" + deleteDir(context.getExternalCacheDir()));

        }

        //GLIDE缓存
        GlideCacheUtil.getInstance().clearImageAllCache(context);

    }

    private static boolean deleteDir(File dir) {

        UDebug.i("deleteDir->" + dir.getAbsolutePath());

        if (dir != null && dir.isDirectory()) {

            String[] children = dir.list();

            for (int i = 0; i < children.length; i++) {

                boolean success = deleteDir(new File(dir, children[i]));

                if (!success) {

                    return false;

                }

            }

        }

        return dir.delete();

    }

// 获取文件

// Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/

// 目录，一般放一些长时间保存的数据

// Context.getExternalCacheDir() -->

// SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据

    public static long getFolderSize(File file) throws Exception {

        long size = 0;

        try {

            File[] fileList = file.listFiles();

            for (int i = 0; i < fileList.length; i++) {

// 如果下面还有文件

                if (fileList[i].isDirectory()) {

                    size = size + getFolderSize(fileList[i]);

                } else {

                    size = size + fileList[i].length();

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return size;

    }

    /**
     * 格式化单位
     *
     * @param size
     */

    public static String getFormatSize(double size) {

        double kiloByte = size / 1024;

        if (kiloByte < 1) {

            return size + "Byte";

        }

        double megaByte = kiloByte / 1024;

        if (megaByte < 1) {

            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));

            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";

        }

        double gigaByte = megaByte / 1024;

        if (gigaByte < 1) {

            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));

            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";

        }

        double teraBytes = gigaByte / 1024;

        if (teraBytes < 1) {

            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));

            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";

        }

        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";

    }


}
