package com.example.activity.shop;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.Activity_Submit;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.DemoActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.http.base.BaseResponse;
import com.example.http.base.Code;
import com.example.http.base.Protocol;
import com.example.http.respose.ResponseEPDetail;
import com.example.keyguard.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 兑换详情页
 * @author Created by qinxianyuzou on 2014-12-29.
 */
public class Activity_ShopInfo extends BaseActivity {

	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 返回按钮 */
	@ViewInject(R.id.rl_public_back)
	private RelativeLayout rl_public_back;
	/** 大图 */
	@ViewInject(R.id.iv_shopinfo_icon)
	private ImageView iv_shopinfo_icon;
	/** 兑换按钮 */
	@ViewInject(R.id.but_shopinfo_exchange)
	private Button but_shopinfo_exchange;
	/** 标题 */
	@ViewInject(R.id.tv_shopinfo_title)
	private TextView tv_shopinfo_title;
	/** 需要积分 */
	@ViewInject(R.id.tv_shopinfo_need_jifen)
	private TextView tv_shopinfo_need_jifen;
	/** 介绍 */
	@ViewInject(R.id.tv_shopinfo_introduce)
	private TextView tv_shopinfo_introduce;
	/** 使用说明 */
	@ViewInject(R.id.tv_shopinfo_usage_intro)
	private TextView tv_shopinfo_usage_intro;
	/** 标题 */
	private static String mTitle = "";
	/** 商品id */
	private static String mEpid = "";
	private long epdetailFalg;
	private long exchange_productFalg;
	/** 图片加载工具 */
	private BitmapUtils bitmapUtils;

	/**
	 * @Description 不设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 */
	public static void luanch(Activity activity) {
		Intent intent = new Intent(activity, Activity_ShopInfo.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	/**
	 * @Description 设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 * @param title
	 */
	public static void luanch(Activity activity, String title, String epid) {
		mTitle = title;
		mEpid = epid;
		Intent intent = new Intent(activity, Activity_ShopInfo.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopinfo);
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
		but_shopinfo_exchange.setOnClickListener(this);
		bitmapUtils = new BitmapUtils(activity);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		epdetailFalg = Protocol.get_ep_detail(activity, setTag(), mEpid);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_public_back:
			finish();
			break;
		case R.id.but_shopinfo_exchange:
			//exchange_productFalg = Protocol.exchange_product(activity, setTag(), mEpid);
			break;

		default:
			break;
		}
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub
		if (flag == epdetailFalg) {
			ResponseEPDetail responseEPDetail = (ResponseEPDetail) response;
			if (responseEPDetail.getCode().equals(Code.CODE_SUCCESS)) {
				tv_shopinfo_title.setText(responseEPDetail.getData().getTitle());
				tv_shopinfo_introduce.setText(responseEPDetail.getData().getIntroduce());
				tv_shopinfo_need_jifen.setText(responseEPDetail.getData().getNeed_jifen());
				tv_shopinfo_usage_intro.setText(responseEPDetail.getData().getUsage_intro());
				bitmapUtils.display(iv_shopinfo_icon, responseEPDetail.getData().getPicture_url().get(0));
			} else {
				showToast(responseEPDetail.getMsg());
			}
		}
		if (flag == exchange_productFalg) {
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
		return this.getClass().getSimpleName();
	}

}
