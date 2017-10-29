package com.moon.samples.utils;

import android.os.Environment;

import java.io.File;
import java.util.Map;

/**
 * author: moon
 * created on: 17/10/27 上午11:51
 * description:
 */
public class FileUtil {

    public static String getSoftwareStorageDirectory() {
        String strDirectory = null;
        Map<String,String> sysInfo=System.getenv();
        String sd_defult = sysInfo.get("SECONDARY_STORAGE");
        if (!CheckDirectoryExists(sd_defult))
        {
            sd_defult = Environment.getExternalStorageDirectory().getPath();
        }
        return strDirectory;
    }

    //判断检出目录是否存在
    private static boolean CheckDirectoryExists(String fileName) {
        if (fileName == null || fileName.length()<=0) {
            return false;
        }
        File temFile = new File(fileName);
        File[] childFiles = null;

        if (temFile != null)
            childFiles = temFile.listFiles();

        if (childFiles == null) {
            return false;
        }

        //目录是否可以写
        File testFile = new File(temFile, "com_testDir");
        if (!testFile.exists()) {
            if (!testFile.mkdir()) {
                return false;
            }
            testFile.delete();
        }

        return true;
    }
}
