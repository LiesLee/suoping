package com.example.entity;

import java.io.Serializable;

import com.example.util.StringUtils;

/**
 * @Description 下载实体
 * @author Created by qinxianyuzou on 2015-1-5.
 */
public class DownAPP_Entity implements Serializable {
	/**  */
	private static final long serialVersionUID = 5274167901380717560L;
	/** 下载地址 */
	private String download_url = StringUtils.EMPTY;

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @see #download_url
	 * @return the download_url
	 */
	public String getDownload_url() {
		return download_url;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @see #download_url
	 * @param download_url
	 *            the download_url to set
	 */
	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DownAPP_Entity [download_url=" + download_url + "]";
	}

}
