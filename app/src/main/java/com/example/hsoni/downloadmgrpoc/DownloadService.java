package com.example.hsoni.downloadmgrpoc;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by HSoni on 6/21/2018.
 */

public class DownloadService extends Service {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int downloadId = intent.getIntExtra("downloadId", -1);
        NotificationHelper notificationHelper = NotificationHelper.getInstance(this);
        Notification.Builder notificationBuilder =  notificationHelper.getNotification("Download " + downloadId, null , null, true, true);

        DownloadRunnable downloadRunnable = new DownloadRunnable(DownloadManager.getInstance().getDownloadInfo(downloadId), this);
        executorService.submit(downloadRunnable);
        notificationBuilder.setProgress(100,0, false);
        notificationHelper.notify(downloadId, notificationBuilder);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
