package com.example.activity.common;

import java.io.File;
import java.lang.ref.WeakReference;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.download.DownloadInfo;
import com.example.download.DownloadManager;
import com.example.download.DownloadService;
import com.example.keyguard.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class DownDemo extends BaseActivity {

	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 返回按钮 */
	@ViewInject(R.id.rl_public_back)
	private RelativeLayout rl_public_back;
	/** 列表 */
	@ViewInject(R.id.lv_shop_body)
	private ListView lv_shop_body;
	/** 标题 */
	private static String mTitle = "";

	/**
	 * @Description 不设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 */
	public static void luanch(Activity activity) {
		Intent intent = new Intent(activity, DownDemo.class);
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
		Intent intent = new Intent(activity, DownDemo.class);
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
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

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

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub

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

	private DownloadManager downloadManager;
	private DownloadInfo downloadInfo;

	private void name() {
		downloadManager = DownloadService.getDownloadManager(activity);
		downloadInfo = downloadManager.getDownloadInfo(0);
		DownloadItemViewHolder holder = new DownloadItemViewHolder(downloadInfo);
		holder.refresh();

		HttpHandler<File> handler = downloadInfo.getHandler();
		if (handler != null) {
			RequestCallBack callBack = handler.getRequestCallBack();
			if (callBack instanceof DownloadManager.ManagerCallBack) {
				DownloadManager.ManagerCallBack managerCallBack = (DownloadManager.ManagerCallBack) callBack;
				if (managerCallBack.getBaseCallBack() == null) {
					managerCallBack.setBaseCallBack(new DownloadRequestCallBack());
				}
			}
			callBack.setUserTag(new WeakReference<DownloadItemViewHolder>(holder));
		}
	}

	public class DownloadItemViewHolder {
		@ViewInject(R.id.download_pb)
		ProgressBar progressBar;
		@ViewInject(R.id.btn_download_stop)
		Button btn_download_stop;
		@ViewInject(R.id.btn_download_cancle)
		Button btn_download_cancle;
		private DownloadInfo downloadInfo;

		public DownloadItemViewHolder(DownloadInfo downloadInfo) {
			this.downloadInfo = downloadInfo;
		}

		@OnClick(R.id.btn_download_stop)
		public void stop(View view) {
			HttpHandler.State state = downloadInfo.getState();
			switch (state) {
			case WAITING:
			case STARTED:
			case LOADING:
				try {
					downloadManager.stopDownload(downloadInfo);
				} catch (DbException e) {
					LogUtils.e(e.getMessage(), e);
				}
				break;
			case CANCELLED:
			case FAILURE:
				try {
					downloadManager.resumeDownload(downloadInfo, new DownloadRequestCallBack());
				} catch (DbException e) {
					LogUtils.e(e.getMessage(), e);
				}
				break;
			default:
				break;
			}
		}

		@OnClick(R.id.btn_download_cancle)
		public void remove(View view) {
			try {
				downloadManager.removeDownload(downloadInfo);
			} catch (DbException e) {
				LogUtils.e(e.getMessage(), e);
			}
		}

		public void update(DownloadInfo downloadInfo) {
			this.downloadInfo = downloadInfo;
			refresh();
		}

		public void refresh() {
			btn_download_stop.setText(downloadInfo.getState().toString());
			if (downloadInfo.getFileLength() > 0) {
				progressBar.setProgress((int) (downloadInfo.getProgress() * 100 / downloadInfo.getFileLength()));
			} else {
				progressBar.setProgress(0);
			}

			btn_download_stop.setVisibility(View.VISIBLE);
			btn_download_stop.setText("暂停");
			HttpHandler.State state = downloadInfo.getState();
			switch (state) {
			case WAITING:
				btn_download_stop.setText("暂停");
				break;
			case STARTED:
				btn_download_stop.setText("暂停");
				break;
			case LOADING:
				btn_download_stop.setText("暂停");
				break;
			case CANCELLED:
				btn_download_stop.setText("回复");
				break;
			case SUCCESS:
				btn_download_stop.setVisibility(View.INVISIBLE);
				break;
			case FAILURE:
				btn_download_stop.setText("重试");
				break;
			default:
				break;
			}
		}
	}

	private class DownloadRequestCallBack extends RequestCallBack<File> {

		@SuppressWarnings("unchecked")
		private void refreshListItem() {
			if (userTag == null)
				return;
			WeakReference<DownloadItemViewHolder> tag = (WeakReference<DownloadItemViewHolder>) userTag;
			DownloadItemViewHolder holder = tag.get();
			holder.refresh();
		}

		@Override
		public void onStart() {
			refreshListItem();
		}

		@Override
		public void onLoading(long total, long current, boolean isUploading) {
			refreshListItem();
		}

		@Override
		public void onSuccess(ResponseInfo<File> responseInfo) {
			refreshListItem();
		}

		@Override
		public void onFailure(HttpException error, String msg) {
			refreshListItem();
		}

		@Override
		public void onCancelled() {
			refreshListItem();
		}
	}
}
