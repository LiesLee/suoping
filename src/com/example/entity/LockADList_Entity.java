package com.example.entity;

import java.io.Serializable;

import com.example.util.StringUtils;

public class LockADList_Entity implements Serializable {

	/**  */
	private static final long serialVersionUID = 5791492841338282189L;
	private String id = StringUtils.EMPTY;
	private String title = StringUtils.EMPTY;
	private String earn_jifen = StringUtils.EMPTY;
	private String hp_url = StringUtils.EMPTY;
	private String detail_url = StringUtils.EMPTY;
	private String process_name = StringUtils.EMPTY;
	/** 广告类型,0有下载，1没有下载 */
	private int app_type;
	private int exper_time;

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @see #id
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @see #id
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #app_type
	 * @return the app_type
	 */
	public int getApp_type() {
		return app_type;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #app_type
	 * @param app_type
	 *            the app_type to set
	 */
	public void setApp_type(int app_type) {
		this.app_type = app_type;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @see #title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @see #title
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @see #earn_jifen
	 * @return the earn_jifen
	 */
	public String getEarn_jifen() {
		return earn_jifen;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @see #earn_jifen
	 * @param earn_jifen
	 *            the earn_jifen to set
	 */
	public void setEarn_jifen(String earn_jifen) {
		this.earn_jifen = earn_jifen;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @see #hp_url
	 * @return the hp_url
	 */
	public String getHp_url() {
		return hp_url;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @see #hp_url
	 * @param hp_url
	 *            the hp_url to set
	 */
	public void setHp_url(String hp_url) {
		this.hp_url = hp_url;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #detail_url
	 * @return the detail_url
	 */
	public String getDetail_url() {
		return detail_url;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #detail_url
	 * @param detail_url
	 *            the detail_url to set
	 */
	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-9.
	 * @see #exper_time
	 * @return the exper_time
	 */
	public int getExper_time() {
		return exper_time;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-9.
	 * @see #exper_time
	 * @param exper_time
	 *            the exper_time to set
	 */
	public void setExper_time(int exper_time) {
		this.exper_time = exper_time;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-10.
	 * @see #process_name
	 * @return the process_name
	 */
	public String getProcess_name() {
		return process_name;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-10.
	 * @see #process_name
	 * @param process_name
	 *            the process_name to set
	 */
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LockADList_Entity [id=" + id + ", title=" + title + ", earn_jifen=" + earn_jifen + ", hp_url=" + hp_url
				+ ", detail_url=" + detail_url + ", process_name=" + process_name + ", app_type=" + app_type
				+ ", exper_time=" + exper_time + "]";
	}

}
