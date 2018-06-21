package com.example.hsoni.downloadmgrpoc;

import android.arch.lifecycle.MutableLiveData;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HSoni on 6/21/2018.
 */

public class DownloadInfoBean implements Parcelable{

    public DownloadInfoBean() {
        this.progress = new MutableLiveData<>();
    }

    DownloadInfoBean (Parcel source){
        this.downloadId = source.readInt();
//        this.progress.setValue(( (MutableLiveData<Integer>)source.readValue(MutableLiveData.class.getClassLoader())).getValue() );
//        this.mProgressListener = (ProgressListener) source.readValue(ProgressListener.class.getClassLoader());
    }
    private int downloadId;
    private ProgressListener mProgressListener;
    private MutableLiveData<Integer> progress;

    public int getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(int downloadId) {
        this.downloadId = downloadId;
    }

    public ProgressListener getmProgressListener() {
        return mProgressListener;
    }

    public void setmProgressListener(ProgressListener mProgressListener) {
        this.mProgressListener = mProgressListener;
    }

    public static Creator<DownloadInfoBean> CREATOR = new Creator<DownloadInfoBean>() {
        @Override
        public DownloadInfoBean createFromParcel(Parcel source) {
            return new DownloadInfoBean(source);
        }

        @Override
        public DownloadInfoBean[] newArray(int size) {
            return new DownloadInfoBean[size];
        }
    };

    @Override
    public int describeContents() {
            return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(downloadId);
//        dest.writeValue(progress);
//        dest.writeValue(mProgressListener);
    }

    public MutableLiveData<Integer> getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress.setValue(progress);
    }
}
