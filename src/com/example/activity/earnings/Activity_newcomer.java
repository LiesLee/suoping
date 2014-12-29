package com.example.activity.earnings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.activity.common.KeyGuardActivityManager;
import com.example.keyguard.R;

public class Activity_newcomer extends Activity {

    public static void luanch(Activity activity) {
        Intent intent = new Intent(activity, Activity_newcomer.class);
        KeyGuardActivityManager.getInstance().goTo(activity, intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcomer);
    }
}
