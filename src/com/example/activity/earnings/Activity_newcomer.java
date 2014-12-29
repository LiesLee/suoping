package com.example.activity.earnings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.keyguard.R;

import org.json.JSONObject;

public class Activity_newcomer extends BaseActivity {

    public static void luanch(Activity activity) {
        Intent intent = new Intent(activity, Activity_newcomer.class);
        KeyGuardActivityManager.getInstance().goTo(activity, intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcomer);
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {

    }

    @Override
    public void onHttpError(long flag, VolleyError error) {

    }

    @Override
    public String setTag() {
        return this.getClass().getSimpleName();
    }
}
