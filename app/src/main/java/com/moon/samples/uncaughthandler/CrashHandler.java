package com.moon.samples.uncaughthandler;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import com.moon.samples.utils.UDebug;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 当app 崩溃时捕获异常
 */
public class CrashHandler implements UncaughtExceptionHandler ,Runnable{

    private static final String LOG_PATH = "doingnow/crm/logs/";

    private static final String TAG = "CrashHandler";

    //系统默认的UncaughtException处理类
    private UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler实例
    private static CrashHandler INSTANCE = new CrashHandler();
    //程序的Context对象
    private Context mContext;
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new ArrayMap<>();

    //用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter;

    private Throwable ex ;

    /**
     * 保证只有一个CrashHandler实例
     */
    public CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context c
     */
    public void init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void doWithAfterCrash() {

    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex e
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        this.ex = ex;
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();

                doWithAfterCrash();

                Toast.makeText(mContext, "很抱歉,程序出现异常,即将重启", Toast.LENGTH_LONG).show();

                Looper.loop();

            }
        }.start();

        //保存日志文件
        new Thread(this).start();

        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx c
     */
    private void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                String device = Build.MODEL + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
                infos.put("device", device);
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex 异常内容
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private void saveCrashInfo2File(Throwable ex) {
        final StringBuilder sb = new StringBuilder(); // 日志内容
        formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key);
            sb.append("=");
            sb.append(value);
            sb.append("\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        if (ex != null) {
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            final String fileName = "crash-" + time + "-" + timestamp + ".log";

            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                UDebug.i("没有挂载sd卡");
                return ;
            }

            if (!requestSDPermission()) {
                UDebug.i("没有写卡权限");
                return ;
            }


            writeLog(fileName, sb);


            return ;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
    }

    private void writeLog(String fileName, StringBuilder sb) {

        String path = Environment.getExternalStorageDirectory().getPath()
                + File.separator + LOG_PATH;

        UDebug.i("log文件路径 = " + path);

        File dir = new File(path);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                return;
            }
        }

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(path + fileName);
            fos.write(sb.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean requestSDPermission() {
        return ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void run() {

        //收集设备参数信息
        collectDeviceInfo(mContext);

        saveCrashInfo2File(ex);
    }
}