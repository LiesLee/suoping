package com.example.entity.shop;

import java.io.Serializable;

import com.example.util.StringUtils;

/**
 * @Description 商品列表
 * @author Created by qinxianyuzou on 2015-1-2.
 */
public class EXProduct_Entity implements Serializable {

	/**  */
	private static final long serialVersionUID = -5974157875318100705L;

	/** 商品id */
	private String ep_id = StringUtils.EMPTY;
	/** 商品名称 */
	private String title = StringUtils.EMPTY;
	/** 商品图片 */
	private String hp_url = StringUtils.EMPTY;
	/** 需要积分 */
	private String need_jifen = StringUtils.EMPTY;
    /** 兑换时输入类型 **/
    private int input_type;

    public int getInput_type() {
        return input_type;
    }

    public void setInput_type(int input_type) {
        this.input_type = input_type;
    }

    /**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #ep_id
	 * @return the ep_id
	 */
	public String getEp_id() {
		return ep_id;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #ep_id
	 * @param ep_id
	 *            the ep_id to set
	 */
	public void setEp_id(String ep_id) {
		this.ep_id = ep_id;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #title
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #hp_url
	 * @return the hp_url
	 */
	public String getHp_url() {
		return hp_url;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #hp_url
	 * @param hp_url
	 *            the hp_url to set
	 */
	public void setHp_url(String hp_url) {
		this.hp_url = hp_url;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #need_jifen
	 * @return the need_jifen
	 */
	public String getNeed_jifen() {
		return need_jifen;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-1-2.
	 * @see #need_jifen
	 * @param need_jifen
	 *            the need_jifen to set
	 */
	public void setNeed_jifen(String need_jifen) {
		this.need_jifen = need_jifen;
	}

	@Override
	public String toString() {
		return "EXProduct_Entity [ep_id=" + ep_id + ", title=" + title + ", hp_url=" + hp_url + ", need_jifen="
				+ need_jifen + "]";
	}

}
