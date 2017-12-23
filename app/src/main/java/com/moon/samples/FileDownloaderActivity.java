package com.moon.samples;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.moon.samples.base.BaseActivity;
import com.moon.samples.utils.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FileDownloaderActivity extends BaseActivity {

    private static final String url = "http://cdn.lianke-pub.hy-sport.cn/apk/crm_release_yundonghui_v3.2.4.apk";
//    private static final String url = "http://cdn.llsapp.com/android/LLS-v4.0-595-20160908-143200.apk";
    private String filePath;
    private String llsApkFilePathDir;

    private int downloadId;
    private TextView content , filePathTv;

    private boolean aBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_downloader);

        FileDownloader.setup(this);

        filePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "yundonghui" + File.separator +
                "crm.apk";

//        RxPermissions.getInstance(this)
//                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
//                .subscribe(new Observer<Boolean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Boolean aBoolean) {
//                        if (aBoolean){
//                            //初始化文件路径
//                            filePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "yundonghui" + File.separator +
//                                    "crm.apk";
//
//                        }else {
//                            Toast.makeText(FileDownloaderActivity.this, "您没有授权该权限，请在设置中打开授权", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        content = findViewById(R.id.downlaod_content);
        filePathTv = findViewById(R.id.file_path);


        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start

                downloadId = createDownloadTask().start();

                filePathTv.setText("");
            }
        });



        findViewById(R.id.pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pause
                FileDownloader.getImpl().pause(downloadId);
            }
        });


        findViewById(R.id.delFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pause

                if (new File(filePath).exists()){
                    new File(filePath).delete();
                    new File(FileDownloadUtils.getTempPath(filePath)).delete();

                    filePathTv.setText("");
                }else {
                    Logger.i(filePath + "不存在");
                }
            }
        });

    }


    private BaseDownloadTask createDownloadTask(){

        return FileDownloader.getImpl().create(url)
                .setPath(filePath,false)
                .setCallbackProgressTimes(300)
                .setListener(new FileDownloadSampleListener(){

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        super.completed(task);

                        filePathTv.setText("file = " + task.getPath());
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.progress(task, soFarBytes, totalBytes);

                        content.setText("speed" + task.getSpeed()
                        +"\n"
                        + "download progress : " + String.format("sofar: %d total: %d", soFarBytes, totalBytes));

                    }
                });
    }

    private BaseDownloadTask downloadTask;


    @Override
    protected String getActionTitle() {
        return "文件下载";
    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    @Override
    protected void onPause() {
        super.onPause();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
