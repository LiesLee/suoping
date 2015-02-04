package com.example.activity.shop;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.Activity_Submit;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.DialogClick;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.http.base.BaseResponse;
import com.example.http.base.Code;
import com.example.keyguard.R;
import com.example.util.PublicUtil;
import com.example.util.UIHelper;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 商品web页
 * @author Created by qinxianyuzou on 2014-12-30.
 */
public class Activity_ShopInfoWeb extends BaseActivity {

	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 返回按钮 */
	@ViewInject(R.id.rl_public_back)
	private RelativeLayout rl_public_back;
	/** 兑换按钮 */
	@ViewInject(R.id.but_down_web)
	private Button but_down_web;
	/** web控件 */
	@ViewInject(R.id.wv_public_web)
	private WebView wv_public_web;
	private static String mTitle = "";
	private static String mId = "";
	/** 单价 */
	private static String mDanJia = "";
	/** 单价 */
	private static String mStatus = "";
	/** 单价 */
	private static int mShopType;
	private static String mSendMsg = "";
	/** 页面类型 */
	private static int mType;
	private long exchange_productFalg;

	public static void luanch(Activity activity, String title, String danjia, String id, int type, int shopType) {
		mShopType = shopType;
		mTitle = title;
		mId = id;
		mDanJia = danjia;
		mType = type;
		Intent intent = new Intent(activity, Activity_ShopInfoWeb.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	public static void luanch(Activity activity, String title, String status, String sendMsg, String id, int type) {
		mTitle = title;
		mId = id;
		mStatus = status;
		mSendMsg = sendMsg;
		mType = type;
		Intent intent = new Intent(activity, Activity_ShopInfoWeb.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_down_web);
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
		but_down_web.setOnClickListener(this);
		but_down_web.setText("立即兑换");
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		setWebView();
		String urlString = "http://client.duowanka.com/wget_ep_detail?epid=" + mId;
		wv_public_web.loadUrl(urlString);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_public_back:
			finish();
			break;
		case R.id.but_down_web:
			// UIHelper.showMsgProgressDialog(activity, "正在加载...");
			// exchange_productFalg = Protocol.exchange_product(activity,
			// setTag(), mId);
			if (mType == 0) {
				Activity_Submit.luanch(activity, "订单操作", mTitle, mDanJia, mId, mShopType);
			} else {
				final DialogClick dialogClick1 = new DialogClick(activity);
				dialogClick1.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialogClick1.show();
				dialogClick1.setContent("温馨提示", mSendMsg, mStatus, "知道了", new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialogClick1.dismiss();
					}
				});
			}
			break;

		default:
			break;
		}
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
		return null;
	}

	/**
	 * @Description 设置浏览器
	 * @author Created by qinxianyuzou on 2013-12-3.
	 */
	private void setWebView() {
		WebSettings settings = wv_public_web.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setAllowFileAccess(true);// 设置允许访问文件数据
		wv_public_web.removeAllViews();
		wv_public_web.setWebViewClient(webViewClient);
		// // 开启javascript设置
		// settings.setJavaScriptEnabled(true);
		// 设置可以使用localStorage
		settings.setDomStorageEnabled(true);
		// 应用可以有数据库
		settings.setDatabaseEnabled(true);
		String dbPath = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
		settings.setDatabasePath(dbPath);
		// 应用可以有缓存
		settings.setAppCacheEnabled(true);
		String appCaceDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
		settings.setAppCachePath(appCaceDir);
		settings.setCacheMode(WebSettings.LOAD_DEFAULT); // 默认使用缓存
		settings.setAppCacheMaxSize(8 * 1024 * 1024); // 缓存最多可以有8M
		settings.setAllowFileAccess(true); // 可以读取文件缓存(manifest生效)
		//
		settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		settings.setLoadWithOverviewMode(true);
	}

	/**
	 * 自定义web视图
	 */
	private WebViewClient webViewClient = new WebViewClient() {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Log.d("shouldOverrideUrlLoading", url);
			if (PublicUtil.isNetworkAvailable(activity)) {
				UIHelper.showMsgProgressDialog(activity, "");
				view.loadUrl(url);
				return true;
			} else {
				showToast("请检查网络链接是否正常，稍后再试");
				return true;
			}
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// System.out.println("onPageStarted");
			super.onPageStarted(view, url, favicon);
			if (!PublicUtil.isNetworkAvailable(activity)) {
				PublicUtil.showToast(activity, "无法与服务器通讯，请连接到移动数据或wifi");
				return;
			}
			// QinXianYuZouTool.LoadPrompt(mContext);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			UIHelper.cancelProgressDialog();
			super.onPageFinished(view, url);
		}

		@Override
		public void onLoadResource(WebView view, String url) {
			super.onLoadResource(view, url);
		}

		@Override
		public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
			// TODO Auto-generated method stub
			handler.proceed();
			super.onReceivedSslError(view, handler, error);
		}

		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			// TODO Auto-generated method stub
			super.onReceivedError(view, errorCode, description, failingUrl);
		}

	};
}
