package com.example.keyguard;





import com.example.ui.UINavigationView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;



@SuppressLint("SetJavaScriptEnabled")
public class DownLoadWebActivity extends Activity {
	
	 public static final int LL_NAV = 1111111; 
	 public static final int RL_DOWN = 1111112; 

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		
		//ExitApplication.getInstance().addActivity(this);
		RelativeLayout mRelativeLayout = new RelativeLayout(this);  
	    setContentView(mRelativeLayout); 	  
	    UINavigationView nav = new UINavigationView(this);
	    nav.setStrTitle("搴旂敤璇︽儏");
	    nav.initContent(mRelativeLayout.getContext());
	    nav.setId(LL_NAV);
	    mRelativeLayout.addView(nav);
	    nav.getBtn_left().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(DownLoadWebActivity.this, "杩斿洖涓婚〉!",
					     Toast.LENGTH_SHORT).show();
		        Intent intent = new Intent(DownLoadWebActivity.this, MainActivity.class); 
		        startActivity(intent); 
			}  
        });  
	    

        RelativeLayout mDownloadLayout = new RelativeLayout(mRelativeLayout.getContext());      
        mDownloadLayout.setBackgroundColor(Color.rgb(245, 245, 245));
        mDownloadLayout.setId(RL_DOWN);
        Button mBtnDownloadNow = null; 
        mBtnDownloadNow = new Button(mDownloadLayout.getContext());  
        mBtnDownloadNow.setBackgroundColor(Color.TRANSPARENT);
        mBtnDownloadNow.setText("绔嬪嵆涓嬭浇");  
        mBtnDownloadNow.setTextSize(25);
        mBtnDownloadNow.setTextColor(Color.RED);
        mBtnDownloadNow.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(DownLoadWebActivity.this, "绔嬪嵆涓嬭浇!",
					     Toast.LENGTH_SHORT).show();
			}  
        });  
	    RelativeLayout.LayoutParams Download_rl2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);  
	    mDownloadLayout.addView(mBtnDownloadNow ,Download_rl2);       
	    RelativeLayout.LayoutParams DownLoad_rl1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);  
	    DownLoad_rl1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);  
	    DownLoad_rl1.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);  
        mRelativeLayout.addView(mDownloadLayout ,DownLoad_rl1);  
        
        
	    WebView mWebView = new WebView(mRelativeLayout.getContext());	    
	    RelativeLayout.LayoutParams WebView_rl1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);  
	    WebView_rl1.addRule(RelativeLayout.BELOW,LL_NAV); 
	    WebView_rl1.addRule(RelativeLayout.ABOVE, RL_DOWN);
        mRelativeLayout.addView(mWebView ,WebView_rl1); 
      //璁剧疆WebView灞炴�锛岃兘澶熸墽琛孞avascript鑴氭湰  
        mWebView.getSettings().setJavaScriptEnabled(true); 
        mWebView.loadUrl("http://www.jd.com/");
        mWebView.setWebViewClient(new WebViewClient(){
      	  public boolean shouldOverrideUrlLoading(WebView view, String url) {  //閲嶅啓姝ゆ柟娉曡〃鏄庣偣鍑荤綉椤甸噷闈㈢殑閾炬帴杩樻槸鍦ㄥ綋鍓嶇殑webview閲岃烦杞紝涓嶈烦鍒版祻瑙堝櫒閭ｈ竟
      	       view.loadUrl(url);
      	       return true;
      	  }
        });

	}
}
