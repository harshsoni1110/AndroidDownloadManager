package com.example.hsoni.downloadmgrpoc;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by HSoni on 6/21/2018.
 */

public class DownloadService extends Service {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    HashMap <Integer, Future> mDownloadThreads;
    @Override
    public void onCreate() {
        super.onCreate();
        mDownloadThreads = new HashMap<>();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int downloadId = intent.getIntExtra(DownloadConstant.DOWNLOAD_START_ID, -1);
        DownloadInfoBean downloadInfoBean = DownloadManager.getInstance().getDownloadInfo(downloadId);
        if (downloadId != -1) {
            NotificationHelper notificationHelper = NotificationHelper.getInstance(this);
            Notification.Builder notificationBuilder = notificationHelper.getNotification(downloadInfoBean.getDownloadTitle(), null, null, true, true);
            notificationBuilder.setProgress(100, 0, false);

            DownloadRunnable downloadRunnable = new DownloadRunnable(downloadId, this);
            Future downloadFuture = executorService.submit(downloadRunnable);
            mDownloadThreads.put(downloadId, downloadFuture);

            startForeground(downloadId, notificationBuilder.build());
        }
        stopSelf(startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SERVICEDOWNLOAD",executorService.isShutdown()+"");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
