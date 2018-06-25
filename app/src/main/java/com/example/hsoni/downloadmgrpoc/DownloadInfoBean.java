package com.example.hsoni.downloadmgrpoc;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;

import java.net.URL;
import java.util.HashMap;

/**
 * Created by HSoni on 6/21/2018.
 */

public class DownloadInfoBean{

    private static int i = 0;
    private int downloadId;
    private String downloadTitle;
    private URL url;
    private String directoryPath;
    private String downloadFileName;
    private HashMap<String, String > headers;
    private long totalBytes;
    private long bytesDownloaded;
    private MutableLiveData<String> status;
    private MutableLiveData<Integer> progress;

    private MediatorLiveData<DownloadInfoBean> updateThis;
    public DownloadInfoBean() {
        //Db entries

    }

    public DownloadInfoBean(DownloadRequest downloadRequest){
        this.downloadId = ++i;
        this.downloadTitle = downloadRequest.getDescription();
        this.url = downloadRequest.getUrl();
        this.directoryPath = downloadRequest.getDirPath();
        this.downloadFileName = downloadRequest.getFileName();
        this.headers = downloadRequest.getHeaders();
        this.progress = new MutableLiveData<>();
        this.progress.setValue(0);
        this.totalBytes = 0;
        this.bytesDownloaded = 0;
        this.status = new MutableLiveData<>();
        this.status.setValue(getDownloadStatus (DownloadConstant.STATUS_PENDING));
        this.progress = new MutableLiveData<Integer>();
        this.updateThis = new MediatorLiveData<>();
        this.updateThis.setValue(this);
        this.updateThis.addSource(this.progress, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                updateThis.setValue(DownloadInfoBean.this);
            }
        });

        this.updateThis.addSource(status, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                updateThis.setValue(DownloadInfoBean.this);
            }
        });
    }

    public int getDownloadId() {
        return downloadId;
    }

    public String getDownloadTitle() {
        return downloadTitle;
    }

    public String getDirectoryPath(){
        return directoryPath;
    }

    public String getDownloadFileName(){
        return downloadFileName;
    }
    public void setDownloadId(int downloadId) {
        this.downloadId = downloadId;
    }


    public MutableLiveData<Integer> getProgress() {
        return progress;
    }

    public MediatorLiveData<DownloadInfoBean> getUpdates (){
        return updateThis;
    }
    public MutableLiveData<String> getStatus(){
        return status;
    }
    public void setProgress(int progress) {
        this.progress.setValue(progress);
    }

    public String getDownloadStatus (int downloadStatus) {
        switch (downloadStatus){
            case 0:
                return "Pending";
            case 1:
                return "Start";
            case 2:
                return "Downloading";
            case 3:
                return "Completed";
            default:
                return "Failed";
        }
    }
    public void setDownloadStatus (int status){

        this.status.setValue(getDownloadStatus(status));
    }



    public DownloadInfoBean startDownloading (Context context){
        setDownloadStatus(DownloadConstant.STATUS_START);
        DownloadHelper.scheduleDownload (context, this);
        return this;
    }
}
