package com.example.entity.respose;

import com.example.util.StringUtils;

/**
 * Created by LiesLee on 2014/12/28. Email: LiesLee@foxmail.com
 */
public class ResponseSendCode extends BaseResponse {
	/**  */
	private static final long serialVersionUID = -3487028624149618336L;
	/** 请求返回信息提示 **/
	private String data = StringUtils.EMPTY;

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #data
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #data
	 * @param data
	 *            the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseSendCode [data=" + data + "]";
	}

}
