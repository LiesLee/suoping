package com.example.entity;

/**
 * @Description 用户实体
 * @author Created by qinxianyuzou on 2014-12-26.
 */
public class UserInfo_Entity implements java.io.Serializable {
	/**  */
	private static final long serialVersionUID = 6771506094055558442L;
	/** 登陆成功后 交换获取的Token，全应用唯一 */
	private String token = "";
	/** 配合Token使用的秘钥 (需要授权时，才使用到) */
	private String secret = "";

}
