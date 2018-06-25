package com.example.hsoni.downloadmgrpoc;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DownloadInfoBean downloadInfoBean;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        final TextView textView = findViewById(R.id.txtStatus);
        final ProgressBar progressBar1 = findViewById(R.id.progressBar1);
        final TextView textView1 = findViewById(R.id.txtStatus1);

        DownloadManager downloadManager = DownloadManager.getInstance();
        downloadManager
            .download("",DownloadHelper.getBaseFolder(this),"Content_file.txt")
            .setDescription("Download Content")
            .setCookies("")
            .build()
            .startDownloading(this)
            .getUpdates().observe(this, new Observer<DownloadInfoBean>() {
            @Override
            public void onChanged(@Nullable DownloadInfoBean downloadInfoBean) {
                if (downloadInfoBean.getProgress().getValue() != null)
                    progressBar.setProgress(downloadInfoBean.getProgress().getValue());
                textView.setText(downloadInfoBean.getStatus().getValue());
            }
        });
//            .getProgress().observe(this, new Observer<Integer>() {
//                @Override
//                public void onChanged(@Nullable Integer integer) {
//                    //UI update
//                    progressBar.setProgress(integer);
//
//                }
//            });


        downloadManager
                .download("","","")
                .setDescription("Download Content1")
                .setCookies("")
                .build()
                .startDownloading(this)
                .getUpdates().observe(this, new Observer<DownloadInfoBean>() {
                    @Override
                    public void onChanged(@Nullable DownloadInfoBean downloadInfoBean) {
                        if (downloadInfoBean.getProgress().getValue() != null)
                            progressBar1.setProgress(downloadInfoBean.getProgress().getValue());
                        textView1.setText(downloadInfoBean.getStatus().getValue());

                    }
                });
/*
                .getProgress().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                //UI update
                progressBar1.setProgress(integer);
            }
        });
*/

/*
        DownloadInfoBean downloadInfoBean1 = new DownloadInfoBean();
        downloadInfoBean = new DownloadInfoBean();

        downloadInfoBean.getProgress().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                progressBar.setProgress(integer);
            }
        });
*/

//        com.example.hsoni.downloadmgrpoc.DownloadManager downloadManager = com.example.hsoni.downloadmgrpoc.DownloadManager.getInstance();
//        int downloadId = downloadManager.addToList(downloadInfoBean);
//        downloadInfoBean.setDownloadId(downloadId);
//        Intent intent  = new Intent(this, DownloadService.class);
//        intent.putExtra("downloadId", downloadId);
//        startService(intent);
//
//        downloadInfoBean1.getProgress().observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(@Nullable Integer integer) {
//                progressBar1.setProgress(integer);
//            }
//        });
//
//        int downloadId1 = downloadManager.addToList(downloadInfoBean1);
//        downloadInfoBean1.setDownloadId(downloadId1);
//
//        intent  = new Intent(this, DownloadService.class);
//        intent.putExtra("downloadId", downloadId1);
//        startService(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((NotificationManager)getSystemService(NOTIFICATION_SERVICE)).cancelAll();
    }
}
