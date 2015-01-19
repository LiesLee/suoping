package com.example.keyguard;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;

public class LockService extends Service {

	private static String TAG = "ZdLockService";
	private Intent zdLockIntent = null ;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onCreate(){
		super.onCreate();
		
		
		zdLockIntent = new Intent(LockService.this , LockActivity.class);
		//zdLockIntent = new Intent(ZdLockService.this , MainActivity.class);
//		zdLockIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
		zdLockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		/**/
		IntentFilter mScreenOnFilter = new IntentFilter("android.intent.action.SCREEN_ON");
		LockService.this.registerReceiver(mScreenOnReceiver, mScreenOnFilter);
		
		/**/
		IntentFilter mScreenOffFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
		LockService.this.registerReceiver(mScreenOffReceiver, mScreenOffFilter);
		
		
	    //final IntentFilter filter = new IntentFilter();
	    //filter.addAction(Intent.ACTION_SCREEN_OFF);
	    //registerReceiver(mReceiver, filter);
	       
	}
	
	/*
	  private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
	        @Override
	        public void onReceive(Context context, Intent intent) {
	            final String action = intent.getAction();
	            if (Intent.ACTION_SCREEN_OFF.equals(action)) {
	                Log.d("yzy","screen_off...");
	                startActivity(zdLockIntent);
	              
	            } 
	        }
	    };
*/
	@SuppressLint("Override")
	public int onStartCommand(Intent intent , int flags , int startId){
		
		return Service.START_REDELIVER_INTENT;

	}
	
	public void onDestroy(){
		super.onDestroy();
		LockService.this.unregisterReceiver(mScreenOnReceiver);
		LockService.this.unregisterReceiver(mScreenOffReceiver);
		startService(new Intent(LockService.this, LockService.class));
	}
	

	
	private BroadcastReceiver mScreenOnReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context , Intent intent) {
			
            Log.i(TAG, intent.getAction());

			if(intent.getAction().equals("android.intent.action.SCREEN_ON")){
				Log.i(TAG, "----------------- android.intent.action.SCREEN_ON------");

			}
		}
		
	};
	
	private BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context , Intent intent) {
			String action = intent.getAction() ;
			
		    Log.i(TAG, intent.toString());
		    
			if(action.equals("android.intent.action.SCREEN_OFF")
					|| action.equals("android.intent.action.SCREEN_ON") ){
				/*
				PowerManager pm = (PowerManager)getSystemService(POWER_SERVICE);  
				//PowerManager.ACQUIRE_CAUSES_WAKEUP| PowerManager.SCREEN_DIM_WAKE_LOCK
				 WakeLock mWakelock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK|PowerManager.ON_AFTER_RELEASE, "SimpleTimer");  
				 mWakelock.acquire();  
				 //mWakelock.release();
				*/
				startActivity(zdLockIntent);
				
			}
		}
		
	};
	
	
}
