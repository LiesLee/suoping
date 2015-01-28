package com.example.entity.respose;

import java.util.ArrayList;

import com.example.entity.SignIn_Entity;

/**
 * @Description 签到回调
 * @author Created by qinxianyuzou on 2015-1-26.
 */
public class ResponseSignIn extends BaseResponse {
	/**  */
	private static final long serialVersionUID = 8062805673883580062L;
	/** 用户信息 **/
	private ArrayList<SignIn_Entity> data = new ArrayList<>();

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #data
	 * @return the data
	 */
	public ArrayList<SignIn_Entity> getData() {
		return data;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @see #data
	 * @param data
	 *            the data to set
	 */
	public void setData(ArrayList<SignIn_Entity> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseUserInfo [data=" + data + "]";
	}

}
