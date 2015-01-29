package com.example.entity;

import java.io.Serializable;

/**
 * @Description 签到实体
 * @author Created by qinxianyuzou on 2015-1-26.
 */
public class SignIn_Entity implements Serializable {
	/**  */
	private static final long serialVersionUID = -8266630775840140113L;
	/** 应用id */
	private String eaid = "";
	/** 应用名 */
	private String app_name = "";
	/** 包名 */
	private String process_name = "";
	/** 可获积分 */
	private String earn_jifen = "";
	/** 图标icon */
	private String icon = "";
	/** 回调地址 */
	private String return_url = "";
	/** 使用多长时间 */
	private int use_time = -1;
	/** 应用描述 */
	private String desc = "";

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #eaid
	 * @return the eaid
	 */
	public String getEaid() {
		return eaid;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #eaid
	 * @param eaid
	 *            the eaid to set
	 */
	public void setEaid(String eaid) {
		this.eaid = eaid;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-29.
	 * @see #app_name
	 * @return the app_name
	 */
	public String getApp_name() {
		return app_name;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-29.
	 * @see #app_name
	 * @param app_name
	 *            the app_name to set
	 */
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #process_name
	 * @return the process_name
	 */
	public String getProcess_name() {
		return process_name;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #process_name
	 * @param process_name
	 *            the process_name to set
	 */
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #earn_jifen
	 * @return the earn_jifen
	 */
	public String getEarn_jifen() {
		return earn_jifen;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #earn_jifen
	 * @param earn_jifen
	 *            the earn_jifen to set
	 */
	public void setEarn_jifen(String earn_jifen) {
		this.earn_jifen = earn_jifen;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #icon
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #icon
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #return_url
	 * @return the return_url
	 */
	public String getReturn_url() {
		return return_url;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #return_url
	 * @param return_url
	 *            the return_url to set
	 */
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #use_time
	 * @return the use_time
	 */
	public int getUse_time() {
		return use_time;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #use_time
	 * @param use_time
	 *            the use_time to set
	 */
	public void setUse_time(int use_time) {
		this.use_time = use_time;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #desc
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #desc
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SignIn_Entity [eaid=" + eaid + ", app_name=" + app_name + ", process_name=" + process_name
				+ ", earn_jifen=" + earn_jifen + ", icon=" + icon + ", return_url=" + return_url + ", use_time="
				+ use_time + ", desc=" + desc + "]";
	}

}
