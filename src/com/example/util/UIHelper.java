package com.example.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.androidquery.AQuery;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardApplication;
import com.example.keyguard.R;
import com.example.ui.pull.RefleshListView;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class UIHelper {

	public static final int ACT_TRAN_HEAD = 43;

	/**
	 * 显示软键盘
	 * 
	 * @param context
	 * @param inputMehod
	 *            InputMethodManager.HIDE_NOT_ALWAYS 有焦点才显示
	 *            InputMethodManager.RESULT_UNCHANGED_HIDDEN 焦点不能改变隐藏
	 */
	public static void showInputMethodWinods(Context context, int inputMehod) {
		InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		manager.toggleSoftInput(0, inputMehod);
	}

	// 隐藏软键盘
	public static void hideInputMethodWinods(Context context) {
		InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		View rootView = ((Activity) context).getCurrentFocus();
		if (rootView != null && rootView.getWindowToken() != null) {
			manager.hideSoftInputFromWindow(rootView.getWindowToken(), 0); // 强制隐藏键盘
		}
	}

	// 获取EditText焦点
	public static void getEditTextRequest(EditText editText) {
		editText.setFocusable(true);
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
	}

	/**
	 * 获取屏幕分辨率：宽
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenPixWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		if (!(context instanceof Activity)) {
			dm = context.getResources().getDisplayMetrics();
			return dm.widthPixels;
		}

		WindowManager wm = ((Activity) context).getWindowManager();
		if (wm == null) {
			dm = context.getResources().getDisplayMetrics();
			return dm.widthPixels;
		}

		wm.getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕分辨率：高
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenPixHeight(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		if (!(context instanceof Activity)) {
			dm = context.getResources().getDisplayMetrics();
			return dm.heightPixels;
		}

		WindowManager wm = ((Activity) context).getWindowManager();
		if (wm == null) {
			dm = context.getResources().getDisplayMetrics();
			return dm.heightPixels;
		}

		wm.getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;

	}

	/** 获取状态栏的高度 */
	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		java.lang.reflect.Field field = null;
		int x = 0;
		int statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
			return statusBarHeight;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusBarHeight;
	}

	// 方法名:getAttributes </br> 详述:返回一个Attributes
	public static int[] getAttributes(Context context, Double width, Double heigth) {
		WindowManager m = ((Activity) context).getWindowManager();
		Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用//
		int heigths = (int) (d.getHeight() * heigth); // 高度设置为屏幕的0.6
		int widths = (int) (d.getWidth() * width); // 宽度设置为屏幕的0.95
		int[] newArrribute = new int[] { widths, heigths };
		return newArrribute;
	}

	/**
	 * 将文本设置到剪切板中
	 * 
	 * @param context
	 * @param str
	 * @time 2011-6-27 下午02:53:41
	 * @author:linyg
	 */
	public static boolean setClipboard(Context context, String str) {
		try {
			ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
			clip.setText(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 从剪切板中读取文本
	 * 
	 * @param context
	 * @return
	 * @time 2011-6-27 下午02:53:56
	 * @author:linyg
	 */
	public static String getClipboard(Context context) {
		ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		return clip.getText().toString();
	}

	/**
	 * 将字符串转换成背景颜色
	 * 
	 * @param strColor
	 * @return
	 * @time 2011-6-28 下午02:52:56
	 * @author:linyg
	 */
	public static int stringToRGBColor(String strColor) {
		int red = 0, green = 0, blue = 0;
		if (strColor != null) {
			strColor = strColor.replace("#", "");
			if (strColor.length() == 6) {
				try {
					String strRed = strColor.substring(0, 2);
					String strGreen = strColor.substring(2, 4);
					String strBlue = strColor.substring(4, 6);

					red = Integer.parseInt(strRed, 16);
					green = Integer.parseInt(strGreen, 16);
					blue = Integer.parseInt(strBlue, 16);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Color.rgb(red, green, blue);
	}

	/**
	 * 默认时间LENGTH_SHORT
	 * 
	 * @param msg
	 */
	public static void showToast(Context context, Toast toast, String msg) {
		showToast(context, toast, msg, Toast.LENGTH_SHORT);
	}

	/***
	 * @param msg
	 * @param length
	 *            显示时间
	 */
	public static void showToast(Context context, Toast toast, String msg, int length) {
		if (toast == null) {
			toast = Toast.makeText(context, msg, length);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}



	public interface OnDialogClickListener {
		public void onClick();
	}

	/**
	 * 获取当前手机的独立像素
	 * 
	 * @param context
	 * @return
	 */
	public static float getDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}

	/**
	 * dp2px
	 */
	public static int dip2px(float dipValue) {
		return (int) (dipValue * getDensity(KeyGuardApplication.getInstance()) + 0.5f);
	}

	/**
	 * px2dp
	 */
	public static int px2dip(float pxValue) {
		return (int) (pxValue / getDensity(KeyGuardApplication.getInstance()) + 0.5f);
	}

	/**
	 * 测量View的高度
	 * 
	 * @param view
	 * @return
	 */
	public static int getMeasureHeight(View view) {
		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		return view.getMeasuredHeight();
	}

	/**
	 * 测量View的宽度
	 * 
	 * @param view
	 * @return
	 */
	public static int getMeasureWidth(View view) {
		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		return view.getMeasuredWidth();
	}

	public static View getListHeadView(Context context) {
		View listViewHead = new View(context);
		listViewHead.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, UIHelper.dip2px(43)));
		return listViewHead;
	}

	public static View getHeadView(Context context) {
		View listViewHead = new View(context);
		listViewHead.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, UIHelper.dip2px(43)));
		return listViewHead;
	}





	public interface OnFunctionClickListener {
		public void onClick();
	}



	public static void showShakeAnim(Context context, View view, String toast) {
		Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
		view.startAnimation(shake);
		view.requestFocus();
		((BaseActivity) context).showToast(toast);

	}



	public interface OnClassClickListener {
		public void onClick(String grade, String index, String className);
	}

	public interface OnSubjectClickListener {
		public void onClick(String subjectCode, String subjectName);
	}

	public interface OnTimeClickListener {
		public void onClick(String data, String pm, String hour, String minute);
	}


/*

	// 判断是否有本地更新包
	public static boolean installApp(final Activity activity, ResponseCheckUpgrade msg) {
		final File file = DownloadManager.getDownloadManager(activity).getFile(msg.getDlUrl());
		if (file != null && file.exists()) {
			if (msg.getType() == 1) {
				Dialog dialog = UIHelper.showTowButtonDialog(activity, "版本升级", msg.getVersionDesc(), "稍后再说", "立即安装",
						new OnDialogClickListener() {

							@Override
							public void onClick() {
								long currentTime = System.currentTimeMillis();
								Cookies.getUserInfo().setLastUpgradeTime(currentTime);
							}
						}, new OnDialogClickListener() {

							@Override
							public void onClick() {
								Intent intent = new Intent(Intent.ACTION_VIEW);
								intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
								activity.startActivity(intent);
							}
						});
				dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
					@Override
					public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
						if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
							return true;
						} else {
							return false;
						}
					}
				});
			} else if (msg.getType() == 2) {
				Dialog dialog = UIHelper.showOneButtonDialog(activity, "版本升级", msg.getVersionDesc(), "立即安装",
						new OnDialogClickListener() {

							@Override
							public void onClick() {
								Intent intent = new Intent(Intent.ACTION_VIEW);
								intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
								activity.startActivity(intent);
							}
						}, false, false);

				dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
					@Override
					public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
						if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
							return true;
						} else {
							return false;
						}
					}
				});
			}

			return true;
		}
		return false;
	}
*/





	public static void openBrowser(Context context, String url) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		LogUtil.d("down", "" + url);
		Uri content_url = Uri.parse(url);
		intent.setData(content_url);
		context.startActivity(intent);
	}
	/**
	 * 移动rListView的Y轴距离
	 * 
	 * @param rListView
	 * @param y
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public static void moveRListViewY(RefleshListView rListView, int y) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			rListView.setScrollY(y);
		}
	}


	public static void start2App(Context context, String app_package, String app_mainclass) {
		ComponentName componetName = new ComponentName(app_package, app_mainclass);
		try {
			Intent intent = new Intent();
			intent.setComponent(componetName);
			context.startActivity(intent);
		} catch (Exception e) {
			// 如果没找到 可以给出提示 或者其他操作
			e.printStackTrace();
		}
	}

	/**
	 * 获取app版本号
	 * 
	 * @param context
	 * @return versionName
	 */
	public static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			versionName = packageInfo.versionName;
			if (TextUtils.isEmpty(versionName)) {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}

    public static int dp2px(Context context, int dp) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        display.getMetrics(displaymetrics);

        return (int) (dp * displaymetrics.density + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


//====================================进度条====================================================
    public static SweetAlertDialog pDialog;

    /** 显示一个带文字的进度条
     * @param context
     * @param msg
     */
    public static void showMsgProgressDialog(Context context , String msg){
        if(pDialog!=null){
            pDialog.cancel();
            pDialog = null;
        }
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(msg);
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
    }

    /** 取消进度条 **/
    public static void cancelProgressDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog.cancel();
        }
    }


}
