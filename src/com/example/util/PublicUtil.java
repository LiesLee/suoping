package com.example.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.activity.common.KeyGuardApplication;
import com.example.entity.Download_APK_Install;
import com.example.entity.UserInfo;
import com.google.gson.Gson;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 公共工具类
 * @author Created by qinxianyuzou on 2014-12-24.
 */
public class PublicUtil {
	private final static String TAG = PublicUtil.class.getSimpleName();
    /** 下载目录 **/
    public final static String DOWNLOAD_APP_PATH = Environment.getExternalStorageDirectory() + File.separator + "SuoPingZhuan";

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
	public static UserInfo getUserInfo(Context context) {
		UserInfo userInfo = new Gson().fromJson(
				SharedPreferenceUtil.getInstance(context).getString(SharedPreferenceUtil.USERINFO), UserInfo.class);
		return userInfo;
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
	}

	/**
	 * @Description 打开apk
	 * @author Created by qinxianyuzou on 2014-12-26.
	 * @param context
	 * @param packagename
	 *            包名
	 */
	public static void openAPK(Context context, String packagename) {
		PackageManager packageManager = context.getPackageManager();
		Intent intent = new Intent();
		intent = packageManager.getLaunchIntentForPackage(packagename);
		context.startActivity(intent);
	}
    /** 遍历文件，路径结果 List */
    public static List<String> listFile = new ArrayList<String>();

    /**
     * 遍历文件夹搜索文件
     * @param Path 搜索的目录
     * @param Extension 扩展名
     * @param IsIterative 是否进入子文件夹
     */
    public static void getFiles(String Path, String Extension, boolean IsIterative){
        listFile.clear();
        File[] files = new File(Path).listFiles();
        if(files != null && files.length != 0){
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                if (f.isFile()) {
                    if (f.getPath().substring(f.getPath().length() - Extension.length()).equals(Extension)) //判断扩展名
                        listFile.add(f.getPath());
                    if (!IsIterative) break;
                    //忽略点文件（隐藏文件/文件夹）
                } else if (f.isDirectory() && f.getPath().indexOf("/.") == -1){
                    getFiles(f.getPath(), Extension, IsIterative);
                }
            }
        }
    }

    /**
     * 获取所有已下载的应用并返回集合
     * @return List<Download_APK_Install>
     */
    public static List<Download_APK_Install> getDownloadAppsEntity() {
        Context ctx;
        List<Download_APK_Install> apps = new ArrayList<>();
        getFiles(DOWNLOAD_APP_PATH, "apk", false);
        if (listFile.size() != 0) {
            ctx = KeyGuardApplication.getInstance().getApplicationContext();
            for (String path : listFile) {
                Download_APK_Install apk = new Download_APK_Install();
                apk.setAppIcon(getApkIcon(ctx, path));
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
     * 根据路径获取文件大小
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
     * @param size
     * @return 字符串size 后面带 m
     */
    public static String formatSizeM(double size) {
        DecimalFormat df = new DecimalFormat("#.00");
        return String.valueOf(df.format(size)) + "m";
    }
}
