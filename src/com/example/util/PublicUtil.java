package com.example.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.androidquery.AQuery;
import com.example.activity.main.KeyGuardApplication;
import com.example.entity.Download_APK_Install;
import com.example.entity.UserInfo;
import com.example.http.base.BaseResponse;
import com.example.http.base.Code;
import com.example.http.base.HttpCallBack;
import com.example.http.base.Protocol;
import com.example.keyguard.R;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.SmsShareContent;
import com.umeng.socialize.media.TencentWbShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SmsHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

/**
 * @Description 公共工具类
 * @author Created by qinxianyuzou on 2014-12-24.
 */
public class PublicUtil {
	private final static String TAG = PublicUtil.class.getSimpleName();
	/** 下载目录 **/
	public final static String DOWNLOAD_APP_PATH = Environment.getExternalStorageDirectory() + File.separator
			+ "SuoPingZhuan";

	/**
	 * @Description 短时间toast
	 * @author Created by qinxianyuzou on 2014-12-24.
	 * @param context
	 * @param text
	 *            内容
	 */
	public static void showToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	public static boolean isApkDebugable() {
		try {
			ApplicationInfo info = KeyGuardApplication.getInstance().getApplicationInfo();
			return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
		} catch (Exception e) {

		}
		return false;
	}

	/**
	 * @Description 获取用户实体缓存
	 * @author Created by qinxianyuzou on 2015-1-1.
	 * @param context
	 * @return
	 */
	public static UserInfo getUserInfo_Entity(Context context) {
		UserInfo userInfo = new Gson().fromJson(getUserInfo_String(context), UserInfo.class);
		return userInfo;
	}

	/**
	 * @Description 用户实体转字符串
	 * @author Created by qinxianyuzou on 2015-2-5.
	 * @param userInfo
	 * @return
	 */
	public static String userInfoToString(UserInfo userInfo) {
		String userString = new Gson().toJson(userInfo);
		return userString;
	}

	/**
	 * @Description 保存用户信息字符串
	 * @author Created by qinxianyuzou on 2015-2-9.
	 * @param mContext
	 * @param userInfo
	 */
	public static void setUserInfo(Context mContext, String userInfo) {
		SharedPreferenceUtil.getInstance(mContext).putString(SharedPreferenceUtil.USERINFO, userInfo);
	}

	/**
	 * @Description 获取用户信息字符串
	 * @author Created by qinxianyuzou on 2015-2-9.
	 * @param mContext
	 * @return
	 */
	public static String getUserInfo_String(Context mContext) {
		return SharedPreferenceUtil.getInstance(mContext).getString(SharedPreferenceUtil.USERINFO);
	}

	/**
	 * @Description 保存cookies
	 * @author Created by qinxianyuzou on 2015-2-4.
	 * @param mContext
	 * @param rawCookies
	 */
	public static void setCookies(Context mContext, String rawCookies) {
		SharedPreferenceUtil.getInstance(mContext).putString(SharedPreferenceUtil.COOKIES, rawCookies);
	}

	/**
	 * @Description 获取cookies
	 * @author Created by qinxianyuzou on 2015-2-4.
	 * @param mContext
	 * @return
	 */
	public static String getCookies(Context mContext) {
		return SharedPreferenceUtil.getInstance(mContext).getString(SharedPreferenceUtil.COOKIES);
	}

	/**
	 * @Description 保存上次登录密码
	 * @author Created by qinxianyuzou on 2015-2-4.
	 * @param mContext
	 * @param rawCookies
	 */
	public static void setOldPassword(Context mContext, String rawCookies) {
		SharedPreferenceUtil.getInstance(mContext).putString(SharedPreferenceUtil.OLD_PASSWORD, rawCookies);
	}

	/**
	 * @Description 获取上次登录密码
	 * @author Created by qinxianyuzou on 2015-2-4.
	 * @param mContext
	 * @return
	 */
	public static String getOldPassword(Context mContext) {
		return SharedPreferenceUtil.getInstance(mContext).getString(SharedPreferenceUtil.OLD_PASSWORD);
	}

	/**
	 * @Description 保存上次登录账号
	 * @author Created by qinxianyuzou on 2015-2-4.
	 * @param mContext
	 * @param rawCookies
	 */
	public static void setOldAccount(Context mContext, String rawCookies) {
		SharedPreferenceUtil.getInstance(mContext).putString(SharedPreferenceUtil.OLD_ACCOUNT, rawCookies);
	}

	/**
	 * @Description 获取上次登陆了账号
	 * @author Created by qinxianyuzou on 2015-2-4.
	 * @param mContext
	 * @return
	 */
	public static String getOldAccount(Context mContext) {
		return SharedPreferenceUtil.getInstance(mContext).getString(SharedPreferenceUtil.OLD_PASSWORD);
	}

	/**
	 * @Description 获取上次保存的缓存
	 * @author Created by qinxianyuzou on 2015-2-4.
	 * @param mContext
	 * @return
	 */
	public static String getADCache(Context mContext) {
		return SharedPreferenceUtil.getInstance(mContext).getString(SharedPreferenceUtil.ADCACHE);
	}

	/**
	 * @Description 保存广告
	 * @author Created by qinxianyuzou on 2015-2-13.
	 * @param mContext
	 * @param rawCookies
	 */
	public static void setADCache(Context mContext, String rawCookies) {
		SharedPreferenceUtil.getInstance(mContext).putString(SharedPreferenceUtil.ADCACHE, rawCookies);
	}

	/**
	 * @Description 保存用户登录信息
	 * @author Created by qinxianyuzou on 2015-2-9.
	 * @param mContext
	 * @param cellphoneNumber
	 * @param password
	 * @param userInfo
	 */
	public static void saveLoginMsg(Context mContext, String cellphoneNumber, String password, UserInfo userInfo) {
		PublicUtil.setOldAccount(mContext, cellphoneNumber);
		PublicUtil.setOldPassword(mContext, password);
		PublicUtil.setUserInfo(mContext, PublicUtil.userInfoToString(userInfo));
	}

	/**
	 * @Description 清空用户登录信息
	 * @author Created by qinxianyuzou on 2015-2-9.
	 */
	public static void clearLoginMsg(Context mContext) {
		setCookies(mContext, "");
		setOldPassword(mContext, "");
		setUserInfo(mContext, "");
	}

	/**
	 * @Description 判断是否有网络
	 * @author Created by qinxianyuzou on 2013-12-1.
	 * @param mContext
	 * @return
	 */
	public static boolean isNetworkAvailable(Context mContext) {
		boolean flag = false;
		ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null) {
			NetworkInfo ni = cm.getActiveNetworkInfo();
			if (ni != null) {
				flag = ni.isAvailable();
			}
		}
		return flag;

	}

	/**
	 * @Description 获取软件名
	 * @author Created by qinxianyuzou on 2014-12-26.
	 * @param context
	 * @param apkPath
	 * @return
	 */
	public static String getAPPName(Context context, String apkPath) {
		PackageManager pm = context.getPackageManager();
		PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
		if (info != null) {
			ApplicationInfo appInfo = info.applicationInfo;
			appInfo.sourceDir = apkPath;
			appInfo.publicSourceDir = apkPath;
			// ActivityInfo appInfo = info.activities[0];
			// 软件名
			String label = appInfo.loadLabel(pm).toString();
			try {
				return label;
			} catch (OutOfMemoryError e) {
				Log.e("ApkIconLoader", e.toString());
			}
		}
		return "";
	}

	/**
	 * @Description 获取icon
	 * @author Created by qinxianyuzou on 2014-12-26.
	 * @param context
	 * @param apkPath
	 * @return
	 */
	public static Drawable getApkIcon(Context context, String apkPath) {
		PackageManager pm = context.getPackageManager();
		PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
		if (info != null) {
			ApplicationInfo appInfo = info.applicationInfo;
			appInfo.sourceDir = apkPath;
			appInfo.publicSourceDir = apkPath;
			try {
				return appInfo.loadIcon(pm);
			} catch (OutOfMemoryError e) {
				Log.e("ApkIconLoader", e.toString());
			}
		}
		return null;
	}

	/**
	 * @Description 获取apk版本号
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @param context
	 * @param apkPath
	 * @return
	 */
	public static int getApkVersionCode(Context context, String apkPath) {
		PackageManager pm = context.getPackageManager();
		PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
		if (info != null) {
			try {
				return info.versionCode;
			} catch (OutOfMemoryError e) {
				Log.e("ApkIconLoader", e.toString());
			}
		}
		return -1;
	}

	/**
	 * @Description 获取包名
	 * @author Created by qinxianyuzou on 2014-12-26.
	 * @param context
	 * @param apkPath
	 * @return
	 */
	public static String getPackageName(Context context, String apkPath) {
		PackageManager pm = context.getPackageManager();
		PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
		if (info != null) {
			ApplicationInfo appInfo = info.applicationInfo;
			// 包名
			String packageName = appInfo.packageName;
			try {
				return packageName;
			} catch (OutOfMemoryError e) {
				Log.e("ApkIconLoader", e.toString());
			}
		}
		return "";
	}

	/**
	 * @Description 判断是否安装
	 * @author Created by qinxianyuzou on 2014-12-26.
	 * @param context
	 * @param packagename
	 *            包名
	 * @return
	 */
	public static boolean isApkInstalled(Context context, String packagename) {
		PackageManager localPackageManager = context.getPackageManager();
		try {
			PackageInfo localPackageInfo = localPackageManager.getPackageInfo(packagename,
					PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
			return false;
		}

	}

	/**
	 * @Description 判断apk是否运行
	 * @author Created by qinxianyuzou on 2015-1-9.
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static boolean isRunningAPK(Context context, String packageName) {
		ActivityManager am = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> infos = am.getRunningAppProcesses();
		for (RunningAppProcessInfo rapi : infos) {
			if (rapi.processName.equals(packageName))
				return true;
		}
		return false;
	}

	/**
	 * @Description 安装apk
	 * @author Created by qinxianyuzou on 2014-12-26.
	 * @param context
	 * @param apkPath
	 */
	public static void installAPK(Context context, String apkPath) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.parse("file://" + apkPath), "application/vnd.android.package-archive");
		context.startActivity(intent);
		// PackageManager pManager=context.getPackageManager();
		// startInstalledBroadcast(context, getPackageName(context, apkPath));
	}

	// /**
	// * @Description 监听软件安装状态
	// * @author Created by qinxianyuzou on 2015-1-9.
	// * @param context
	// * @param packagename
	// */
	// public static void startInstalledBroadcast(Context context, String
	// packagename) {
	// IntentFilter intentFilter = new IntentFilter();
	// intentFilter.addAction("startInstalledBroadcast");
	// context.registerReceiver(new StartInstalledBroadcast(packagename),
	// intentFilter);
	// }

	/**
	 * @Description 打开apk
	 * @author Created by qinxianyuzou on 2014-12-26.
	 * @param context
	 * @param packagename
	 *            包名
	 */
	public static void openAPK(Context context, String packagename) {
		PackageManager packageManager = context.getPackageManager();
		Intent intent = packageManager.getLaunchIntentForPackage(packagename);
		context.startActivity(intent);
	}

	/**
	 * @Description 获取版本号
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return pi.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	/** 遍历文件，路径结果 List */
	public static List<String> listFile = new ArrayList<String>();

	/**
	 * 遍历文件夹搜索文件
	 * 
	 * @param Path
	 *            搜索的目录
	 * @param Extension
	 *            扩展名
	 * @param IsIterative
	 *            是否进入子文件夹
	 */
	public static void getFiles(String Path, String Extension, boolean IsIterative) {
		listFile.clear();
		File[] files = new File(Path).listFiles();
		if (files != null && files.length != 0) {
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isFile()) {
					if (f.getPath().substring(f.getPath().length() - Extension.length()).equals(Extension)) // 判断扩展名
						listFile.add(f.getPath());
					if (!IsIterative)
						break;
					// 忽略点文件（隐藏文件/文件夹）
				} else if (f.isDirectory() && f.getPath().indexOf("/.") == -1) {
					// getFiles(f.getPath(), Extension, IsIterative);
				}
			}
		}
	}

	/**
	 * @Description 设置分享平台
	 * @author Created by qinxianyuzou on 2015-1-20.
	 * @param activity
	 * @param mController
	 * @param title
	 * @param fshareMSG
	 * @param url
	 */
	public static void setShare(Activity activity, UMSocialService mController, String title, String fshareMSG,
			String url) {
		// ShareListener shareListener = new ShareListener(activity);
		// mController.postShare(activity, SHARE_MEDIA.QQ, shareListener);
		// mController.postShare(activity, SHARE_MEDIA.QZONE, shareListener);
		// mController.postShare(activity, SHARE_MEDIA.WEIXIN, shareListener);
		// mController.postShare(activity, SHARE_MEDIA.WEIXIN_CIRCLE,
		// shareListener);
		// mController.postShare(activity, SHARE_MEDIA.SINA, shareListener);
		// mController.postShare(activity, SHARE_MEDIA.SMS, shareListener);
		// mController.postShare(activity, SHARE_MEDIA.TENCENT, shareListener);
		shareQQ(activity, mController, title, fshareMSG, url);
		shareWeibo(activity, mController, title, fshareMSG, url);
		shareSMS(activity, mController, fshareMSG, url);
		shareWX(activity, mController, title, fshareMSG, url);
		shareWXCircle(activity, mController, title, fshareMSG, url);
		// // 设置分享内容
		// mController.setShareContent(fshareMSG);
		// 设置分享图片, 参数2为图片的url地址
		// mController.setShareMedia(new UMImage(activity,
		// "http://www.umeng.com/images/pic/banner_module_social.png"));
		// 设置分享图片，参数2为本地图片的资源引用
		// mController.setShareMedia(new UMImage(activity, R.drawable.logo));
		// mController.setShareMedia(new UMImage(activity, url));
	}

	/**
	 * @Description 分享微博
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @param activity
	 */
	private static void shareWeibo(Activity activity, UMSocialService mController, String title, String fshareMSG,
			String url) {
		SinaShareContent sinaShareContent = new SinaShareContent();
		// 设置朋友圈title
		sinaShareContent.setTitle(title);
		sinaShareContent.setShareContent(fshareMSG + url);
		sinaShareContent.setShareImage(new UMImage(activity, R.drawable.logo));
		if (!StringUtils.isEmpty(url)) {
			sinaShareContent.setTargetUrl(url);
		}
		mController.setShareMedia(sinaShareContent);
		TencentWbShareContent tencentWbShareContent = new TencentWbShareContent();
		// 设置朋友圈title
		tencentWbShareContent.setTitle(title);
		tencentWbShareContent.setShareContent(fshareMSG + url);
		tencentWbShareContent.setShareImage(new UMImage(activity, R.drawable.logo));
		if (!StringUtils.isEmpty(url)) {
			tencentWbShareContent.setTargetUrl(url);
		}
		mController.setShareMedia(tencentWbShareContent);
	}

	/**
	 * @Description 分享微信
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @param activity
	 */
	private static void shareWX(Activity activity, UMSocialService mController, String title, String fshareMSG,
			String url) {
		String appID = "wx2f8d9e440cb2d07d";
		String appSecret = "c4efeef102f7df2c59e12a71b2f2c59a";
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(activity, appID, appSecret);
		wxHandler.addToSocialSDK();
		// 添加微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(activity, appID, appSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
		//
		WeiXinShareContent shareContent = new WeiXinShareContent();
		// 设置朋友圈title
		shareContent.setTitle(title);
		shareContent.setShareContent(fshareMSG);
		shareContent.setShareImage(new UMImage(activity, R.drawable.logo));
		if (!StringUtils.isEmpty(url)) {
			shareContent.setTargetUrl(url);
		}
		mController.setShareMedia(shareContent);
	}

	/**
	 * @Description 分享朋友圈
	 * @author Created by qinxianyuzou on 2015-1-20.
	 * @param activity
	 * @param mController
	 * @param title
	 * @param fshareMSG
	 * @param url
	 */
	private static void shareWXCircle(Activity activity, UMSocialService mController, String title, String fshareMSG,
			String url) {
		CircleShareContent shareContent = new CircleShareContent();
		// 设置朋友圈title
		shareContent.setTitle(title);
		shareContent.setShareContent(fshareMSG);
		shareContent.setShareImage(new UMImage(activity, R.drawable.logo));
		if (!StringUtils.isEmpty(url)) {
			shareContent.setTargetUrl(url);
		}
		mController.setShareMedia(shareContent);
	}

	/**
	 * @Description 分享qq和qq空间
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @param activity
	 */
	private static void shareQQ(Activity activity, UMSocialService mController, String title, String fshareMSG,
			String url) {
		// 参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity, "100424468", "c7394704798a158208a74ab60104f0ba");
		qqSsoHandler.addToSocialSDK();
		QQShareContent shareContent = new QQShareContent();
		shareContent.setTitle(title);
		shareContent.setShareImage(new UMImage(activity, R.drawable.logo));
		shareContent.setShareContent(fshareMSG);
		if (!StringUtils.isEmpty(url)) {
			shareContent.setTargetUrl(url);
		}
		mController.setShareMedia(shareContent);
		//
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(activity, "100424468", "c7394704798a158208a74ab60104f0ba");
		qZoneSsoHandler.addToSocialSDK();
		QZoneShareContent qZoneShareContent = new QZoneShareContent();
		qZoneShareContent.setTitle(title);
		qZoneShareContent.setShareImage(new UMImage(activity, R.drawable.logo));
		qZoneShareContent.setShareContent(fshareMSG);
		if (!StringUtils.isEmpty(url)) {
			qZoneShareContent.setTargetUrl(url);
		}
		mController.setShareMedia(qZoneShareContent);
	}

	/**
	 * @Description 分享短信
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @param activity
	 */
	private static void shareSMS(Activity activity, UMSocialService mController, String fshareMSG, String url) {
		// 添加短信
		SmsHandler smsHandler = new SmsHandler();
		smsHandler.addToSocialSDK();
		SmsShareContent shareContent = new SmsShareContent();
		shareContent.setShareContent(fshareMSG + ":" + url);
		mController.setShareMedia(shareContent);
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void updateAPP(final Activity activity, String url) {
		String apkName[] = url.split("/");
		final String downFile = DOWNLOAD_APP_PATH + "/update/" + apkName[apkName.length - 1];
		HttpUtils http = new HttpUtils();
		final Notification mNotification;
		final NotificationManager mNotificationManager;
		mNotificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotification = new Notification(R.drawable.logo, "正在下载...", System.currentTimeMillis());
		mNotification.contentView = new RemoteViews(activity.getPackageName(), R.layout.download);
		mNotification.flags = Notification.FLAG_INSISTENT;
		mNotification.sound = null;
		HttpHandler handler = http.download(url, downFile, true, true, new RequestCallBack<File>() {

			@Override
			public void onStart() {
				// tv_info.setText("conn...");
				PublicUtil.showToast(activity, "正在下载...");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				// tv_info.setText(current + "/" + total);
				LogUtil.d("updateAPP", "<==========handler");
				DecimalFormat decimalFormat = new DecimalFormat("0%");
				LogUtil.d("updateAPP", "total:" + total);
				LogUtil.d("updateAPP", "current:" + current);
				LogUtil.d("updateAPP", "(float) current / (float) total:" + (float) current / (float) total);
				LogUtil.d("updateAPP", "decimalFormat:" + decimalFormat.format((float) current / (float) total));
				LogUtil.d("updateAPP", "handler==========>");
				mNotification.contentView.setTextViewText(R.id.content_view_text1,
						decimalFormat.format((float) current / (float) total));
				mNotification.contentView.setProgressBar(R.id.content_view_progress, (int) total, (int) current, false);
				mNotificationManager.notify(3566, mNotification);
			}

			@Override
			public void onSuccess(ResponseInfo<File> responseInfo) {
				mNotificationManager.cancel(3566);
				PublicUtil.showToast(activity, "下载完成...");
				PublicUtil.installAPK(activity, downFile);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// tv_info.setText(msg);
				LogUtil.d("handler", msg);
				if (msg.equals("maybe the file has downloaded completely")) {
					showToast(activity, "文件已经下载完成");
					installAPK(activity, downFile);
				}
			}

		});
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void downloadAPP(final Activity activity, final String url) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String apkName[] = url.split("/");
				// final String downFile = DOWNLOAD_APP_PATH + "/" +
				// apkName[apkName.length - 1] + ".apk";
				final String downFile = DOWNLOAD_APP_PATH + "/" + getFileName(url);
				HttpUtils http = new HttpUtils();
				final Notification mNotification;
				final NotificationManager mNotificationManager;
				mNotificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
				mNotification = new Notification(R.drawable.logo, "正在下载...", System.currentTimeMillis());
				mNotification.contentView = new RemoteViews(activity.getPackageName(), R.layout.download);
				mNotification.flags = Notification.FLAG_INSISTENT;
				mNotification.sound = null;
				HttpHandler handler = http.download(url, downFile, true, false, new RequestCallBack<File>() {

					@Override
					public void onStart() {
						// tv_info.setText("conn...");
						PublicUtil.showToast(activity, "正在下载...");
						YouMengUtil.onEvent(activity, YouMengUtil.START_DOWNLOAD);
					}

					@Override
					public void onLoading(long total, long current, boolean isUploading) {
						// tv_info.setText(current + "/" + total);
						DecimalFormat decimalFormat = new DecimalFormat("0%");
						LogUtil.d("downloadAPP",
								"decimalFormat:" + decimalFormat.format((float) current / (float) total));
						mNotification.contentView.setTextViewText(R.id.content_view_text1,
								decimalFormat.format((float) current / (float) total));
						mNotification.contentView.setProgressBar(R.id.content_view_progress, (int) total,
								(int) current, false);
						mNotificationManager.notify(3567, mNotification);
					}

					@Override
					public void onSuccess(ResponseInfo<File> responseInfo) {
						mNotificationManager.cancel(3567);
						PublicUtil.showToast(activity, "下载完成...");
						YouMengUtil.onEvent(activity, YouMengUtil.DOWNLOAD_SUCCESS);
						// PublicUtil.installAPK(activity, downFile);
						PublicUtil.installAPK(activity, responseInfo.result.toString());
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// tv_info.setText(msg);
						LogUtil.d("handler", msg);
						if (msg.indexOf("maybe the file has downloaded completely") > 0) {
							showToast(activity, "应用已经下载");
							installAPK(activity, downFile);
						} else {
							YouMengUtil.onEvent(activity, YouMengUtil.DOWNLOAD_FAILURE);
							showToast(activity, "文件下载失败，下载文件不存在");
						}
						// if (msg.indexOf("Target host must not be null") > 0)
						// {
						// YouMengUtil.onEvent(activity,
						// YouMengUtil.DOWNLOAD_FAILURE);
						// showToast(activity, "文件下载失败，下载文件不存在");
						// }
					}

				});
			}
		}).start();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void downloadAPP(final Activity activity, final String url, final Handler mHandler) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String apkName[] = url.split("/");
				// final String downFile = DOWNLOAD_APP_PATH + "/" +
				// apkName[apkName.length - 1] + ".apk";
				final String downFile = DOWNLOAD_APP_PATH + "/" + getFileName(url);
				HttpUtils http = new HttpUtils();
				final Notification mNotification;
				final NotificationManager mNotificationManager;
				mNotificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
				mNotification = new Notification(R.drawable.logo, "正在下载...", System.currentTimeMillis());
				mNotification.contentView = new RemoteViews(activity.getPackageName(), R.layout.download);
				mNotification.flags = Notification.FLAG_INSISTENT;
				mNotification.sound = null;
				HttpHandler handler = http.download(url, downFile, true, false, new RequestCallBack<File>() {

					@Override
					public void onStart() {
						// tv_info.setText("conn...");
						PublicUtil.showToast(activity, "正在下载...");
						YouMengUtil.onEvent(activity, YouMengUtil.START_DOWNLOAD);
						Message msg = new Message();
						msg.what = 0;
						mHandler.sendMessage(msg);
					}

					@Override
					public void onLoading(long total, long current, boolean isUploading) {
						// tv_info.setText(current + "/" + total);
						DecimalFormat decimalFormat = new DecimalFormat("0%");
						LogUtil.d("downloadAPP",
								"decimalFormat:" + decimalFormat.format((float) current / (float) total));
						mNotification.contentView.setTextViewText(R.id.content_view_text1,
								decimalFormat.format((float) current / (float) total));
						mNotification.contentView.setProgressBar(R.id.content_view_progress, (int) total,
								(int) current, false);
						mNotificationManager.notify(3567, mNotification);
						Message msg = new Message();
						msg.arg1 = (int) total;
						msg.arg2 = (int) current;
						msg.what = 1;
						mHandler.sendMessage(msg);
					}

					@Override
					public void onSuccess(ResponseInfo<File> responseInfo) {
						mNotificationManager.cancel(3567);
						PublicUtil.showToast(activity, "下载完成...");
						YouMengUtil.onEvent(activity, YouMengUtil.DOWNLOAD_SUCCESS);
						// PublicUtil.installAPK(activity, downFile);
						PublicUtil.installAPK(activity, responseInfo.result.toString());
						Message msg = new Message();
						msg.what = 2;
						LogUtil.d(TAG, "apkPuth:" + responseInfo.result.toString());
						msg.obj = responseInfo.result.toString();
						mHandler.sendMessage(msg);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// tv_info.setText(msg);
						LogUtil.d("handler", msg);
						Message message = new Message();
						message.what = 3;
						mHandler.sendMessage(message);
						if (msg.indexOf("maybe the file has downloaded completely") > 0) {
							showToast(activity, "应用已经下载");
							installAPK(activity, downFile);
						} else {
							YouMengUtil.onEvent(activity, YouMengUtil.DOWNLOAD_FAILURE);
							showToast(activity, "文件下载失败，下载文件不存在");
						}
						// if (msg.indexOf("Target host must not be null") > 0)
						// {
						// YouMengUtil.onEvent(activity,
						// YouMengUtil.DOWNLOAD_FAILURE);
						// showToast(activity, "文件下载失败，下载文件不存在");
						// }
					}

				});
			}
		}).start();
	}

	/**
	 * @Description 获取文件名
	 * @author Created by qinxianyuzou on 2015-1-24.
	 * @param url
	 * @return
	 */
	public static String getFileName(String url) {
		String filename = "";
		boolean isok = false;
		// 从UrlConnection中获取文件名称
		try {
			URL myURL = new URL(url);

			URLConnection conn = myURL.openConnection();
			if (conn == null) {
				return null;
			}

			BufferedInputStream bis = null;
			HttpURLConnection urlconnection = null;
			urlconnection = (HttpURLConnection) myURL.openConnection();
			urlconnection.connect();
			bis = new BufferedInputStream(urlconnection.getInputStream());
			LogUtil.d(TAG, "fileType:" + HttpURLConnection.guessContentTypeFromStream(bis));

			Map<String, List<String>> hf = conn.getHeaderFields();
			if (hf == null) {
				return null;
			}
			Set<String> key = hf.keySet();
			if (key == null) {
				return null;
			}

			for (String skey : key) {
				List<String> values = hf.get(skey);
				for (String value : values) {
					String result;
					try {
						LogUtil.d(TAG, "value:" + value);
						result = new String(value.getBytes("ISO-8859-1"), "GBK");
						int location = result.indexOf("filename");
						if (location >= 0) {
							result = result.substring(location + "filename".length());
							filename = result.substring(result.indexOf("=") + 1);
							filename = filename.substring(filename.indexOf("\"") + 1);
							filename = filename.substring(0, filename.indexOf(".apk") + 4);
							isok = true;
							LogUtil.d(TAG, filename);
						}
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}// ISO-8859-1 UTF-8 gb2312
				}
				if (isok) {
					break;
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 从路径中获取
		if (filename == null || "".equals(filename)) {
			filename = url.substring(url.lastIndexOf("/") + 1);
		}
		return filename;
	}

	/**
	 * 获取所有已下载的应用并返回集合
	 * 
	 * @return List<Download_APK_Install>
	 */
	public static List<Download_APK_Install> getDownloadAppsEntity() {
		Context ctx;
		List<Download_APK_Install> apps = new ArrayList<>();
		getFiles(DOWNLOAD_APP_PATH, "apk", true);
		if (listFile.size() != 0) {
			ctx = KeyGuardApplication.getInstance().getApplicationContext();
			for (String path : listFile) {
				Download_APK_Install apk = new Download_APK_Install();
				// apk.setAppIcon(getApkIcon(ctx, path));
				apk.setAppName(getAPPName(ctx, path));
				apk.setAppPath(path);
				apk.setInstalled(isApkInstalled(ctx, getPackageName(ctx, path)));
				apk.setFileSize(formatSizeM(getFileSize(path)));
				apps.add(apk);
			}
			return apps;
		} else {
			return apps;
		}
	}

	/**
	 * 获取所有已下载并安装的应用并返回集合
	 * 
	 * @return List<Download_APK_Install>
	 */
	public static List<Download_APK_Install> getDownloadAppsEntityIsInstalled() {
		Context ctx;
		List<Download_APK_Install> apps = new ArrayList<>();
		getFiles(DOWNLOAD_APP_PATH, "apk", true);
		if (listFile.size() != 0) {
			ctx = KeyGuardApplication.getInstance().getApplicationContext();
			for (String path : listFile) {
				Download_APK_Install apk = new Download_APK_Install();
				// apk.setAppIcon(getApkIcon(ctx, path));
				apk.setAppName(getAPPName(ctx, path));
				apk.setAppPath(path);
				apk.setInstalled(isApkInstalled(ctx, getPackageName(ctx, path)));
				apk.setFileSize(formatSizeM(getFileSize(path)));
				if (apk.isInstalled()) {
					apps.add(apk);
				}
			}
			return apps;
		} else {
			return apps;
		}
	}

	/**
	 * 根据路径获取文件大小
	 * 
	 * @param path
	 * @return 大小 单位m
	 */
	public static double getFileSize(String path) {
		File f = new File(path);
		if (f.exists() && f.isFile()) {
			double size = f.length();
			return size / 1024 / 1024;
		} else {
			return 0;
		}
	}

	/**
	 * 格式化文件大小保留两位小数并且后面带m
	 * 
	 * @param size
	 * @return 字符串size 后面带 m
	 */
	public static String formatSizeM(double size) {
		DecimalFormat df = new DecimalFormat("#.00");
		return String.valueOf(df.format(size)) + "m";
	}

	/**
	 * @Description 计算软件打开时间
	 * @author Created by qinxianyuzou on 2015-1-26.
	 * @param activity
	 * @param tag
	 * @param packagename
	 * @param howLong
	 * @param url
	 */
	public static void APPTiming(final Context activity, final String tag, final String packagename, final int howLong,
			final String url) {
		openAPK(activity, packagename);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				boolean isRunning = true;
				int timing = 0;
				// PublicUtil.showToast(activity, "至少要运行"+howLong+"秒");
				while (isRunning) {
					try {
						Thread.sleep(1000);
						if (isRunningAPK(activity, packagename)) {
							timing++;
							LogUtil.d(tag, "使用了" + timing + "秒");
							if (timing > howLong) {
								// 发送接口
								Protocol.signinSuccess(activity, tag, url, new HttpCallBack() {

									@Override
									public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
										// TODO Auto-generated method stub
										BaseResponse msginfo = (BaseResponse) response;
										if (msginfo.getCode().equals(Code.CODE_SUCCESS)) {
											showToast(activity, msginfo.getMsg());
										}
									}

									@Override
									public void onHttpError(long flag, VolleyError error) {
										// TODO Auto-generated method stub

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

	/**
	 * 判断程序是否在前台运行
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isTopActivity(Context context) {
		ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = mActivityManager.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			// 应用程序位于堆栈的顶层
			if (context.getPackageName().equals(tasksInfo.get(0).topActivity.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @Description 判断程序是否在前台运行
	 * @author Created by qinxianyuzou on 2015-2-12.
	 * @param packageName
	 * @param context
	 * @return
	 */
	public static boolean isTopActivity(String packageName, Context context) {
		ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = mActivityManager.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			// 应用程序位于堆栈的顶层
			if (packageName.equals(tasksInfo.get(0).topActivity.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @Description 加载网络图片
	 * @author Created by qinxianyuzou on 2015-2-6.
	 * @param aQuery
	 * @param imageView
	 * @param url
	 * @param fallbackId
	 */
	public static void loadNetImage(AQuery aQuery, ImageView imageView, String url, int fallbackId) {
		boolean memCache = false;
		boolean fileCache = true;
		aQuery.id(imageView).image(url, memCache, fileCache, 200, fallbackId);
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2015-2-6.
	 * @param aQuery
	 * @param progressBar
	 *            进度条
	 * @param imageView
	 *            图片
	 * @param url
	 *            地址
	 * @param fallbackId
	 *            失败默认图
	 */
	public static void loadNetImage(AQuery aQuery, ProgressBar progressBar, ImageView imageView, String url,
			int fallbackId) {
		boolean memCache = false;
		boolean fileCache = true;
		aQuery.id(imageView).progress(progressBar).image(url, memCache, fileCache, 0, fallbackId);
	}
}
