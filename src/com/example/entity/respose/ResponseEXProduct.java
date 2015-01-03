package com.example.entity.respose;

import java.util.ArrayList;

import com.example.entity.shop.EXProduct_Entity;

/**
 * Created by LiesLee on 2014/12/28. Email: LiesLee@foxmail.com
 */
public class ResponseEXProduct extends BaseResponse {
	/**  */
	private static final long serialVersionUID = -3487028624149618336L;
	/** 请求返回信息提示 **/
	private ArrayList<EXProduct_Entity> data = new ArrayList<EXProduct_Entity>();

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #data
	 * @return the data
	 */
	public ArrayList<EXProduct_Entity> getData() {
		return data;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #data
	 * @param data
	 *            the data to set
	 */
	public void setData(ArrayList<EXProduct_Entity> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseEXProduct [data=" + data + "]";
	}

}
