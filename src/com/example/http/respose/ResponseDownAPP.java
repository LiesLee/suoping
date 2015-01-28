package com.example.http.respose;

import com.example.entity.DownAPP_Entity;
import com.example.http.base.BaseResponse;

/**
 * @Description 下载APP
 * @author Created by qinxianyuzou on 2015-1-8.
 */
public class ResponseDownAPP extends BaseResponse {
	/**  */
	private static final long serialVersionUID = -3487028624149618336L;
	/** 请求返回信息提示 **/
	private DownAPP_Entity data = new DownAPP_Entity();

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @see #data
	 * @return the data
	 */
	public DownAPP_Entity getData() {
		return data;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @see #data
	 * @param data
	 *            the data to set
	 */
	public void setData(DownAPP_Entity data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseEXProduct [data=" + data + "]";
	}

}
