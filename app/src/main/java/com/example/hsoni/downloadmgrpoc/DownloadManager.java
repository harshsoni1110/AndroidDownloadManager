package com.example.hsoni.downloadmgrpoc;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HSoni on 6/21/2018.
 */

public class DownloadManager {
    private static DownloadManager mDownloadmManager;

    HashMap <Integer, DownloadInfoBean> mDownloadList;
    private static int i = 0;
    private DownloadManager (){
        mDownloadList = new HashMap<>();
    }

    public static DownloadManager getInstance (){
        if (mDownloadmManager == null) {
            mDownloadmManager = new DownloadManager();
        }
        return mDownloadmManager;
    }

    public int addToList (DownloadInfoBean downloadInfoBean){
        mDownloadList.put(++i, downloadInfoBean);
        return i;
    }

    public DownloadInfoBean getDownloadInfo (int downloadId){
        return mDownloadList.get(downloadId);
    }
}
