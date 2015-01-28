package com.example.http.base;

import com.example.util.StringUtils;

import java.io.Serializable;

/**
 * Created by LiesLee on 2014/12/28. Email: LiesLee@foxmail.com
 */
public class BaseResponse implements Serializable {
	/**  */
	private static final long serialVersionUID = -8240313804078683806L;
	/** 状态编码 **/
	private String code = StringUtils.EMPTY;
	/** 状态信息 */
	private String msg = StringUtils.EMPTY;

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #code
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #code
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #msg
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #msg
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @Description 请求成功
	 * @author Created by qinxianyuzou on 2015-1-15.
	 * @return
	 */
	public boolean isSuccess() {
		if (getCode().equals(Code.CODE_SUCCESS)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "BaseResponse [code=" + code + ", msg=" + msg + "]";
	}

}
