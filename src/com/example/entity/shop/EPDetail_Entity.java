package com.example.entity.shop;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.util.StringUtils;

/**
 * @Description 商品详情
 * @author Created by qinxianyuzou on 2015-1-2.
 */
public class EPDetail_Entity implements Serializable {
	/**  */
	private static final long serialVersionUID = 2006129632670126693L;
	/** 商品名称 */
	private String title = StringUtils.EMPTY;
	/** 商品简介 */
	private String introduce = StringUtils.EMPTY;
	/** 使用说明 */
	private String usage_intro = StringUtils.EMPTY;
	/** 需要积分 */
	private String need_jifen = StringUtils.EMPTY;
	/** 商品介绍图 */
	private ArrayList<String> picture_url = new ArrayList<>();

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-3.
	 * @see #title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-3.
	 * @see #title
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-3.
	 * @see #introduce
	 * @return the introduce
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-3.
	 * @see #introduce
	 * @param introduce
	 *            the introduce to set
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-3.
	 * @see #usage_intro
	 * @return the usage_intro
	 */
	public String getUsage_intro() {
		return usage_intro;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-3.
	 * @see #usage_intro
	 * @param usage_intro
	 *            the usage_intro to set
	 */
	public void setUsage_intro(String usage_intro) {
		this.usage_intro = usage_intro;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-3.
	 * @see #need_jifen
	 * @return the need_jifen
	 */
	public String getNeed_jifen() {
		return need_jifen;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-3.
	 * @see #need_jifen
	 * @param need_jifen
	 *            the need_jifen to set
	 */
	public void setNeed_jifen(String need_jifen) {
		this.need_jifen = need_jifen;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-3.
	 * @see #picture_url
	 * @return the picture_url
	 */
	public ArrayList<String> getPicture_url() {
		return picture_url;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-3.
	 * @see #picture_url
	 * @param picture_url
	 *            the picture_url to set
	 */
	public void setPicture_url(ArrayList<String> picture_url) {
		this.picture_url = picture_url;
	}

	@Override
	public String toString() {
		return "EPDetail_Entity [title=" + title + ", introduce=" + introduce + ", usage_intro=" + usage_intro
				+ ", need_jifen=" + need_jifen + ", picture_url=" + picture_url + "]";
	}

}
