package com.example.http.respose;

import java.io.Serializable;

import com.example.http.base.BaseResponse;

/**
 * @Description 新手活动——了解锁屏赚
 * @author Created by qinxianyuzou on 2015-1-28.
 */
public class ResponseNewcomerAbout extends BaseResponse {
	/**  */
	private static final long serialVersionUID = 4544369230161227398L;
	/** 用户信息 **/
	private NewcomerAbout_Entity data = new NewcomerAbout_Entity();

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @see #data
	 * @return the data
	 */
	public NewcomerAbout_Entity getData() {
		return data;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-28.
	 * @see #data
	 * @param data
	 *            the data to set
	 */
	public void setData(NewcomerAbout_Entity data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseUserInfo [data=" + data + "]";
	}

	public class NewcomerAbout_Entity implements Serializable {
		/**  */
		private static final long serialVersionUID = 8951738975090997270L;
		private String url = "";

		/**
		 * @Description
		 * @author Created by qinxianyuzou on 2015-1-28.
		 * @see #url
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}

		/**
		 * @Description
		 * @author Created by qinxianyuzou on 2015-1-28.
		 * @see #url
		 * @param url
		 *            the url to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "NewcomerAbout_Entity [url=" + url + "]";
		}

	}
}
