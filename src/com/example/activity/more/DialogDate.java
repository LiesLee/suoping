package com.example.activity.more;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.keyguard.R;

/**
 * @Description 日期输入框
 * @author Created by qinxianyuzou on 2014-12-30.
 */
public class DialogDate extends Dialog implements android.view.View.OnClickListener {
	private Context mContext;
	private EditText et_dialog_date_year;
	private EditText et_dialog_date_month;

	public DialogDate(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public DialogDate(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_date);
		findViewById(R.id.but__dialog_date_submit).setOnClickListener(this);
		findViewById(R.id.but__dialog_date_cancel).setOnClickListener(this);
		et_dialog_date_year=(EditText) findViewById(R.id.et_dialog_date_year);
		et_dialog_date_month=(EditText) findViewById(R.id.et_dialog_date_month);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.but__dialog_date_submit:
			et_dialog_date_year.getText().toString();
			et_dialog_date_month.getText().toString();
			dismiss();
			break;
		case R.id.but__dialog_date_cancel:
			dismiss();
			break;

		default:
			break;
		}
	}

}
