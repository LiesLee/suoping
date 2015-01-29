package com.example.activity.earnings;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entity.SignIn_Entity;
import com.example.keyguard.R;
import com.example.util.PublicUtil;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;

/**
 * @author Created by qinxianyuzou on 2014-12-25.
 */
public class My_Not_Active_Adapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater listContainer;

	/** 数据源 */
	private List<SignIn_Entity> listData = new ArrayList<SignIn_Entity>();
	/** 图片加载工具 */
	private BitmapUtils bitmapUtils;
	/** 图片加载回调 */
	private BitmapLoadCallBack<ImageView> bitmapLoadCallBack;

	public My_Not_Active_Adapter(Context context, BitmapUtils bitmapUtils) {
		mContext = context;
		listContainer = LayoutInflater.from(mContext);
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

	public void setData(List<SignIn_Entity> data) {
		listData = data;
		notifyDataSetChanged();
	}

	public List<SignIn_Entity> getData() {
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
			convertView = listContainer.inflate(R.layout.item_not_active, null);
			listItemView.download_app_icon = (ImageView) convertView.findViewById(R.id.download_app_icon);
			listItemView.download_app_name = (TextView) convertView.findViewById(R.id.download_app_name);
			listItemView.download_app_info = (TextView) convertView.findViewById(R.id.download_app_info);
			listItemView.tv_install_status = (TextView) convertView.findViewById(R.id.tv_install_status);
			convertView.setTag(listItemView);
		} else {
			listItemView = (HolderView) convertView.getTag();
		}

		if (listData != null && listData.size() != 0) {
			final SignIn_Entity apk = listData.get(position);
			bitmapUtils.display(listItemView.download_app_icon, apk.getIcon());
			listItemView.download_app_name.setText(apk.getApp_name());
			listItemView.download_app_info.setText(apk.getDesc());
			listItemView.tv_install_status.setVisibility(View.VISIBLE);
			if (PublicUtil.isApkInstalled(mContext, apk.getProcess_name())) {
				listItemView.tv_install_status.setText("已安装" + "，使用" + apk.getUse_time() + "秒签到");
			} else {
				listItemView.tv_install_status.setText("未安装");
			}
			// convertView.setOnClickListener(new View.OnClickListener() {
			// @Override
			// public void onClick(View v) {
			// // LogUtil.i("=====包名=====",
			// // PublicUtil.getPackageName(mContext, apk.getAppPath()));
			// if (PublicUtil.isApkInstalled(mContext, apk.getPacket_name())) {
			// PublicUtil.APPTiming(mContext, apk.getPacket_name());
			// } else {
			// // PublicUtil.openAPK(mContext,
			// // PublicUtil.getPackageName(mContext,
			// // apk.getAppPath()));
			// PublicUtil.downloadAPP((Activity) mContext,
			// apk.getPacket_name());
			// }
			// }
			// });
		}
		return convertView;
	}

	private class HolderView {
		private ImageView download_app_icon;
		private TextView download_app_name;
		private TextView download_app_info;
		private TextView tv_install_status;
	}

}
