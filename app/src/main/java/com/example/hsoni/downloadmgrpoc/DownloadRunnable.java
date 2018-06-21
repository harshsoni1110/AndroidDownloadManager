package com.example.hsoni.downloadmgrpoc;

import android.app.Notification;
import android.content.Context;
import android.os.Message;
import android.util.Log;

/**
 * Created by HSoni on 6/21/2018.
 */

public class DownloadRunnable implements Runnable{

    DownloadInfoBean mDownloadInfoBean;
    Context context;
    public DownloadRunnable(DownloadInfoBean downloadInfoBean, Context context) {
        mDownloadInfoBean = downloadInfoBean;
        this.context = context;
    }

    @Override
    public void run() {
        ProgressHandler progressHandler = new ProgressHandler(mDownloadInfoBean);
        for (int i = 1; i <= 100; i++){
            try {
                Message message = new Message();
                message.what = DownloadConstant.DOWNLOAD_PROGRESS;
                message.arg1 = i;
                NotificationHelper notificationHelper = NotificationHelper.getInstance(context);
                Notification.Builder notificationBuilder;
                if (i == 100)
                    notificationBuilder =  notificationHelper.getNotification("Download " + mDownloadInfoBean.getDownloadId() , "Completed" , null, false, true);
                else
                    notificationBuilder =  notificationHelper.getNotification("Download " + mDownloadInfoBean.getDownloadId() , i + "%" , null, true, true);
                notificationBuilder.setProgress(100,i, false);
                notificationHelper.notify(mDownloadInfoBean.getDownloadId(), notificationBuilder);
                Log.d("DOWNLOAD", i+"");
                progressHandler.sendMessage(message);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}