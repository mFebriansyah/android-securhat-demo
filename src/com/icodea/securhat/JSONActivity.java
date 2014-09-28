package com.icodea.securhat;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class JSONActivity extends ListActivity {
	private static String link_url = "http://10.0.2.2/securhat/json/module/member/member.php";
	private static final String ARRAY_MEMBER = "data";
	private static final String ARRAY_ID = "id_fms_sm_member";
	private static final String ARRAY_NAMA = "username";
	JSONArray member = null;
	ArrayList<HashMap<String, String>> daftar_member = new ArrayList<HashMap<String, String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.utama);
		new AmbilDataJSON().execute();
	}
	class AmbilDataJSON extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(JSONActivity.this);
            pDialog.setMessage("Loading Data Dulu...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		protected String doInBackground(String... args) {
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.AmbilJsonUrl(link_url);
			try {
				member = json.getJSONArray(ARRAY_MEMBER);
				for(int i = 0; i < member.length(); i++){
					JSONObject ar = member.getJSONObject(i);
					String id = ar.getString(ARRAY_ID);
					String nama = ar.getString(ARRAY_NAMA);
					HashMap<String, String> map = new HashMap<String, String>();
					map.put(ARRAY_ID, id);
					map.put(ARRAY_NAMA, nama);
					daftar_member.add(map);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		protected void onPostExecute(String dataJSON) {
			pDialog.dismiss();
	    	runOnUiThread(new Runnable() {
	            public void run() {
	            	ListAdapter adapter = new SimpleAdapter(
        				JSONActivity.this, daftar_member,
        				R.layout.list_item, new String[] {ARRAY_ID, ARRAY_NAMA}, new int[] {R.id.id, R.id.nama}
    				);
	                setListAdapter(adapter);
	            }
	        });
		}
	}
}

