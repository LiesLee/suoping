package com.example.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * Various String utility functions. Most of the functions herein are
 * re-implementations of the ones in apache commons StringUtils.
 */
public class StringUtils {

	public static final String ERROR_TOAST = "网络环境不给力，请检查网络";
	public static final String NO_MORE_DATE = "没有更多数据";
	public static final String EMPTY = "";

	public static final String BLANK_SPACE = " ";

	public static String trim(String text) {
		if (text == null)
			return "";
		return text.trim();
	}

	/**
	 * @Description 判断字符串是否为空
	 * @author Created by qinxianyuzou on 2014-12-11.
	 * @param text
	 * @return
	 */
	public static boolean isEmpty(String text) {
		return text == null || text.trim().compareTo("") == 0 || text.equals("null");
	}

	public static String listToString(List<String> list, String separator) {
		if (list == null || list.size() == 0)
			return "";

		StringBuilder string = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			string.append(list.get(i));
			if (i < list.size() - 1) {
				string.append(separator);
			}
		}

		return string.toString();
	}

	public static String stringArrayToString(String[] array, String separator) {
		if (array == null || array.length == 0)
			return "";
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			string.append(array[i]).append(separator);
		}
		return string.toString();
	}

	public final static int getIntValue(String str) {
		if (str != null && str.length() > 0) {
			try {
				return Integer.parseInt(str);
			} catch (Exception e) {
			}
		}
		return 0;
	}

	public final static long getLongValue(String str) {
		if (str != null && str.length() > 0) {
			try {
				return Long.parseLong(str);
			} catch (Exception e) {
			}
		}
		return 0;
	}

	public static String removeEmptyChar(String src) {
		if (src == null || src.length() == 0)
			return src;
		return src.replaceAll("[\r]*[\n]*[　]*[ ]*", "");
	}

	public static String getFileNameFromUrl(String url) {

		// 名字不能只用这个
		// 通过 ‘？’ 和 ‘/’ 判断文件名
		String extName = "";
		String filename;
		int index = url.lastIndexOf('?');
		if (index > 1) {
			extName = url.substring(url.lastIndexOf('.') + 1, index);
		} else {
			extName = url.substring(url.lastIndexOf('.') + 1);
		}
		filename = hashKeyForDisk(url) + "." + extName;
		return filename;
	}

	/**
	 * 一个散列方法,改变一个字符串(如URL)到一个散列适合使用作为一个磁盘文件名。
	 */
	public static String hashKeyForDisk(String key) {
		String cacheKey;
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey = bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			cacheKey = String.valueOf(key.hashCode());
		}

		return cacheKey;
	}

	private static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	public static String Md5(String string) {
		if (string != null && !string.equals("")) {
			try {
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
				byte[] md5Byte = md5.digest(string.getBytes("UTF8"));
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < md5Byte.length; i++) {
					sb.append(HEX[(int) (md5Byte[i] & 0xff) / 16]);
					sb.append(HEX[(int) (md5Byte[i] & 0xff) % 16]);
				}
				string = sb.toString();
			} catch (Exception e) {
			}
		}
		return string;
	}

	/**
	 * 根据各国的手机号码规则，检测输入
	 * 
	 * @param code
	 * @param number
	 * @return
	 * @time 2011-7-22 上午09:41:04
	 * @author:linyg
	 */
	public static boolean phoneNumberValid(String code, String number) {
		// 手机号固定在5-20范围内
		if (number.length() < 5 || number.length() > 20) {
			return false;
		}

		String match = "";
		if ("86".equals(code)) {// 中国
			if (number.length() != 11) {
				return false;
			} else {
				match = "^[1]{1}[0-9]{2}[0-9]{8}$";
			}
		}

		// 正则匹配
		if (!"".equals(match)) {
			return number.matches(match);
		}
		return true;
	}

	public static boolean phoneNumberValid(String number) {
		// 手机号固定在5-20范围内
		if (number.length() < 5 || number.length() > 20) {
			return false;
		}

		String match = "";
		if (number.length() != 11) {
			return false;
		} else {
			// match = "^[1]{1}[0-9]{2}[0-9]{8}$";
			match = "^(13[0-9]|14[5|7]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";
		}

		// 正则匹配
		if (!"".equals(match)) {
			return number.matches(match);
		}
		return true;
	}

	/** 判断邮箱地址是否有效限 */
	public static boolean isEmailAddValid(String address) {
		if (address != null && address.length() > 0) {
			char[] cAddress = address.toCharArray();
			for (char c : cAddress) {
				if (c > 127) {
					return false;
				}
			}

			Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
			Matcher m = p.matcher(address);
			return m.matches();
		}
		return false;
	}

	/**
	 * 判断密码是否有效
	 * <p>
	 * 0 -- 非法；1 -- 正确； 2 -- 不一致
	 */
	public static int isPasswordValid(String password, String repeated) {
		if (password != null) {
			int len = password.length();
			if (len >= 6 && len <= 16) {
				char[] cPsw = password.toCharArray();
				boolean wrongChar = false;
				for (char c : cPsw) {
					if (c >= 128) { // 找到非ascii码
						wrongChar = true;
						break;
					}
				}
				if (!wrongChar) {
					return password.equals(repeated) ? 1 : 2;
				}
			}
		}
		return 0;
	}

	/**
	 * 过滤掉 \r 换行 \n回车
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\r+|\n+");
			Matcher m = p.matcher(str);
			dest = m.replaceAll(" ");// .replaceAll(" +", " ");
		}
		return dest;
	}

	// 半角字符与全角字符混乱所致：这种情况一般就是汉字与数字、英文字母混用,可以避免由于占位导致的排版混乱问题了
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	// 得到中文首字母
	public static String getPinYinHeadChar(String str) {

		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	// 得到字符串所有首字母
	public static String getPinYinAllChar(String str) {

		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null && pinyinArray.length > 0) {
				convert += pinyinArray[0].charAt(0) + "" + word;
			} else {
				convert += word;
			}
		}
		return convert;
	}

	public static String ListTOString(ArrayList<String> dataList) {
		if (null == dataList || dataList.size() == 0) {
			return "";
		}
		StringBuilder str_b = new StringBuilder();
		for (String ss : dataList) {
			str_b.append(ss);
			str_b.append("$");
		}
		return str_b.substring(0, str_b.length() - 1);

	}

	/**
	 * 提取英文的首字母，非英文字母用#代替。
	 */
	public static String getAlpha(String str) {
		String defaultKey = "#";
		if (StringUtils.isEmpty(str)) {
			return defaultKey;
		}
		// 正则表达式，判断首字母是否是英文字母
		char word = str.charAt(0);
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(String.valueOf(word)).matches()) {
			return String.valueOf(word).toUpperCase(); // 大写输出
		} else {
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null)
				return String.valueOf(pinyinArray[0].charAt(0)).toUpperCase();
		}

		return defaultKey;
	}

	/**
	 * 判断当前String是否包含key
	 * 
	 * @param value
	 * @param key
	 * @return
	 */
	public static boolean matchValue(String value, String key) {
		// 正则表达式，判断首字母是否是英文字母
		String fValue = getPinYinHeadChar(value).substring(0, 1);
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		char fKey = key.trim().substring(0, 1).charAt(0);

		if (pattern.matcher(String.valueOf(fKey)).matches()) {
			return String.valueOf(fKey).toUpperCase().equals(fValue.toUpperCase());
		} else {
			if (value.contains(key))
				return true;
		}
		return false;
	}

	// 汉英混合搜索
	public static boolean matchAllValue(String value, String key) {

		// 字符串所有字首字母
		String fValue = getPinYinAllChar(value);
		return doMatcher(fValue, key);
	}

	private static boolean doMatcher(String fValue, String kValue) {
		StringBuilder reg = new StringBuilder();
		for (int i = 0; i < kValue.length(); i++) {
			char ch = kValue.charAt(i);
			reg.append(ch).append("\\w*");
		}
		Pattern pattern = Pattern.compile(reg.toString());
		return pattern.matcher(fValue).find();
	}


	public static <T> boolean isEmptyData(List<T> newData) {
		if (newData == null) {
			return true;
		} else {
			if (newData.size() == 0) {
				return true;
			} else {
				return false;
			}
		}
	}
}
