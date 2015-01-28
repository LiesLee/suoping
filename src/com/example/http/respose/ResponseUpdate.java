package com.example.http.respose;

import com.example.entity.Update_Entity;
import com.example.http.base.BaseResponse;

/**
 * @Description 更新实体
 * @author Created by qinxianyuzou on 2015-1-5.
 */
public class ResponseUpdate extends BaseResponse {
	/**  */
	private static final long serialVersionUID = -3487028624149618336L;
	/** 请求返回信息提示 **/
	private Update_Entity data = new Update_Entity();

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @see #data
	 * @return the data
	 */
	public Update_Entity getData() {
		return data;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @see #data
	 * @param data
	 *            the data to set
	 */
	public void setData(Update_Entity data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseEXProduct [data=" + data + "]";
	}

}
