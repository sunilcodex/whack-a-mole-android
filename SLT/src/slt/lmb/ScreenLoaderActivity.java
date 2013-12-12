package slt.lmb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ScreenLoaderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_loader);
		

		Thread screenTimer=new Thread(){
			public void run(){
				try{
					int screenTimer=0;
					while(screenTimer<4000){
						sleep(100);
						screenTimer=screenTimer+100;
					}
					startActivity(new Intent("slt.lmb.CLEARSCEEN"));
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				finally{
					finish();
				}
			}
		};
		screenTimer.start();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

}
