package com.example.activity.more;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.keyguard.R;

import android.app.Activity;
import android.os.Bundle;

import org.json.JSONObject;

public class Activity_more extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_more);
	}

    @Override
    public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {

    }

    @Override
    public void onHttpError(long flag, VolleyError error) {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public String setTag() {
        return null;
    }
}
