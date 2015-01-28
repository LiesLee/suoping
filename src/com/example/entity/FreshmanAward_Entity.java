package com.example.entity;

import java.io.Serializable;

/**
 * @Description 新手活动实体
 * @author Created by qinxianyuzou on 2015-1-28.
 */
public class FreshmanAward_Entity implements Serializable {

	/**  */
	private static final long serialVersionUID = 4205138455303026850L;
	/** 完善信息奖励金额 */
	private float info = 0;
	/** 填写邀请码奖励 */
	private float invite = 0;
	/** 了解锁屏赚奖励 */
	private float software = 0;
	/** 总奖励金额 */
	private float sum = 0;

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @see #info
	 * @return the info
	 */
	public float getInfo() {
		return info;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @see #info
	 * @param info
	 *            the info to set
	 */
	public void setInfo(float info) {
		this.info = info;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @see #invite
	 * @return the invite
	 */
	public float getInvite() {
		return invite;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @see #invite
	 * @param invite
	 *            the invite to set
	 */
	public void setInvite(float invite) {
		this.invite = invite;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @see #software
	 * @return the software
	 */
	public float getSoftware() {
		return software;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @see #software
	 * @param software
	 *            the software to set
	 */
	public void setSoftware(float software) {
		this.software = software;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @see #sum
	 * @return the sum
	 */
	public float getSum() {
		return sum;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @see #sum
	 * @param sum
	 *            the sum to set
	 */
	public void setSum(float sum) {
		this.sum = sum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FreshmanAward_Entity [info=" + info + ", invite=" + invite + ", software=" + software + ", sum=" + sum
				+ "]";
	}

}
