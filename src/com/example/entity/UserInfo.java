package com.example.entity;

import com.example.util.StringUtils;

import java.io.Serializable;

/**
 * 用户信息 Created by LiesLee on 2014/12/28. Email: LiesLee@foxmail.com
 */
public class UserInfo implements Serializable {
	/**  */
	private static final long serialVersionUID = -6905060687749964702L;
	/** 用户id **/
	private String id = StringUtils.EMPTY;
	/** 手机号码 **/
	private String username = StringUtils.EMPTY;
	/** 总积分 **/
	private float sum_earn;
	/** 当天积分 **/
	private float today_earn;
	/** 性别 0代表男性，1代表女性 **/
	private int sex;
	/** 邀请码 **/
	private int invite_no;
	/** 用户昵称 **/
	private String name;


	/**
	 * @Description 
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #id
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @Description 
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #id
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @param username the username to set
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
	 * @param sum_earn the sum_earn to set
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
	 * @param today_earn the today_earn to set
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
	public int getSex() {
		return sex;
	}


	/**
	 * @Description 
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #sex
	 * @param sex the sex to set
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
	public int getInvite_no() {
		return invite_no;
	}


	/**
	 * @Description 
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #invite_no
	 * @param invite_no the invite_no to set
	 */
	public void setInvite_no(int invite_no) {
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "UserInfo{" + "id='" + id + '\'' + ", username='" + username + '\'' + ", sum_earn=" + sum_earn
				+ ", today_earn=" + today_earn + ", sex=" + sex + ", invite_no=" + invite_no + ", name='" + name + '\''
				+ '}';
	}
}
