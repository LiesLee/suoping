package com.example.address;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Description 省份
 * @author Created by qinxianyuzou on 2015-2-3.
 */
public class Provinces_Entity implements Serializable {
	/**  */
	private static final long serialVersionUID = 4381770101207284779L;
	/** id */
	private int id;
	/** 名称 */
	private String name = "";
	/** 城市列表 */
	private ArrayList<City_Entity> city_Entities = new ArrayList<>();

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
	 * @see #city_Entities
	 * @return the city_Entities
	 */
	public ArrayList<City_Entity> getCity_Entities() {
		return city_Entities;
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-3.
	 * @see #city_Entities
	 * @param city_Entities
	 *            the city_Entities to set
	 */
	public void setCity_Entities(ArrayList<City_Entity> city_Entities) {
		this.city_Entities = city_Entities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Provinces_Entity [id=" + id + ", name=" + name + ", city_Entities=" + city_Entities + "]";
	}

}
