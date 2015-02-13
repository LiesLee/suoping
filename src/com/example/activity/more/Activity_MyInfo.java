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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.http.base.BaseResponse;
import com.example.http.base.Code;
import com.example.http.base.Protocol;
import com.example.http.respose.ResponseUserInfo;
import com.example.keyguard.R;
import com.example.util.PublicUtil;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 我的账号
 * @author Created by qinxianyuzou on 2014-12-29.
 */
public class Activity_MyInfo extends BaseActivity {
	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 返回按钮 */
	@ViewInject(R.id.rl_public_back)
	private RelativeLayout rl_public_back;
	/** 列表 */
	@ViewInject(R.id.lv_shop_body)
	private ListView lv_shop_body;
	private MyInfo_Adapter adapter;
	/** 图片加载工具 */
	private BitmapUtils bitmapUtils;
	/** 保存数据源 */
	private List<NameValuePair> dataList = new ArrayList<NameValuePair>();
	public static long sexFlag;
	public static long dateFlag;

	public static void luanch(Activity activity) {
		Intent intent = new Intent(activity, Activity_MyInfo.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);
		ViewUtils.inject(activity);
		initUI();
		initData();
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		tv_public_top_title.setText("我的账号");
		rl_public_back.setVisibility(View.VISIBLE);
		rl_public_back.setOnClickListener(this);
		adapter = new MyInfo_Adapter(activity, bitmapUtils);
		lv_shop_body.setAdapter(adapter);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		dataList.clear();
		// dataList.add(new BasicNameValuePair("昵称", "花朋友"));
		dataList.add(new BasicNameValuePair("管理收货地址", ""));
		// dataList.add(new BasicNameValuePair("手机号", "159*****320"));
		// dataList.add(new BasicNameValuePair("邀请码", ""));
		dataList.add(new BasicNameValuePair("性别", PublicUtil.getUserInfo_Entity(activity).getSex()));
		dataList.add(new BasicNameValuePair("生日", PublicUtil.getUserInfo_Entity(activity).getBirthday()));
		// dataList.add(new BasicNameValuePair("账户状态", "正常"));
		// dataList.add(new BasicNameValuePair("修改密码", ""));
		dataList.add(new BasicNameValuePair("退出登录", ""));
		adapter.setData(dataList);

	}

	private long userFlag;

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub
		if (sexFlag == flag) {
			BaseResponse msgInfo = (BaseResponse) response;
			if (msgInfo.getCode().equals(Code.CODE_SUCCESS)) {
				userFlag = Protocol.get_user_info(activity, setTag());
			}
			showToast(msgInfo.getMsg());
		}
		if (dateFlag == flag) {
			BaseResponse msgInfo = (BaseResponse) response;
			if (msgInfo.getCode().equals(Code.CODE_SUCCESS)) {
				userFlag = Protocol.get_user_info(activity, setTag());
			}
			showToast(msgInfo.getMsg());
		}
		if (userFlag == flag) {
			ResponseUserInfo msg = (ResponseUserInfo) response;
			if (msg.getCode().equals(Code.CODE_SUCCESS)) {
				PublicUtil.setUserInfo(activity, PublicUtil.userInfoToString(msg.getData()));
				initData();
			}
		}
	}

	@Override
	public void onHttpError(long flag, VolleyError error) {
		// TODO Auto-generated method stub

	}

	@Override
	public String setTag() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_public_back:
			finish();
			break;

		default:
			break;
		}
	}

}
