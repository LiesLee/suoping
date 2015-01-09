package com.example.entity;

import java.io.Serializable;

import com.example.util.StringUtils;

/**
 * @Description 邀请实体
 * @author Created by qinxianyuzou on 2015-1-5.
 */
public class InviteDetail_Entity implements Serializable {
	/**  */
	private static final long serialVersionUID = -7648082233300430477L;
	/** 邀请人数 */
	private String sum_num = StringUtils.EMPTY;
	/** 邀请获得收益 */
	private String sum_iearn = StringUtils.EMPTY;

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-9.
	 * @see #sum_num
	 * @return the sum_num
	 */
	public String getSum_num() {
		return sum_num;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-9.
	 * @see #sum_num
	 * @param sum_num
	 *            the sum_num to set
	 */
	public void setSum_num(String sum_num) {
		this.sum_num = sum_num;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-9.
	 * @see #sum_iearn
	 * @return the sum_iearn
	 */
	public String getSum_iearn() {
		return sum_iearn;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-9.
	 * @see #sum_iearn
	 * @param sum_iearn
	 *            the sum_iearn to set
	 */
	public void setSum_iearn(String sum_iearn) {
		this.sum_iearn = sum_iearn;
	}

	@Override
	public String toString() {
		return "InviteDetail_Entity [sum_num=" + sum_num + ", sum_iearn=" + sum_iearn + "]";
	}

}
