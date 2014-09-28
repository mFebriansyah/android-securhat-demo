package com.icodea.securhat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.graphics.Bitmap;

public class MainActivity extends Activity {
	WebView webview;
	WebSettings websettings;
	ProgressBar progress;
	boolean doubleBackToExitPressedOnce;
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		progress=(ProgressBar)findViewById(R.id.pbLoader);
		webview=(WebView)findViewById(R.id.webview);
		websettings=webview.getSettings();
		websettings.setJavaScriptEnabled(true);
		webview.setWebViewClient(new WebViewClient(){
			@Override
	    	public void onPageStarted(WebView webview, String s, Bitmap bitmap) { 
	    		super.onPageStarted(webview, s, bitmap);
    			progress.setVisibility(ProgressBar.VISIBLE); 
    			webview.setVisibility(ProgressBar.INVISIBLE); 
	    	}
			@Override
	    	public void onPageFinished(WebView webview, String s) {
	    		super.onPageFinished(webview, s);
    			progress.setVisibility(ProgressBar.INVISIBLE); 
    			webview.setVisibility(ProgressBar.VISIBLE); 
	    	} 
			@Override
	        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				webview.loadUrl("file:///android_asset/my-error-page.html");
	        }
		});
		webview.loadUrl("http://www.securhat.com/index.php");
    }
    
    //Back Button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode){
            	case KeyEvent.KEYCODE_BACK:
	                if(webview.canGoBack()){
	                	webview.goBack();
	                }else{
	                    if (doubleBackToExitPressedOnce) {
	                        this.moveTaskToBack(true);
	                        return true;
	                    }
		        	    this.doubleBackToExitPressedOnce = true;
		        	    Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
		        	    new Handler().postDelayed(new Runnable() {
		        	        @Override
		        	        public void run() {
		        	            doubleBackToExitPressedOnce=false;                       
		        	        }
		        	    }, 2000);
	                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    
    //Option Menu
    private int group1Id = 1;
    int homeId = Menu.FIRST;
	int profileId = Menu.FIRST +1;
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(group1Id, homeId, homeId, "home");
		menu.add(group1Id, profileId, profileId, "about");
    return super.onCreateOptionsMenu(menu); 
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   switch (item.getItemId()) {
	   		case 1:
	   			Toast msg = Toast.makeText(MainActivity.this, "halooo", Toast.LENGTH_LONG);
	   			msg.show();
				return true;
   			case 2:
				//code here
   				Intent iAbout = new Intent(MainActivity.this, AboutActivity.class);
				startActivity(iAbout);
				return true;
   			default:
				break;
	   }
	   return super.onOptionsItemSelected(item);
	}
}