package com.example.keyguard;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.example.entity.LockADList_Entity;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;

/**
 * @Description 我的账号的adapter
 * @author Created by qinxianyuzou on 2014-12-25.
 */
public class LockAD_PagerAdapter extends PagerAdapter {
	private Activity activity;
	private LayoutInflater listContainer;
	/** 数据源 */
	private List<LockADList_Entity> listData = new ArrayList<>();
	/** 图片加载工具 */
	private BitmapUtils bitmapUtils;
	/** 图片加载回调 */
	private BitmapLoadCallBack<ImageView> bitmapLoadCallBack;
	private AQuery aQuery;

	// /** 退出标记 */
	// private long logout;

	public LockAD_PagerAdapter(Activity context, BitmapUtils bitmapUtils) {
		activity = context;
		listContainer = LayoutInflater.from(activity);
		this.bitmapUtils = bitmapUtils;
		aQuery = new AQuery(activity);
		bitmapLoadCallBack = new DefaultBitmapLoadCallBack<ImageView>() {

			@Override
			public void onLoadFailed(ImageView container, String uri, Drawable drawable) {
				// TODO Auto-generated method stub
				super.onLoadFailed(container, uri, drawable);
				container.setImageResource(R.drawable.launch_bg);
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

	public void setData(List<LockADList_Entity> data) {
		listData = data;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return listData.size();
		// return 15;
	}

	// @Override
	// public Object getItem(int position) {
	// return listData.get(position);
	// }
	//
	// @Override
	// public long getItemId(int position) {
	// return position;
	// }
	//
	// @Override
	// public View getView(int position, View convertView, ViewGroup parent) {
	// // 实例化viewholader
	// final HolderView listItemView;
	//
	// if (convertView == null) {
	// listItemView = new HolderView();
	// //
	// convertView = listContainer.inflate(R.layout.activity_lock_img, null);
	// listItemView.iv_lock_img = (ImageView)
	// convertView.findViewById(R.id.iv_lock_img);
	// //
	// // 设置标记
	// convertView.setTag(listItemView);
	// } else {
	// listItemView = (HolderView) convertView.getTag();
	// }
	// // bitmapUtils.display(listItemView.iv_lock_img,
	// // listData.get(position).getHp_url(), bitmapLoadCallBack);
	// aQuery.id(listItemView.iv_lock_img).image(listData.get(position).getHp_url());
	// return convertView;
	// }

	private class HolderView {
		private ImageView iv_lock_img;
	}

	@Override
	public boolean isViewFromObject(View convertView, Object arg1) {
		// TODO Auto-generated method stub
		final HolderView listItemView;
		//
		if (convertView == null) {
			listItemView = new HolderView();
			//
			convertView = listContainer.inflate(R.layout.activity_lock_img, null);
			listItemView.iv_lock_img = (ImageView) convertView.findViewById(R.id.iv_lock_img);
			//
			// 设置标记
			convertView.setTag(listItemView);
		} else {
			listItemView = (HolderView) convertView.getTag();
		}
		// bitmapUtils.display(listItemView.iv_lock_img,
		// listData.get(position).getHp_url(), bitmapLoadCallBack);
		// aQuery.id(listItemView.iv_lock_img).image(listData.get(position).getHp_url());
		return false;
	}

}
