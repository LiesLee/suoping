package com.example.activity.shop;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entity.shop.EXProduct_Entity;
import com.example.keyguard.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;

/**
 * @Description 兑换的adapter
 * @author Created by qinxianyuzou on 2014-12-25.
 */
public class Shop_Adapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater listContainer;
	/** 数据源 */
	private List<EXProduct_Entity> listData = new ArrayList<EXProduct_Entity>();
	/** 图片加载工具 */
	private BitmapUtils bitmapUtils;
	/** 图片加载回调 */
	private BitmapLoadCallBack<ImageView> bitmapLoadCallBack;

	public Shop_Adapter(Context context, BitmapUtils bitmapUtils) {
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

	public void setData(List<EXProduct_Entity> data) {
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
			convertView = listContainer.inflate(R.layout.item_shop, null);
			listItemView.iv_item_shop_icon = (ImageView) convertView.findViewById(R.id.iv_item_shop_icon);
			listItemView.tv_item_shop_name = (TextView) convertView.findViewById(R.id.tv_item_shop_name);
			listItemView.tv_item_shop_money = (TextView) convertView.findViewById(R.id.tv_item_shop_money);
			//
			// 设置标记
			convertView.setTag(listItemView);
		} else {
			listItemView = (HolderView) convertView.getTag();
		}
		listItemView.tv_item_shop_name.setText(listData.get(position).getTitle());
		listItemView.tv_item_shop_money.setText(listData.get(position).getNeed_jifen());
		bitmapUtils.display(listItemView.iv_item_shop_icon, listData.get(position).getHp_url(), bitmapLoadCallBack);
		return convertView;
	}

	private class HolderView {
		private ImageView iv_item_shop_icon;
		private TextView tv_item_shop_name;
		private TextView tv_item_shop_money;
	}

}
