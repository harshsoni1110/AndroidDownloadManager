package com.example.hsoni.downloadmgrpoc;

import android.app.Notification;
import android.content.Context;
import android.os.Message;
import android.util.Log;

import java.io.File;

/**
 * Created by HSoni on 6/21/2018.
 */

public class DownloadRunnable implements Runnable{

    private DownloadInfoBean mDownloadInfoBean;
    private int downloadId;
    private Context context;
    public DownloadRunnable(int downloadId, Context context) {
        this.downloadId = downloadId;
        mDownloadInfoBean = DownloadManager.getInstance().getDownloadInfo(downloadId);
        this.context = context;
    }

    @Override
    public void run() {
        ProgressHandler progressHandler = new ProgressHandler(downloadId);
        String downloadFilePath = mDownloadInfoBean.getDirectoryPath() + "/" + mDownloadInfoBean.getDownloadFileName();
        File downloadFile = new File(mDownloadInfoBean.getDirectoryPath() + "/" + mDownloadInfoBean.getDownloadFileName());
        NotificationHelper notificationHelper = NotificationHelper.getInstance(context);
        Notification.Builder notificationBuilder;
        for (int i = 1; i <= 100; i++){
            try {
                Message message = new Message();
                message.what = DownloadConstant.DOWNLOAD_PROGRESS;
                message.arg1 = i;

                if (i == 100) {
                    message.arg2 = DownloadConstant.STATUS_COMPLETED;
                    notificationBuilder = notificationHelper.getNotification(mDownloadInfoBean.getDownloadTitle(), "Completed", null, false, true);
                }
                else {
                    message.arg2 = DownloadConstant.STATUS_IN_PROGRESS;
                    notificationBuilder = notificationHelper.getNotification(mDownloadInfoBean.getDownloadTitle(), i + "%", null, true, true);
                }
                notificationBuilder.setProgress(100,i, false);
                notificationHelper.notify(mDownloadInfoBean.getDownloadId(), notificationBuilder);
                Log.d("DOWNLOAD", i+"");
                progressHandler.sendMessage(message);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                mDownloadInfoBean.setDownloadStatus(DownloadConstant.STATUS_FAILURE);
            }
        }
//        mDownloadInfoBean.setDownloadStatus(DownloadConstant.STATUS_COMPLETED);
    }
}