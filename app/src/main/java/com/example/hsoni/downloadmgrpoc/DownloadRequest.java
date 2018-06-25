package com.example.hsoni.downloadmgrpoc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by HSoni on 6/22/2018.
 */

public class DownloadRequest {
    private URL url;
    private String description;
    private String dirPath;
    private String fileName;
    private HashMap <String, String> headers;

    private final static String COOKIE = "cookie";
    private final static String AGENT = "user-agent";

    public DownloadRequest(String url, String dirPath, String fileName) throws MalformedURLException {
        //TODO remove url
        this.url = null;// new URL(url);
        this.dirPath = dirPath;
        this.fileName = fileName;
        this.headers = new HashMap<>();
    }

    public DownloadRequest setDescription (String description) {
        this.description = description;
        return this;
    }
    public String getDescription (){
        return description;
    }
    public DownloadRequest setCookies (String cookies){
        headers.put(COOKIE, cookies);
        return this;
    }

    public DownloadInfoBean build(){
        DownloadInfoBean downloadInfoBean = new DownloadInfoBean(this);
        DownloadManager.getInstance().addToDownloads(downloadInfoBean);
        return downloadInfoBean;
    }

    public URL getUrl() {
        return url;
    }

    public String getDirPath() {
        return dirPath;
    }

    public String getFileName() {
        return fileName;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }
}
