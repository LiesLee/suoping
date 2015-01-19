package com.example.activity.common;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.entity.respose.BaseResponse;
import com.example.entity.respose.Code;
import com.example.http.Protocol;
import com.example.keyguard.R;
import com.example.util.PublicUtil;
import com.example.util.SharedPreferenceUtil;
import com.example.util.UIHelper;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 单内容提交
 * @author Created by qinxianyuzou on 2014-12-29.
 */
public class Activity_Submit extends BaseActivity {
	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 返回按钮 */
	@ViewInject(R.id.rl_public_back)
	private RelativeLayout rl_public_back;
	/** 单价 */
	@ViewInject(R.id.tv_submit_order_danjia)
	private TextView tv_submit_order_danjia;
	/** 总价 */
	@ViewInject(R.id.tv_submit_order_1)
	private TextView tv_submit_order_1;
	/** 余额 */
	@ViewInject(R.id.tv_submit_order_2)
	private TextView tv_submit_order_2;
	/** 提交按钮 */
	@ViewInject(R.id.but_submit_order_submit)
	private Button but_submit_order_submit;
	private static String title = "";
	private static String mId = "";
	/** 单价 */
	private static String mDanJia = "";
	private long exchange_productFalg;

	public static void luanch(Activity activity, String title, String danjia, String id) {
		Activity_Submit.title = title;
		mId = id;
		mDanJia = danjia;
		Intent intent = new Intent(activity, Activity_Submit.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit_order);
		ViewUtils.inject(activity);
		initUI();
		initData();
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		tv_public_top_title.setText(title);
		rl_public_back.setVisibility(View.VISIBLE);
		rl_public_back.setOnClickListener(this);
		but_submit_order_submit.setOnClickListener(this);
		tv_submit_order_danjia.setText(mDanJia);
		tv_submit_order_1.setText(mDanJia);
		tv_submit_order_2.setText("" + PublicUtil.getUserInfo(activity).getSum_earn());
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub
		if (flag == exchange_productFalg) {
			UIHelper.cancelProgressDialog();
			BaseResponse baseResponse = (BaseResponse) response;
			if (baseResponse.getCode().equals(Code.CODE_SUCCESS)) {
				showToast(baseResponse.getMsg());
				finish();
			} else {
				showToast(baseResponse.getMsg());
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
		return Activity_Submit.class.getSimpleName();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_public_back:
			finish();
			break;
		case R.id.but_submit_order_submit:
			UIHelper.showMsgProgressDialog(activity, "正在加载...");
			exchange_productFalg = Protocol.exchange_product(activity, setTag(), mId);
			break;

		default:
			break;
		}
	}

}
