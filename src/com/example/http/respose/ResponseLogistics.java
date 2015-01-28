package com.example.http.respose;

import java.util.ArrayList;

import com.example.entity.more.Logistics_Entity;
import com.example.http.base.BaseResponse;

/**
 * @Description 物流地址列表
 * @author Created by qinxianyuzou on 2015-1-4.
 */
public class ResponseLogistics extends BaseResponse {
	/**  */
	private static final long serialVersionUID = 8062805673883580062L;
	/** 用户信息 **/
	private ArrayList<Logistics_Entity> data = new ArrayList<>();

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @see #data
	 * @return the data
	 */
	public ArrayList<Logistics_Entity> getData() {
		return data;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-4.
	 * @see #data
	 * @param data
	 *            the data to set
	 */
	public void setData(ArrayList<Logistics_Entity> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseUserInfo [data=" + data + "]";
	}

}
