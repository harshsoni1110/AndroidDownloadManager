package com.example.hsoni.downloadmgrpoc;

import android.content.Context;
import android.content.Intent;

/**
 * Created by HSoni on 6/22/2018.
 */

public class DownloadHelper {

/*
    private static DownloadHelper downloadHelper;
    public static DownloadHelper getInstance (){
        if (downloadHelper == null){
            downloadHelper = new DownloadHelper();
        }
        return downloadHelper;
    }
*/

    public static String getBaseFolder (Context context){
        return context.getFilesDir().getAbsolutePath();
    }
    public static void scheduleDownload (Context context, DownloadInfoBean downloadInfoBean){
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(DownloadConstant.DOWNLOAD_START_ID, downloadInfoBean.getDownloadId());
        context.startService(intent);
    }
}
