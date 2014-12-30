package com.example.activity.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.keyguard.R;

/**
 * @Description 性别选择框
 * @author Created by qinxianyuzou on 2014-12-30.
 */
public class DialogSex extends Dialog implements android.view.View.OnClickListener {
	private Context mContext;

	public DialogSex(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public DialogSex(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_sex);
		findViewById(R.id.ll_sex_man).setOnClickListener(this);
		findViewById(R.id.ll_sex_woman).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ll_sex_man:
			dismiss();
			break;
		case R.id.ll_sex_woman:
			dismiss();
			break;

		default:
			break;
		}
	}

}
