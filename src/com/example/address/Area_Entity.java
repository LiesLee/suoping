package com.example.address;

import java.io.Serializable;

/**
 * @Description 镇区
 * @author Created by qinxianyuzou on 2015-2-3.
 */
public class Area_Entity implements Serializable {
	/**  */
	private static final long serialVersionUID = -7557069227400538476L;
	/** id */
	private int id;
	/** 城市id */
	private int city_id;
	/** 名称 */
	private String name = "";

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #id
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #id
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #city_id
	 * @return the city_id
	 */
	public int getCity_id() {
		return city_id;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #city_id
	 * @param city_id
	 *            the city_id to set
	 */
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #name
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Area_Entity [id=" + id + ", city_id=" + city_id + ", name=" + name + "]";
	}

}
