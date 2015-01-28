package com.example.http.respose;

import com.example.entity.FreshmanAward_Entity;
import com.example.http.base.BaseResponse;

/**
 * @Description 新手活动信息
 * @author Created by qinxianyuzou on 2015-1-28.
 */
public class ResponseFreshmanAward extends BaseResponse {
	/**  */
	private static final long serialVersionUID = 4544369230161227398L;
	/** 用户信息 **/
	private FreshmanAward_Entity data = new FreshmanAward_Entity();

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @see #data
	 * @return the data
	 */
	public FreshmanAward_Entity getData() {
		return data;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @see #data
	 * @param data
	 *            the data to set
	 */
	public void setData(FreshmanAward_Entity data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseUserInfo [data=" + data + "]";
	}

}
