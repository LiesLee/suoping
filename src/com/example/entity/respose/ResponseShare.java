package com.example.entity.respose;

import com.example.entity.Share_Entity;

/**
 * @Description 分享
 * @author Created by qinxianyuzou on 2015-1-5.
 */
public class ResponseShare extends BaseResponse {
	/**  */
	private static final long serialVersionUID = -3487028624149618336L;
	/** 请求返回信息提示 **/
	private Share_Entity data = new Share_Entity();

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @see #data
	 * @return the data
	 */
	public Share_Entity getData() {
		return data;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @see #data
	 * @param data
	 *            the data to set
	 */
	public void setData(Share_Entity data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseEXProduct [data=" + data + "]";
	}

}
