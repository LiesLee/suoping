package com.example.activity.earnings;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.DialogClick;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.activity.more.Activity_MyInfo;
import com.example.entity.Exchange_detail;
import com.example.http.base.Code;
import com.example.http.base.Protocol;
import com.example.http.respose.ResponseExchangeDetail;
import com.example.keyguard.R;
import com.example.util.LogUtil;
import com.example.util.UIHelper;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 成功兑换
 * @author Created by qinxianyuzou on 2014-12-30.
 */
public class Activity_EarningsShop extends BaseActivity {

	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 返回按钮 */
	@ViewInject(R.id.rl_public_back)
	private RelativeLayout rl_public_back;
	/** 列表 */
	@ViewInject(R.id.lv_shop_body)
	private ListView rListView;
	/** 标题 */
	private static String mTitle = "已兑换商品";
	private long exFlag;
	private Earning_Shop_Adapter adapter;
	private List<Exchange_detail> data = new ArrayList<>();

	/**
	 * @Description 不设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 */
	public static void luanch(Activity activity) {
		Intent intent = new Intent(activity, Activity_EarningsShop.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	/**
	 * @Description 设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 * @param title
	 */
	public static void luanch(Activity activity, String title) {
		mTitle = title;
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
		tv_public_top_title.setText(mTitle);
		rl_public_back.setVisibility(View.VISIBLE);
		rl_public_back.setOnClickListener(this);

		adapter = new Earning_Shop_Adapter(activity);
		rListView.setAdapter(adapter);
		rListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				final DialogClick dialogClick1 = new DialogClick(activity);
				dialogClick1.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialogClick1.show();
				dialogClick1.setContent("温馨提示", data.get(position).getSend_msg(), data.get(position).getIs_send(),
						"知道了", new OnClickListener() {
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								dialogClick1.dismiss();
							}
						});
			}
		});
	}

	@Override
	protected void initData() {
		UIHelper.showMsgProgressDialog(this, "加载中...");
		exFlag = Protocol.get_user_ex_detail(this, setTag());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_public_back:
			this.finish();
			break;

		default:
			break;
		}
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		if (flag == exFlag) {
			UIHelper.cancelProgressDialog();
			ResponseExchangeDetail msg = (ResponseExchangeDetail) response;
			if (msg.getCode().equals(Code.CODE_SUCCESS)) {
				LogUtil.i("======ear======", jsonString.toString());
				data = msg.getData();
				adapter.setData(msg.getData());
			}
		}
	}

	@Override
	public void onHttpError(long flag, VolleyError error) {
		// TODO Auto-generated method stub
		UIHelper.cancelProgressDialog();
	}

	@Override
	public String setTag() {
		return this.getClass().getSimpleName();
	}

}
