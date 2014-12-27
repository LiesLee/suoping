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
		/*
		 * 	START_STICKY锛氬鏋渟ervice杩涚▼琚玨ill鎺夛紝淇濈暀service鐨勭姸鎬佷负寮�鐘舵�锛屼絾涓嶄繚鐣欓�閫佺殑intent瀵硅薄銆傞殢鍚庣郴缁熶細灏濊瘯閲嶆柊鍒涘缓service锛�
				鐢变簬鏈嶅姟鐘舵�涓哄紑濮嬬姸鎬侊紝鎵�互鍒涘缓鏈嶅姟鍚庝竴瀹氫細璋冪敤onStartCommand(Intent,int,int)鏂规硶銆傚鏋滃湪姝ゆ湡闂存病鏈変换浣曞惎鍔ㄥ懡浠よ浼犻�鍒皊ervice锛岄偅涔堝弬鏁癐ntent灏嗕负null銆�
			START_NOT_STICKY锛氣�闈炵矘鎬х殑鈥濄�浣跨敤杩欎釜杩斿洖鍊兼椂锛屽鏋滃湪鎵ц�?宱nStartCommand鍚庯紝鏈嶅姟琚紓甯竗ill鎺夛紝绯荤粺涓嶄細鑷姩閲嶅惎璇ユ湇鍔°�
			START_REDELIVER_INTENT锛氶噸浼營ntent銆備娇鐢ㄨ繖涓繑鍥炲�鏃讹紝濡傛灉鍦ㄦ墽琛屽畬onStartCommand鍚庯紝鏈嶅姟琚紓甯竗ill鎺夛紝绯荤粺浼氳嚜鍔ㄩ噸鍚鏈嶅姟锛屽苟灏咺ntent鐨勫�浼犲叆銆�
			START_STICKY_COMPATIBILITY锛歋TART_STICKY鐨勫吋�?圭増鏈紝浣嗕笉淇濊瘉鏈嶅姟琚玨ill鍚庝竴�?氳兘閲嶅惎銆�?		 */
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
