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

	@Override
	public String toString() {
		return "LockADList_Entity [id=" + id + ", title=" + title + ", earn_jifen=" + earn_jifen + ", hp_url=" + hp_url
				+ "]";
	}

}
