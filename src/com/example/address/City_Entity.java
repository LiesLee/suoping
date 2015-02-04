package com.example.address;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Description 城市
 * @author Created by qinxianyuzou on 2015-2-3.
 */
public class City_Entity implements Serializable {
	/**  */
	private static final long serialVersionUID = -7737117178823085545L;
	/** id */
	private int id;
	/** 省份id */
	private int provinces_id;
	/** 名称 */
	private String name = "";
	/** 地区列表 */
	private ArrayList<Area_Entity> area_Entities = new ArrayList<>();

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
	 * @see #provinces_id
	 * @return the provinces_id
	 */
	public int getProvinces_id() {
		return provinces_id;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #provinces_id
	 * @param provinces_id
	 *            the provinces_id to set
	 */
	public void setProvinces_id(int provinces_id) {
		this.provinces_id = provinces_id;
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

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #area_Entities
	 * @return the area_Entities
	 */
	public ArrayList<Area_Entity> getArea_Entities() {
		return area_Entities;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #area_Entities
	 * @param area_Entities
	 *            the area_Entities to set
	 */
	public void setArea_Entities(ArrayList<Area_Entity> area_Entities) {
		this.area_Entities = area_Entities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "City_Entity [id=" + id + ", provinces_id=" + provinces_id + ", name=" + name + ", area_Entities="
				+ area_Entities + "]";
	}
}
