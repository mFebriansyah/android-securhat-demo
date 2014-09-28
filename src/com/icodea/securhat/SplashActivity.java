package com.icodea.securhat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {
	Thread tSplash;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		//thread use to do something with delay time.
		tSplash = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					Intent iIcodea = new Intent(SplashActivity.this, IcodeaSplashActivity.class);
					startActivity(iIcodea);
					finish();
				}
			}
		});
		tSplash.start();
	}
}
