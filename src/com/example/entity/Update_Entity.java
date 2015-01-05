package com.example.entity;

import java.io.Serializable;

import com.example.util.StringUtils;

/**
 * @Description 更新实体
 * @author Created by qinxianyuzou on 2015-1-5.
 */
public class Update_Entity implements Serializable {
	/**  */
	private static final long serialVersionUID = -4285545536476158955L;
	private String download_url = StringUtils.EMPTY;
	private int app_version;
	private int app_forced_upgraded;
	private String app_size = StringUtils.EMPTY;

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @see #download_url
	 * @return the download_url
	 */
	public String getDownload_url() {
		return download_url;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @see #download_url
	 * @param download_url
	 *            the download_url to set
	 */
	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @see #app_version
	 * @return the app_version
	 */
	public int getApp_version() {
		return app_version;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @see #app_version
	 * @param app_version
	 *            the app_version to set
	 */
	public void setApp_version(int app_version) {
		this.app_version = app_version;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @see #app_forced_upgraded
	 * @return the app_forced_upgraded
	 */
	public boolean getApp_forced_upgraded() {
		if (app_forced_upgraded == 1) {
			return true;
		}
		return false;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @see #app_forced_upgraded
	 * @param app_forced_upgraded
	 *            the app_forced_upgraded to set
	 */
	public void setApp_forced_upgraded(int app_forced_upgraded) {
		this.app_forced_upgraded = app_forced_upgraded;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @see #app_size
	 * @return the app_size
	 */
	public String getApp_size() {
		return app_size;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @see #app_size
	 * @param app_size
	 *            the app_size to set
	 */
	public void setApp_size(String app_size) {
		this.app_size = app_size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Update_Entity [download_url=" + download_url + ", app_version=" + app_version
				+ ", app_forced_upgraded=" + app_forced_upgraded + ", app_size=" + app_size + "]";
	}

}
