package com.icodea.securhat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class IcodeaSplashActivity extends Activity {
	Thread tSplash;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icodea_splash);
		//thread use to do something with delay time.
		tSplash = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					Intent iMain = new Intent(IcodeaSplashActivity.this, MainActivity.class);
					startActivity(iMain);
					finish();
				}
			}
		});
		tSplash.start();
	}
}
