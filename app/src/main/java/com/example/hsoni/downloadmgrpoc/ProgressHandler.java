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
    public ProgressHandler(DownloadInfoBean downloadInfoBean) {
        super(Looper.getMainLooper());
        this.downloadInfoBean = downloadInfoBean;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case DownloadConstant.DOWNLOAD_PROGRESS:

                int progress = (int)msg.arg1;
                downloadInfoBean.setProgress(progress);
               /* if (mProgressListener != null)
                    mProgressListener.postProgress(progress);*/
                break;
        }
    }
}
