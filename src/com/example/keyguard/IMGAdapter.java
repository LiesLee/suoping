package com.example.keyguard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class IMGAdapter extends SimpleAdapter {

	Context context = null;

	private int lineHeight;
	private int lineWidth;

	public IMGAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		this.context = context;
	}

	public void setlineHeight(int lineHeight) {
		Log.d("hhz", "setlineHeight..." + lineHeight);
		this.lineHeight = lineHeight;
	}

	public void setlineWidth(int lineWidth) {
		Log.d("hhz", "setlineWidth..." + lineWidth);
		this.lineWidth = lineWidth;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// Inflate the note_item layout manually, and treat it as the item view
		// 重新填充note_item部局，并把它作为项的view返回
		convertView = LayoutInflater.from(context).inflate(R.layout.scroll_item, null);
		HashMap<String, Object> theMap = (HashMap<String, Object>) getItem(position);
		ImageView image = (ImageView) convertView.findViewById(R.id.id_title);
		image.setImageResource((Integer) theMap.get("Image"));
		// Calculate the item width by the column number to let total width fill
		// the screen width
		// 根据列数计算项目宽度，以使总宽度尽量填充屏幕
		// int itemWidth = (int)(getResources().getDisplayMetrics().widthPixels
		// - colnum * 10) / colnum;
		// Calculate the height by your scale rate, I just use itemWidth here
		// 下面根据比例计算您的item的高度，此处只是使用itemWidth
		// int itemHeight = itemWidth;

		/*
		 * AbsListView.LayoutParams param = new AbsListView.LayoutParams(
		 * screenWidth-image_slide_rightMargin-image_slide_leftMargin,
		 * (screenHeight-( image_slide_bottomMargin-image_slide_width)*2)*1);
		 */
		if (position % 2 == 0 && position >= 0) {
			convertView.setBackgroundColor(Color.YELLOW);
		} else {
			convertView.setBackgroundColor(Color.GREEN);
		}
		AbsListView.LayoutParams param = new AbsListView.LayoutParams(lineWidth, lineHeight);
		convertView.setLayoutParams(param);

		return convertView;
	}

}
