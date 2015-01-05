package com.example.activity.more;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.entity.more.Logistics_Entity;
import com.example.keyguard.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;

/**
 * @Description 地址列表
 * @author Created by qinxianyuzou on 2014-12-25.
 */
public class Adapter_AddressList extends BaseAdapter {
	private Activity activity;
	private LayoutInflater listContainer;
	/** 数据源 */
	private List<Logistics_Entity> listData = new ArrayList<Logistics_Entity>();
	/** 图片加载工具 */
	private BitmapUtils bitmapUtils;
	/** 图片加载回调 */
	private BitmapLoadCallBack<ImageView> bitmapLoadCallBack;
	/** 退出标记 */
	private long logout;

	public Adapter_AddressList(Activity context, BitmapUtils bitmapUtils) {
		activity = context;
		listContainer = LayoutInflater.from(activity);
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

	public void setData(List<Logistics_Entity> data) {
		listData = data;
		notifyDataSetChanged();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// 实例化viewholader
		final HolderView listItemView;

		if (convertView == null) {
			listItemView = new HolderView();
			//
			convertView = listContainer.inflate(R.layout.item_myinfo, null);
			listItemView.ll_item_myifno_btn = (LinearLayout) convertView.findViewById(R.id.ll_item_myifno_btn);
			listItemView.tv_item_myifno_name = (TextView) convertView.findViewById(R.id.tv_item_myifno_name);
			listItemView.tv_item_myifno_value = (TextView) convertView.findViewById(R.id.tv_item_myifno_value);
			//
			// 设置标记
			convertView.setTag(listItemView);
		} else {
			listItemView = (HolderView) convertView.getTag();
		}
		listItemView.tv_item_myifno_name.setText(listData.get(position).getTo_where());
		listItemView.tv_item_myifno_value.setText(listData.get(position).getTo_who());
		return convertView;
	}

	private class HolderView {
		private LinearLayout ll_item_myifno_btn;
		private TextView tv_item_myifno_name;
		private TextView tv_item_myifno_value;
	}

}
