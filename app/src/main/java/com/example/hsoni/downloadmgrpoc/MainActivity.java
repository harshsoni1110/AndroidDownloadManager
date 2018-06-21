package com.example.hsoni.downloadmgrpoc;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    DownloadInfoBean downloadInfoBean;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        final ProgressBar progressBar1 = findViewById(R.id.progressBar1);

        DownloadInfoBean downloadInfoBean1 = new DownloadInfoBean();
        downloadInfoBean = new DownloadInfoBean();

        downloadInfoBean.getProgress().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                progressBar.setProgress(integer);
            }
        });

        com.example.hsoni.downloadmgrpoc.DownloadManager downloadManager = com.example.hsoni.downloadmgrpoc.DownloadManager.getInstance();
        int downloadId = downloadManager.addToList(downloadInfoBean);
        downloadInfoBean.setDownloadId(downloadId);
        Intent intent  = new Intent(this, DownloadService.class);
        intent.putExtra("downloadId", downloadId);
        startService(intent);

        downloadInfoBean1.getProgress().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                progressBar1.setProgress(integer);
            }
        });

        int downloadId1 = downloadManager.addToList(downloadInfoBean1);
        downloadInfoBean1.setDownloadId(downloadId1);

        intent  = new Intent(this, DownloadService.class);
        intent.putExtra("downloadId", downloadId1);
        startService(intent);


    }

    private void downloadImage (){
        DownloadManager downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);

        DownloadManager.Request requestDownload = new DownloadManager.Request(Uri.parse("https://r2---sn-nx5pm-3gge.googlevideo.com/videoplayback?mm=31%2C29&lmt=1528194818459816&ratebypass=yes&c=WEB&ipbits=0&mn=sn-nx5pm-3gge%2Csn-n8v7zn7y&ip=185.134.233.121&key=yt6&mv=m&mt=1529402269&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cnh%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&fvip=4&ms=au%2Crdu&pl=22&id=o-AObdjYTYAnK_NaU-apX06SvVew30pcAO9IIVfSdYDhmN&mime=video%2Fmp4&ei=6tMoW9zMJ9nX7AS4sbCgAQ&requiressl=yes&signature=C89DADD31360CF2E7ECDE6C0D1B625F51D679283.59D5857ABA001A3B02425A696746B4A1584CFF88&itag=22&expire=1529423946&nh=%2CIgpwcjAyLnN2bzA2KgkxMjcuMC4wLjE&fexp=23709359&initcwndbps=625000&source=youtube&dur=544.229&video_id=yUegdy0wNnI&title=A+Day+in+the+Life+of+Software+Engineer%2C+Jakarta+-+Indonesia"));//Uri.parse(downloadUrl));
        requestDownload.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/GadgetSaint/"  + "/" + "Sample_" + 1 + ".mp3");
        requestDownload.setTitle("Contetype file downloading");

        downloadManager.enqueue(requestDownload);
        downloadManager.enqueue(requestDownload);
        downloadManager.enqueue(requestDownload);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    downloadImage();
                }
                break;
        }
    }

    public ProgressListener getDownloadProgressListener (){
        return downloadInfoBean.getmProgressListener();
    }
}
