package com.example.http;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.Get_logistics_list_entity;

import android.content.Context;

/**
 * @Description 接口类
 * @author Created by qinxianyuzou on 2014-12-24.
 */
public class Protocol {

	/**
	 * @Description 获取用户物流信息
	 * @author Created by qinxianyuzou on 2014-12-24.
	 * @param mContext
	 * @param tag
	 * @param responseClass
	 * @param httpCallBack
	 * @return
	 */
	public static long get_logistics_list(Context mContext, String tag) {
		Map<String, String> requestParam = new HashMap<String, String>();
		return ConnectorManage.getInstance(mContext).sentHttpRequest(mContext, 0, Config.GET_LOGISTICS_LIST, tag,
				requestParam, Get_logistics_list_entity.class, null);
	}
}
