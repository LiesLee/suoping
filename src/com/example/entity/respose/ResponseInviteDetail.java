package com.example.entity.respose;

import com.example.entity.InviteDetail_Entity;

/**
 * @Description 邀请详情回调
 * @author Created by qinxianyuzou on 2015-1-9.
 */
public class ResponseInviteDetail extends BaseResponse {
	/**  */
	private static final long serialVersionUID = 8062805673883580062L;
	/** 用户信息 **/
	private InviteDetail_Entity data = new InviteDetail_Entity();

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #data
	 * @return the data
	 */
	public InviteDetail_Entity getData() {
		return data;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #data
	 * @param data
	 *            the data to set
	 */
	public void setData(InviteDetail_Entity data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseUserInfo [data=" + data + "]";
	}

}
