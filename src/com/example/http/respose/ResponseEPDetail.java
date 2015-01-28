package com.example.http.respose;

import com.example.entity.shop.EPDetail_Entity;
import com.example.http.base.BaseResponse;

/**
 * @Description 详情页返回体
 * @author Created by qinxianyuzou on 2015-1-3.
 */
public class ResponseEPDetail extends BaseResponse {
	/**  */
	private static final long serialVersionUID = -3487028624149618336L;
	/** 请求返回信息提示 **/
	private EPDetail_Entity data = new EPDetail_Entity();

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-3.
	 * @see #data
	 * @return the data
	 */
	public EPDetail_Entity getData() {
		return data;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-3.
	 * @see #data
	 * @param data
	 *            the data to set
	 */
	public void setData(EPDetail_Entity data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseEXProduct [data=" + data + "]";
	}

}
