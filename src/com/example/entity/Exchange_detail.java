package com.example.entity;

import java.io.Serializable;

/**
 * Created by LiesLee on 2015/1/9. Email: LiesLee@foxmail.com
 */
public class Exchange_detail implements Serializable {
	/**  */
	private static final long serialVersionUID = 3401119959962727213L;
	private String epid = "";
	private String ep_name = "";
	private String hp_url = "";
	private int is_send;
	private String send_msg = "";
	private int ep_jifen;

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @see #is_send
	 * @return the is_send
	 */
	public String getIs_send() {
		if (is_send == 0) {
			return "未发货";
		}
		return "已发货";
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @see #is_send
	 * @param is_send
	 *            the is_send to set
	 */
	public void setIs_send(int is_send) {
		this.is_send = is_send;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @see #send_msg
	 * @return the send_msg
	 */
	public String getSend_msg() {
		return send_msg;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @see #send_msg
	 * @param send_msg
	 *            the send_msg to set
	 */
	public void setSend_msg(String send_msg) {
		this.send_msg = send_msg;
	}

	public String getEpid() {
		return epid;
	}

	public void setEpid(String epid) {
		this.epid = epid;
	}

	public String getEp_name() {
		return ep_name;
	}

	public void setEp_name(String ep_name) {
		this.ep_name = ep_name;
	}

	public int getEp_jifen() {
		return ep_jifen;
	}

	public void setEp_jifen(int ep_jifen) {
		this.ep_jifen = ep_jifen;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-10.
	 * @see #hp_url
	 * @return the hp_url
	 */
	public String getHp_url() {
		return hp_url;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-10.
	 * @see #hp_url
	 * @param hp_url
	 *            the hp_url to set
	 */
	public void setHp_url(String hp_url) {
		this.hp_url = hp_url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Exchange_detail [epid=" + epid + ", ep_name=" + ep_name + ", hp_url=" + hp_url + ", is_send=" + is_send
				+ ", send_msg=" + send_msg + ", ep_jifen=" + ep_jifen + "]";
	}

}
