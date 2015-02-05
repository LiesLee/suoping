package com.example.http.respose;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.entity.SignIn_Entity;
import com.example.http.base.BaseResponse;

/**
 * @Description 信鸽实体
 * @author Created by qinxianyuzou on 2015-1-26.
 */
public class ResponseXinGe extends BaseResponse {
	/**  */
	private static final long serialVersionUID = 8062805673883580062L;
	/** 用户信息 **/
	private XinGe_Entity data = new XinGe_Entity();

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-5.
	 * @see #data
	 * @return the data
	 */
	public XinGe_Entity getData() {
		return data;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-5.
	 * @see #data
	 * @param data
	 *            the data to set
	 */
	public void setData(XinGe_Entity data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseUserInfo [data=" + data + "]";
	}

	public class XinGe_Entity implements Serializable {

		/**  */
		private static final long serialVersionUID = 219240163678260213L;
		private int type;

		/**
		 * @Description
		 * @author Created by qinxianyuzou on 2015-2-5.
		 * @see #type
		 * @return the type
		 */
		public int getType() {
			return type;
		}

		/**
		 * @Description
		 * @author Created by qinxianyuzou on 2015-2-5.
		 * @see #type
		 * @param type
		 *            the type to set
		 */
		public void setType(int type) {
			this.type = type;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "XinGe_Entity [type=" + type + "]";
		}

	}
}
