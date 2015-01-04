package com.example.entity.respose;

import com.example.entity.UserInfo;

/**
 * Created by LiesLee on 2014/12/28. Email: LiesLee@foxmail.com
 */
public class ResponseUserInfo extends BaseResponse {
	/**  */
	private static final long serialVersionUID = 8062805673883580062L;
	/** 用户信息 **/
	private UserInfo data=new UserInfo();

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #data
	 * @return the data
	 */
	public UserInfo getData() {
		return data;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #data
	 * @param data
	 *            the data to set
	 */
	public void setData(UserInfo data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseUserInfo [data=" + data + "]";
	}

}
