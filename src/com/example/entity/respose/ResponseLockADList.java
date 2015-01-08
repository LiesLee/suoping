package com.example.entity.respose;

import java.util.ArrayList;

import com.example.entity.LockADList_Entity;
import com.example.entity.more.Logistics_Entity;

/**
 * @Description 锁屏广告列表
 * @author Created by qinxianyuzou on 2015-1-4.
 */
public class ResponseLockADList extends BaseResponse {
	/**  */
	private static final long serialVersionUID = 8062805673883580062L;
	/** 用户信息 **/
	private ArrayList<LockADList_Entity> data = new ArrayList<>();

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @see #data
	 * @return the data
	 */
	public ArrayList<LockADList_Entity> getData() {
		return data;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-8.
	 * @see #data
	 * @param data
	 *            the data to set
	 */
	public void setData(ArrayList<LockADList_Entity> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseUserInfo [data=" + data + "]";
	}

}
