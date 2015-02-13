package com.example.activity.common;

import java.io.File;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.http.base.BaseResponse;
import com.example.http.base.Code;
import com.example.http.base.HttpCallBack;
import com.example.http.base.Protocol;
import com.example.http.respose.ResponseDownAPP;
import com.example.keyguard.R;
import com.example.util.LogUtil;
import com.example.util.PublicUtil;
import com.example.util.StringUtils;
import com.example.util.UIHelper;
import com.example.util.YouMengUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.umeng.analytics.MobclickAgent;

/**
 * @Description 下载web页
 * @author Created by qinxianyuzou on 2014-12-30.
 */
public class Activity_DownloadWeb extends BaseActivity implements ADD_APK_Interface {

	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 返回按钮 */
	@ViewInject(R.id.rl_public_back)
	private RelativeLayout rl_public_back;
	/** 下载状态 */
	@ViewInject(R.id.ll_down_btn)
	private LinearLayout ll_down_btn;
	/** 进度条 */
	@ViewInject(R.id.download_pb)
	private ProgressBar download_pb;
	/** 暂停按钮 */
	@ViewInject(R.id.btn_download_stop)
	private Button btn_download_stop;
	/** 取消按钮 */
	@ViewInject(R.id.btn_download_cancle)
	private Button btn_download_cancle;
	/** 安装按钮 */
	@ViewInject(R.id.btn_down_install)
	private Button btn_down_install;
	/** 启动按钮 */
	@ViewInject(R.id.btn_down_open)
	private Button btn_down_open;
	/** 下载按钮 */
	@ViewInject(R.id.but_down_web)
	private Button but_down_web;
	/** web控件 */
	@ViewInject(R.id.wv_public_web)
	private WebView wv_public_web;
	private static String mTitle = "应用详情";
	private static String mId = "";
	private static int mExper_time;

	private static String mPackageName = "";
	/** 文件地址 */
	private String mInstallPush = "";

	// private DownAPKInstalledBroadcast receiveBroadCast;
	public static ADD_APK_Interface add_APK_Interface;
	private long downFlag;

	public static void luanch(Activity activity, String id, String packageName, int exper_time) {
		mId = id;
		mExper_time = exper_time;
		mPackageName = packageName;
		Intent intent = new Intent(activity, Activity_DownloadWeb.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	public static void luanch(Activity activity, String title, String id) {
		mTitle = title;
		mId = id;
		Intent intent = new Intent(activity, Activity_DownloadWeb.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_down_web2);
		ViewUtils.inject(activity);
		add_APK_Interface = this;
		initUI();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onResume(this);
		if (PublicUtil.isApkInstalled(activity, mPackageName)) {
			btn_down_install.setVisibility(View.GONE);
			btn_down_open.setVisibility(View.VISIBLE);
			but_down_web.setVisibility(View.GONE);
		} else {
			if (StringUtils.isEmpty(mInstallPush)) {
				btn_down_install.setVisibility(View.GONE);
				btn_down_open.setVisibility(View.GONE);
				but_down_web.setVisibility(View.VISIBLE);
			} else {
				btn_down_install.setVisibility(View.VISIBLE);
				btn_down_open.setVisibility(View.GONE);
				but_down_web.setVisibility(View.GONE);
			}
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		tv_public_top_title.setText(mTitle);
		rl_public_back.setVisibility(View.VISIBLE);
		rl_public_back.setOnClickListener(this);
		but_down_web.setOnClickListener(this);
		btn_down_install.setOnClickListener(this);
		btn_down_open.setOnClickListener(this);
		but_down_web.setText("立即下载");
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		setWebView();
		String urlString = "http://client.duowanka.com/wget_earn_detail?eaid=" + mId;
		LogUtil.d(setTag(), urlString);
		wv_public_web.loadUrl(urlString);
		isDownLoad();
	}

	private void isDownLoad() {
		String tempPath = "";
		for (int i = 0; i < PublicUtil.getDownloadAppsEntity().size(); i++) {
			String appPath = PublicUtil.getDownloadAppsEntity().get(i).getAppPath();
			String packageName = PublicUtil.getPackageName(activity, appPath);
			if (mPackageName.equals(packageName)) {
				if (StringUtils.isEmpty(tempPath)) {
					tempPath = appPath;
				} else {
					File file1 = new File(tempPath);
					File file2 = new File(appPath);
					if (file1.lastModified() < file2.lastModified()) {
						tempPath = appPath;
					}
				}
			}
		}
		mInstallPush = tempPath;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		// try {
		// unregisterReceiver(receiveBroadCast);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		mExper_time = 0;
		mId = "";
		mPackageName = "";
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.rl_public_back:
			finish();
			break;
		case R.id.but_down_web:
			// PublicUtil.downloadAPP(activity,
			// "http://static.duowanka.com/downloadApp");
			YouMengUtil.onEvent(activity, YouMengUtil.CLICK_DOWNLOAD);
			UIHelper.showMsgProgressDialog(activity, "正在加载...");
			downFlag = Protocol.get_earn_downloadurl(activity, setTag(), mId);
			break;
		case R.id.btn_down_install:
			PublicUtil.installAPK(activity, mInstallPush);
			break;
		case R.id.btn_down_open:
			PublicUtil.openAPK(activity, mPackageName);
			countTime();
			break;

		default:
			break;
		}
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub
		if (flag == downFlag) {
			UIHelper.cancelProgressDialog();
			final ResponseDownAPP responseDownAPP = (ResponseDownAPP) response;
			if (responseDownAPP.getCode().equals(Code.CODE_SUCCESS)) {
				but_down_web.setText("正在下载...");
				PublicUtil.downloadAPP(activity, responseDownAPP.getData().getDownload_url(), updateHandler);
			} else {
				showToast(responseDownAPP.getMsg());
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
			if (!PublicUtil.isNetworkAvailable(activity)) {
				PublicUtil.showToast(activity, "无法与服务器通讯，请连接到移动数据或wifi");
				return;
			}
			UIHelper.showMsgProgressDialog(activity, "");
			super.onPageStarted(view, url, favicon);
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

	Handler updateHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			// 开始
			case 0:
				// mPackageName = "";
				mInstallPush = "";
				but_down_web.setText("正在下载...");
				but_down_web.setClickable(false);
				download_pb.setVisibility(View.VISIBLE);
				// but_down_web.setVisibility(View.GONE);
				// ll_down_btn.setVisibility(View.VISIBLE);
				break;
			// 下载中
			case 1:
				// but_down_web.setText("正在下载..." + msg.obj);
				download_pb.setMax(msg.arg1);
				download_pb.setProgress(msg.arg2);
				break;
			// 下载成功
			case 2:
				but_down_web.setText("下载完成");
				mInstallPush = msg.obj.toString();
				// mPackageName = PublicUtil.getPackageName(activity,
				// mInstallPush);
				// LogUtil.d(setTag(), "mPackageName:" + mPackageName);
				break;
			// 下载失败
			case 3:
				but_down_web.setText("点击下载");
				download_pb.setVisibility(View.GONE);
				but_down_web.setClickable(true);
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	private void countTime() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				boolean isRunning = true;
				int timing = 0;
				// PublicUtil.showToast(activity,
				// "至少要运行"+howLong+"秒");
				LogUtil.d(setTag(), "至少要运行" + mExper_time);
				while (isRunning) {
					try {
						Thread.sleep(1000);
						if (PublicUtil.isRunningAPK(activity, mPackageName)
								&& PublicUtil.isTopActivity(activity, mPackageName)) {
							timing++;
							LogUtil.d(setTag(), "使用了" + timing + "秒");
							if (timing > mExper_time) {
								// 发送接口
								Protocol.APP_RETURN(activity, setTag(), mId, new HttpCallBack() {

									@Override
									public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
										// TODO Auto-generated
										// method stub
										BaseResponse msg = (BaseResponse) response;
										showToast(msg.getMsg());
									}

									@Override
									public void onHttpError(long flag, VolleyError error) {
										// TODO Auto-generated
										// method stub

									}
								});
								isRunning = false;
							}
						} else {
							YouMengUtil.onEventValue(activity, YouMengUtil.APP_START_TIME_LONG, timing);
							isRunning = false;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void addAPK(String packageName) {
		// TODO Auto-generated method stub
		if (!StringUtils.isEmpty(mPackageName) && packageName.equals(mPackageName)) {
			LogUtil.d(setTag(), "openAPK");
			PublicUtil.openAPK(activity, packageName);
			countTime();
		}
	}
}
