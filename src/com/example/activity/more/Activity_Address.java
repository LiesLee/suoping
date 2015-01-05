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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.entity.more.Logistics_Entity;
import com.example.entity.respose.Code;
import com.example.entity.respose.ResponseLogistics;
import com.example.http.Protocol;
import com.example.keyguard.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 我的账号
 * @author Created by qinxianyuzou on 2014-12-29.
 */
public class Activity_Address extends BaseActivity {
	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 返回按钮 */
	@ViewInject(R.id.rl_public_back)
	private RelativeLayout rl_public_back;
	/** 列表 */
	@ViewInject(R.id.lv_shop_body)
	private ListView lv_shop_body;
	/** 默认 */
	@ViewInject(R.id.cb_address_default)
	private CheckBox cb_address_default;
	/** 提交 */
	@ViewInject(R.id.but_address_submit)
	private Button but_address_submit;
	/**  */
	private Address_Adapter adapter;
	/** 图片加载工具 */
	private BitmapUtils bitmapUtils;
	/** 保存数据源 */
	private List<NameValuePair> dataList = new ArrayList<NameValuePair>();
	private long logisticsFlag;

	/** 标题 */
	private static String mTitle = "管理收货地址";
	private static String mId = "";

	public static Logistics_Entity logistics_Entity = new Logistics_Entity();

	/**
	 * @Description 不设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 */
	public static void luanch(Activity activity) {
		Intent intent = new Intent(activity, Activity_Address.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	/**
	 * @Description 设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 * @param title
	 */
	public static void luanch(Activity activity, String id) {
		mId = id;
		Intent intent = new Intent(activity, Activity_Address.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);
		ViewUtils.inject(activity);
		initUI();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initData();
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		tv_public_top_title.setText(mTitle);
		rl_public_back.setVisibility(View.VISIBLE);
		rl_public_back.setOnClickListener(this);
		but_address_submit.setOnClickListener(this);
		adapter = new Address_Adapter(activity, bitmapUtils);
		lv_shop_body.setAdapter(adapter);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		dataList.clear();
		dataList.add(new BasicNameValuePair("收货地址", logistics_Entity.getTo_where()));
		dataList.add(new BasicNameValuePair("收货人姓名", logistics_Entity.getTo_who()));
		dataList.add(new BasicNameValuePair("联系号码", ""));
		dataList.add(new BasicNameValuePair("邮编", logistics_Entity.getPost_no()));
		adapter.setData(dataList);
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub
		if (logisticsFlag == flag) {
			ResponseLogistics responseLogistics = (ResponseLogistics) response;
			if (responseLogistics.getCode().equals(Code.CODE_SUCCESS)) {
				showToast(responseLogistics.getMsg());
				finish();
			} else {
				showToast(responseLogistics.getMsg());
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
		return null;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_public_back:
			finish();
			break;
		case R.id.but_address_submit:
			int isDefault = cb_address_default.isChecked() ? 1 : 0;
			logisticsFlag = Protocol.add_logistics(activity, setTag(), "" + isDefault, logistics_Entity.getTo_who(),
					logistics_Entity.getTo_where(), logistics_Entity.getPost_no());
			break;

		default:
			break;
		}
	}

}
