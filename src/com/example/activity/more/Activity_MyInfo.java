package com.example.activity.more;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.Activity_Submit;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.keyguard.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 我的账号
 * @author Created by qinxianyuzou on 2014-12-29.
 */
public class Activity_MyInfo extends BaseActivity {
	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 列表 */
	@ViewInject(R.id.lv_shop_body)
	private ListView lv_shop_body;
	private MyInfo_Adapter adapter;
	/** 图片加载工具 */
	private BitmapUtils bitmapUtils;
	/** 保存数据源 */
	private List<NameValuePair> dataList = new ArrayList<NameValuePair>();

	public static void luanch(Activity activity) {
		Intent intent = new Intent(activity, Activity_MyInfo.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		tv_public_top_title.setText("我的账号");
		adapter = new MyInfo_Adapter(activity, bitmapUtils);
		// 滑动时停止加载图片
		lv_shop_body.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
		lv_shop_body.setAdapter(adapter);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		dataList.add(new BasicNameValuePair("昵称", "花朋友"));
		dataList.add(new BasicNameValuePair("管理收货地址", ""));
		dataList.add(new BasicNameValuePair("手机号", "159*****320"));
		dataList.add(new BasicNameValuePair("邀请码", "未填写，立奖1元"));
		dataList.add(new BasicNameValuePair("性别", "男"));
		dataList.add(new BasicNameValuePair("生日", "1997年12月"));
		dataList.add(new BasicNameValuePair("账户状态", "正常"));
		dataList.add(new BasicNameValuePair("修改密码", ""));
		dataList.add(new BasicNameValuePair("退出登录", ""));
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}