package com.example.entity.more;

import java.io.Serializable;

import com.example.util.StringUtils;

public class Logistics_Entity implements Serializable {
	/**  */
	private static final long serialVersionUID = -174466486138736107L;
	/** 地址id */
	private String ogistics_id = StringUtils.EMPTY;
	/** 是否默认配送 */
	private int default_addr;
	/** 收货人 */
	private String to_who = StringUtils.EMPTY;
	/** 收货地址 */
	private String to_where = StringUtils.EMPTY;
	/** 邮编 */
	private String post_no = StringUtils.EMPTY;

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @see #ogistics_id
	 * @return the ogistics_id
	 */
	public String getOgistics_id() {
		return ogistics_id;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @see #ogistics_id
	 * @param ogistics_id
	 *            the ogistics_id to set
	 */
	public void setOgistics_id(String ogistics_id) {
		this.ogistics_id = ogistics_id;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @see #default_addr
	 * @return the default_addr
	 */
	public boolean getDefault_addr() {
		if (default_addr == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @see #default_addr
	 * @param default_addr
	 *            the default_addr to set
	 */
	public void setDefault_addr(int default_addr) {
		this.default_addr = default_addr;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @see #to_who
	 * @return the to_who
	 */
	public String getTo_who() {
		return to_who;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @see #to_who
	 * @param to_who
	 *            the to_who to set
	 */
	public void setTo_who(String to_who) {
		this.to_who = to_who;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @see #to_where
	 * @return the to_where
	 */
	public String getTo_where() {
		return to_where;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @see #to_where
	 * @param to_where
	 *            the to_where to set
	 */
	public void setTo_where(String to_where) {
		this.to_where = to_where;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @see #post_no
	 * @return the post_no
	 */
	public String getPost_no() {
		return post_no;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @see #post_no
	 * @param post_no
	 *            the post_no to set
	 */
	public void setPost_no(String post_no) {
		this.post_no = post_no;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Logistics_Entity [ogistics_id=" + ogistics_id + ", default_addr=" + default_addr + ", to_who=" + to_who
				+ ", to_where=" + to_where + ", post_no=" + post_no + "]";
	}
}
