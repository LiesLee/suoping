package com.example.keyguard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.activity.main.MainActivity;
import com.example.ui.UINavigationView;

@SuppressLint("SetJavaScriptEnabled")
public class DownLoadWebActivity extends Activity {

	public static final int LL_NAV = 1111111;
	public static final int RL_DOWN = 1111112;

	public static DownLoadWebActivity instance = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		instance = this;

		// ExitApplication.getInstance().addActivity(this);
		RelativeLayout mRelativeLayout = new RelativeLayout(this);
		setContentView(mRelativeLayout);
		UINavigationView nav = new UINavigationView(this);
		nav.setStrTitle("应用详情");
		nav.initContent(mRelativeLayout.getContext());
		nav.setId(LL_NAV);
		mRelativeLayout.addView(nav);
		nav.getBtn_left().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(DownLoadWebActivity.this, "鏉╂柨娲栨稉濠氥�!", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(DownLoadWebActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		RelativeLayout mDownloadLayout = new RelativeLayout(mRelativeLayout.getContext());
		mDownloadLayout.setBackgroundColor(Color.rgb(245, 245, 245));
		mDownloadLayout.setId(RL_DOWN);
		Button mBtnDownloadNow = null;
		mBtnDownloadNow = new Button(mDownloadLayout.getContext());
		mBtnDownloadNow.setBackgroundColor(Color.TRANSPARENT);
		mBtnDownloadNow.setText("缁斿宓嗘稉瀣祰");
		mBtnDownloadNow.setTextSize(25);
		mBtnDownloadNow.setTextColor(Color.RED);
		mBtnDownloadNow.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(DownLoadWebActivity.this, "缁斿宓嗘稉瀣祰!", Toast.LENGTH_SHORT).show();
			}
		});
		RelativeLayout.LayoutParams Download_rl2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		mDownloadLayout.addView(mBtnDownloadNow, Download_rl2);
		RelativeLayout.LayoutParams DownLoad_rl1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		DownLoad_rl1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		DownLoad_rl1.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		mRelativeLayout.addView(mDownloadLayout, DownLoad_rl1);

		WebView mWebView = new WebView(mRelativeLayout.getContext());
		RelativeLayout.LayoutParams WebView_rl1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT);
		WebView_rl1.addRule(RelativeLayout.BELOW, LL_NAV);
		WebView_rl1.addRule(RelativeLayout.ABOVE, RL_DOWN);
		mRelativeLayout.addView(mWebView, WebView_rl1);
		// 鐠佸墽鐤哤ebView鐏炵偞锟介敍宀冨厴婢剁喐澧界悰瀛瀉vascript閼存碍婀�
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl("http://www.jd.com/");
		mWebView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) { // 闁插秴鍟撳銈嗘煙濞夋洝銆冮弰搴ｅ仯閸戣崵缍夋い鐢稿櫡闂堛垻娈戦柧鐐复鏉╂ɑ妲搁崷銊ョ秼閸撳秶娈憌ebview闁插矁鐑︽潪顒婄礉娑撳秷鐑﹂崚鐗堢セ鐟欏牆娅掗柇锝堢珶
				view.loadUrl(url);
				return true;
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();

		Log.i("hhz", "onResume");
	}

	@Override
	protected void onPause() {

		Log.i("hhz", "onPause");
		if (MainActivity.instance != null) {
			MainActivity.instance.finish();
		}
		if (DownLoadWebActivity.instance != null) {
			DownLoadWebActivity.instance.finish();
		}
		if (LockActivity.instance != null) {
			LockActivity.instance.finish();
		}
		super.onPause();

	}
}
