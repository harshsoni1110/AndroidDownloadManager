package com.example.hsoni.downloadmgrpoc;

import java.net.MalformedURLException;
import java.util.HashMap;

/**
 * Created by HSoni on 6/21/2018.
 */

public class DownloadManager {
    private static DownloadManager mDownloadManager;

    HashMap <Integer, DownloadInfoBean> mDownloadList;
    private static int i = 0;
    private DownloadManager (){
        mDownloadList = new HashMap<>();
    }

    public static DownloadManager getInstance (){
        if (mDownloadManager == null) {
            mDownloadManager = new DownloadManager();
        }
        return mDownloadManager;
    }

    public DownloadRequest download (String url, String dirPath, String fileName){
        DownloadRequest downloadRequest = null;
        try {
            downloadRequest = new DownloadRequest(url, dirPath, fileName);
        } catch (MalformedURLException e) {
            //Show error in notification or wherever required
            downloadRequest = null;
            e.printStackTrace();
        }
        return downloadRequest;
    }
    public void addToDownloads (DownloadInfoBean downloadInfoBean){
        mDownloadList.put(downloadInfoBean.getDownloadId(), downloadInfoBean);
    }

    public DownloadInfoBean getDownloadInfo (int downloadId){
        return mDownloadList.get(downloadId);
    }
}
