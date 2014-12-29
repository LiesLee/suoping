package com.example.activity.more;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.keyguard.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 我的账号
 * @author Created by qinxianyuzou on 2014-12-29.
 */
public class Activity_Address extends BaseActivity {
	/** 列表 */
	@ViewInject(R.id.lv_shop_body)
	private ListView lv_shop_body;
	private MyInfo_Adapter adapter;
	/** 图片加载工具 */
	private BitmapUtils bitmapUtils;
	/** 保存数据源 */
	private List<NameValuePair> dataList = new ArrayList<NameValuePair>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		adapter = new MyInfo_Adapter(activity, bitmapUtils);
		// 滑动时停止加载图片
		lv_shop_body.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
		lv_shop_body.setAdapter(adapter);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		dataList.add(new BasicNameValuePair("所在地区", ""));
		dataList.add(new BasicNameValuePair("街道地址", ""));
		dataList.add(new BasicNameValuePair("收货人姓名", ""));
		dataList.add(new BasicNameValuePair("手机号码", ""));
		dataList.add(new BasicNameValuePair("邮编", ""));
		adapter.setData(dataList);

	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHttpError(long flag, VolleyError error) {
		// TODO Auto-generated method stub

	}

	@Override
	public String setTag() {
		// TODO Auto-generated method stub
		return null;
	}

}
