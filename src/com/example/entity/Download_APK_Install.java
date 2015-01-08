package com.example.entity;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by LiesLee on 2015/1/5.
 * Email: LiesLee@foxmail.com
 */
public class Download_APK_Install implements Serializable{
    /**  */
	private static final long serialVersionUID = 3923460763119137073L;
	/** app名字 **/
    private String appName;
    /** app图标 **/
    private Drawable appIcon;
    /** 是否已安装 **/
    private boolean isInstalled;
    /** 是否选中（用于删除） **/
    private boolean isChoose;
    /** app说明（安装后获取到的） **/
    private String appInfo;
    /** app路径 **/
    private String appPath;
    /** 文件大小 **/
    private String fileSize;

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public boolean isInstalled() {
        return isInstalled;
    }

    public void setInstalled(boolean isInstalled) {
        this.isInstalled = isInstalled;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean isChoose) {
        this.isChoose = isChoose;
    }

    public String getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    @Override
    public String toString() {
        return "Download_APK_Install{" +
                "appName='" + appName + '\'' +
                ", appIcon=" + appIcon +
                ", isInstalled=" + isInstalled +
                ", isChoose=" + isChoose +
                ", appInfo='" + appInfo + '\'' +
                ", appPath='" + appPath + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
