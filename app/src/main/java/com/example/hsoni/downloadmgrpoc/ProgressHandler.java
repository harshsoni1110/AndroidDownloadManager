package com.example.hsoni.downloadmgrpoc;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by HSoni on 6/21/2018.
 */

public class ProgressHandler extends Handler {
    private DownloadInfoBean downloadInfoBean;
    public ProgressHandler(int downloadId) {
        super(Looper.getMainLooper());
        this.downloadInfoBean = DownloadManager.getInstance().getDownloadInfo(downloadId);
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case DownloadConstant.DOWNLOAD_PROGRESS:
                int progress = msg.arg1;
                int status = msg.arg2;
                downloadInfoBean.setDownloadStatus(status);
                downloadInfoBean.setProgress(progress);
                break;
        }
    }
}
