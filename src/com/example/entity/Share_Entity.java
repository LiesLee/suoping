package com.example.entity;

import java.io.Serializable;

import com.example.util.StringUtils;

/**
 * @Description 分享实体
 * @author Created by qinxianyuzou on 2015-1-5.
 */
public class Share_Entity implements Serializable {
	/**  */
	private static final long serialVersionUID = -4285545536476158955L;
	private String share_msg = StringUtils.EMPTY;
	private String share_url = StringUtils.EMPTY;

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @see #share_msg
	 * @return the share_msg
	 */
	public String getShare_msg() {
		return share_msg;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @see #share_msg
	 * @param share_msg
	 *            the share_msg to set
	 */
	public void setShare_msg(String share_msg) {
		this.share_msg = share_msg;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @see #share_url
	 * @return the share_url
	 */
	public String getShare_url() {
		return share_url;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @see #share_url
	 * @param share_url
	 *            the share_url to set
	 */
	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Share_Entity [share_msg=" + share_msg + ", share_url=" + share_url + "]";
	}

}
