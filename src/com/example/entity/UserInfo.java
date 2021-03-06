package com.example.entity;

import com.example.util.StringUtils;

import java.io.Serializable;

/**
 * 用户信息 Created by LiesLee on 2014/12/28. Email: LiesLee@foxmail.com
 */
public class UserInfo implements Serializable {
	/**  */
	private static final long serialVersionUID = -6905060687749964702L;
	// /** 用户id **/
	// private String id = StringUtils.EMPTY;
	/** 手机号码 **/
	private String username = StringUtils.EMPTY;
	/** 总积分 **/
	private float sum_earn;
	/** 当天积分 **/
	private float today_earn;
	/** 累计积分 **/
	private float total_earn;
	/** 性别 0代表男性，1代表女性 **/
	private int sex;
	/** 邀请码 **/
	private String invite_no;
	/** 用户昵称 **/
	private String name = "";
	/** 生日 */
	private String birthday = "";

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-26.
	 * @see #total_earn
	 * @return the total_earn
	 */
	public float getTotal_earn() {
		return total_earn;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-26.
	 * @see #total_earn
	 * @param total_earn
	 *            the total_earn to set
	 */
	public void setTotal_earn(float total_earn) {
		this.total_earn = total_earn;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #username
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #username
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #sum_earn
	 * @return the sum_earn
	 */
	public float getSum_earn() {
		return sum_earn;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #sum_earn
	 * @param sum_earn
	 *            the sum_earn to set
	 */
	public void setSum_earn(float sum_earn) {
		this.sum_earn = sum_earn;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #today_earn
	 * @return the today_earn
	 */
	public float getToday_earn() {
		return today_earn;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #today_earn
	 * @param today_earn
	 *            the today_earn to set
	 */
	public void setToday_earn(float today_earn) {
		this.today_earn = today_earn;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #sex
	 * @return the sex
	 */
	public String getSex() {
		if (sex == 0) {
			return "男";
		}
		return "女";
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #sex
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #invite_no
	 * @return the invite_no
	 */
	public String getInvite_no() {
		return invite_no;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #invite_no
	 * @param invite_no
	 *            the invite_no to set
	 */
	public void setInvite_no(String invite_no) {
		this.invite_no = invite_no;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #name
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #birthday
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #birthday
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", sum_earn=" + sum_earn + ", today_earn=" + today_earn
				+ ", total_earn=" + total_earn + ", sex=" + sex + ", invite_no=" + invite_no + ", name=" + name
				+ ", birthday=" + birthday + "]";
	}

}
