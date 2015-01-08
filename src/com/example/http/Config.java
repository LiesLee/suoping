package com.example.http;

import com.example.util.PublicUtil;

/**
 * @Description 接口配置
 * @author Created by qinxianyuzou on 2014-12-23.
 */
public class Config {
	/** 是否开发模式 */
	public static boolean DEBUG = PublicUtil.isApkDebugable();
	/** 基础地址 */
	private final static String BASE_URL = "http://client.duowanka.com/";
	/** 获取用户物流信息 */
	public final static String GET_LOGISTICS_LIST = BASE_URL + "get_logistics_list";
	/** 登录 */
	public final static String LOGIN = BASE_URL + "login";
	/** 短信验证 */
	public final static String SEND_CODE = BASE_URL + "send_code";
	/** 注册 */
	public final static String REGISTER = BASE_URL + "register";
	/** 注销 */
	public final static String LOGOUT = BASE_URL + "logout";
	/** 编辑用户物流信息 */
	public final static String EDIT_LOGISTICS = BASE_URL + "edit_logistics";
	/** 添加用户物流信息 */
	public final static String ADD_LOGISTICS = BASE_URL + "add_logistics";
	/** 删除用户物流信息 */
	public final static String DEL_LOGISTICS = BASE_URL + "del_logistics";
	/** 获取用户基本信息 */
	public final static String GET_USER_INFO = BASE_URL + "get_user_info";
	/** 获取兑换商品列表 */
	public final static String GET_EX_PRODUCT = BASE_URL + "get_ex_product";
	/** 获取兑换商品的详细信息 */
	public final static String GET_EP_DETAIL = BASE_URL + "get_ep_detail";
	/** 兑换商品 */
	public final static String EXCHANGE_PRODUCT = BASE_URL + "exchange_product";
	/** 获取积分列表 */
	public final static String GET_EARN_LIST = BASE_URL + "get_earn_list";
	/** 获取APP的详细情况 */
	public final static String GET_EARN_DETAIL = BASE_URL + "get_earn_detail";
	/** 获取APP的下载地址 */
	public final static String GET_EARN_DOWNLOADURL = BASE_URL + "get_earn_downloadurl";
	/** 解锁获积分接口 */
	public final static String LOCK_EARN = BASE_URL + "lock_earn";
	/** 邀请赚积分 */
	public final static String INVITE = BASE_URL + "invite";
	/** APP下载 */
	public final static String DOWNLOADAPP = "http://static.duowanka.com/downloadApp";
	/** APP检查更新 */
	public final static String CHECK_UPDATE = "http://static.duowanka.com/check_update";
}
