package com.example.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Description json解释工具
 * @author Created by qinxianyuzou on 2014-8-19.
 */
public class JSONUtil {

	/**
	 * @Description 获取字符串型数据
	 * @author Created by qinxianyuzou on 2014-8-19.
	 * @param json
	 * @param key
	 * @param defaultValue
	 *            默认值，当获取字符串失败时返回的默认值
	 * @return
	 */
	public static String getString(JSONObject json, String key, String defaultValue) {
		try {
			return json.getString(key);
		} catch (JSONException e) {
			return defaultValue;
		}
	}

	/**
	 * @Description 获取int型数据
	 * @author Created by qinxianyuzou on 2014-8-19.
	 * @param json
	 * @param key
	 * @param defaultValue
	 *            默认值，当获取字符串失败时返回的默认值
	 * @return
	 */
	public static int getInt(JSONObject json, String key, int defaultValue) {
		try {
			return json.getInt(key);
		} catch (JSONException e) {
			return defaultValue;
		}
	}

	/**
	 * @Description 获取long型数据
	 * @author Created by qinxianyuzou on 2014-8-19.
	 * @param json
	 * @param key
	 * @param defaultValue
	 *            默认值，当获取字符串失败时返回的默认值
	 * @return
	 */
	public static long getLong(JSONObject json, String key, long defaultValue) {
		try {
			// json.getJSONArray(name)
			return json.getLong(key);
		} catch (JSONException e) {
			return defaultValue;
		}

	}

	/**
	 * @Description 获取double型数据
	 * @author Created by qinxianyuzou on 2014-8-19.
	 * @param json
	 * @param key
	 * @param defaultValue
	 *            默认值，当获取字符串失败时返回的默认值
	 * @return
	 */
	public static double getDouble(JSONObject json, String key, double defaultValue) {
		try {
			return json.getDouble(key);
		} catch (JSONException e) {
			return defaultValue;
		}
	}

	/**
	 * @Description 获取JSONArray数据
	 * @author Created by qinxianyuzou on 2014-8-19.
	 * @param json
	 * @param key
	 * @param defaultValue
	 *            默认值，当获取字符串失败时返回的默认值
	 * @return
	 */
	public static JSONArray getJSONArray(JSONObject json, String key, JSONArray arry) {
		try {
			return json.getJSONArray(key);
		} catch (JSONException e) {
			return arry;
		}
	}
}
