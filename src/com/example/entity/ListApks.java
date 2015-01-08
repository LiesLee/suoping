package com.example.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LiesLee on 2015/1/5.
 * Email: LiesLee@foxmail.com
 */
public class ListApks implements Serializable {
    /**  */
	private static final long serialVersionUID = 8181217091431470209L;
	private List<Download_APK_Install> apks;

    public List<Download_APK_Install> getApks() {
        return apks;
    }

    public void setApks(List<Download_APK_Install> apks) {
        this.apks = apks;
    }

    @Override
    public String toString() {
        return "ListApks{" +
                "apks=" + apks +
                '}';
    }
}
