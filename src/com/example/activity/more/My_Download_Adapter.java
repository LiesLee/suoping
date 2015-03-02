package com.example.activity.more;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entity.Download_APK_Install;
import com.example.keyguard.R;
import com.example.util.PublicUtil;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by qinxianyuzou on 2014-12-25.
 */
public class My_Download_Adapter extends BaseAdapter {
	private String shop_type;
	private Context mContext;
	private LayoutInflater listContainer;
	/** 数据源 */
	private List<Download_APK_Install> listData = new ArrayList<Download_APK_Install>();
	/** 图片加载工具 */
	private BitmapUtils bitmapUtils;
	/** 图片加载回调 */
	private BitmapLoadCallBack<ImageView> bitmapLoadCallBack;

	public My_Download_Adapter(Context context, BitmapUtils bitmapUtils, String shop_type) {
		mContext = context;
		listContainer = LayoutInflater.from(mContext);
		this.shop_type = shop_type;
		this.bitmapUtils = bitmapUtils;
		bitmapLoadCallBack = new DefaultBitmapLoadCallBack<ImageView>() {

			@Override
			public void onLoadFailed(ImageView container, String uri, Drawable drawable) {
				// TODO Auto-generated method stub
				super.onLoadFailed(container, uri, drawable);
				container.setImageResource(R.drawable.item_icon);
			}

			@Override
			public void onLoadCompleted(ImageView container, String uri, Bitmap bitmap, BitmapDisplayConfig config,
					BitmapLoadFrom from) {
				// TODO Auto-generated method stub
				super.onLoadCompleted(container, uri, bitmap, config, from);
			}
		};
		// ViewUtils.inject((Activity) mContext);
	}

	public void setData(List<Download_APK_Install> data) {
		listData.clear();
		listData.addAll(data);
		notifyDataSetChanged();
	}

	public List<Download_APK_Install> getData() {
		return listData;
	}

	@Override
	public int getCount() {
		return listData.size();
		// return 15;
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// 实例化viewholader
		final HolderView listItemView;

		if (convertView == null) {
			listItemView = new HolderView();
			convertView = listContainer.inflate(R.layout.item_my_download, null);
			listItemView.download_app_icon = (ImageView) convertView.findViewById(R.id.download_app_icon);
			listItemView.download_app_name = (TextView) convertView.findViewById(R.id.download_app_name);
			listItemView.download_app_info = (TextView) convertView.findViewById(R.id.download_app_info);
			listItemView.tv_install_status = (TextView) convertView.findViewById(R.id.tv_install_status);
			listItemView.btn_install_app = (ImageView) convertView.findViewById(R.id.btn_install_app);
			listItemView.btn_choose_app = (ImageView) convertView.findViewById(R.id.btn_choose_app);
			// 设置标记
			convertView.setTag(listItemView);
		} else {
			listItemView = (HolderView) convertView.getTag();
		}

		if (listData != null && listData.size() != 0) {
			final Download_APK_Install apk = listData.get(position);
			listItemView.download_app_icon.setImageDrawable(PublicUtil.getApkIcon(mContext, apk.getAppPath()));
			listItemView.download_app_name.setText(apk.getAppName());
			// 下载管理
			if (shop_type.equals("2")) {
				listItemView.download_app_info.setText("安装");
				listItemView.tv_install_status.setVisibility(View.GONE);
				listItemView.btn_install_app.setVisibility(View.VISIBLE);
				listItemView.btn_choose_app.setVisibility(View.GONE);
				listItemView.btn_install_app.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (new File(apk.getAppPath()).exists()) {
							String packagename = PublicUtil.getPackageName(mContext, apk.getAppPath());
							if (PublicUtil.isApkInstalled(mContext, packagename)) {
								PublicUtil.openAPK(mContext, packagename);
							} else {
								PublicUtil.installAPK(mContext, apk.getAppPath());
							}
						} else {
							Toast.makeText(mContext, "安装包已被删除", Toast.LENGTH_SHORT).show();
						}

					}
				});
				// 安装包管理
			} else if (shop_type.equals("3")) {
				listItemView.download_app_info.setText(apk.getFileSize());
				listItemView.tv_install_status.setVisibility(View.VISIBLE);
				if (apk.isInstalled()) {
					listItemView.tv_install_status.setText("已安装");
				} else {
					listItemView.tv_install_status.setText("未安装");
				}

				listItemView.btn_choose_app.setVisibility(View.VISIBLE);
				listItemView.btn_choose_app.setImageResource(R.drawable.apk_choose_nomal);
				listItemView.btn_install_app.setVisibility(View.GONE);

				listItemView.btn_choose_app.setOnClickListener(new View.OnClickListener() {
					boolean isChoose = false;

					@Override
					public void onClick(View v) {
						isChoose = !isChoose;
						((ImageView) v).setImageResource(isChoose ? R.drawable.apk_choose_ative
								: R.drawable.apk_choose_nomal);
						listData.get(position).setChoose(isChoose);
					}
				});
			}
		}
		return convertView;
	}

	private class HolderView {
		private ImageView download_app_icon;
		private TextView download_app_name;
		private TextView download_app_info;
		private TextView tv_install_status;
		private ImageView btn_install_app;
		private ImageView btn_choose_app;
	}

}
