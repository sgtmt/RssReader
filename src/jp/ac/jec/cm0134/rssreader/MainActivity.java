package jp.ac.jec.cm0134.rssreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
	RowModelAdapter adapter;
	class RowModelAdapter extends ArrayAdapter<RssItem> {
		public RowModelAdapter(Context context) {
			super(context, R.layout.row_item);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final RssItem item = getItem(position);
			if ( convertView == null ) {
				LayoutInflater inflater = getLayoutInflater();
				convertView = inflater.inflate(R.layout.row_item, null);
			}
			if ( item != null ) {
				TextView txt1 = (TextView)convertView.findViewById(R.id.txtTitle);
				if ( txt1 != null ) {
					txt1.setText(item.getTitle());
				}
				TextView txt2 = (TextView)convertView.findViewById(R.id.txtUri);
				if ( txt2 != null ) {
					txt2.setText(item.getUrl());
				}
			}
			return convertView;
		}

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Uri.Builder uriBuilder = new Uri.Builder();
		// 以下のURLにアクセスしている
		// http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=10&q=http://rss.dailynews.yahoo.co.jp/fc/sports/rss.xml
		uriBuilder.scheme("http");
		uriBuilder.authority("ajax.googleapis.com");
		uriBuilder.path("/ajax/services/feed/load");
		uriBuilder.appendQueryParameter("v", "1.0");
		uriBuilder.appendQueryParameter("num", "30");
		// ここがRSSのURL
		uriBuilder.appendQueryParameter("q", "http://rss.dailynews.yahoo.co.jp/fc/sports/rss.xml");
		AsyncHttpReqest req = new AsyncHttpReqest(MainActivity.this);
		req.execute(uriBuilder);
		
//		adapter = new RowModelAdapter(this);
//		ArrayList<RssItem> ary = JsonHelper.parseJson(getData());
//		for ( RssItem tmp: ary ) {
//			adapter.add(tmp);
//		}
//		ListView list = (ListView)findViewById(R.id.listView1);
//		list.setAdapter(adapter);
//		list.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position,
//					long id) {
//				ListView listView = (ListView)parent;
//				RssItem item = (RssItem)listView.getItemAtPosition(position);
//				Toast.makeText(MainActivity.this, item.getUrl(), Toast.LENGTH_SHORT).show();
//			}
//			
//		});

//		Button btn = (Button)findViewById(R.id.btnLoad);
//		btn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				TextView txt = (TextView)findViewById(R.id.txtResult);
//				StringBuilder sb = new StringBuilder();
//				ArrayList<RssItem> ary = JsonHelper.parseJson(getData());
//				for ( RssItem tmp: ary ) {
//					sb.append(tmp.getTitle() + "\n");
//					sb.append(tmp.getUrl() + "\n");
//				}
//				txt.setText(sb.toString());
//			}
//		});
	}
	public void setListView(ArrayList<RssItem> items) {
		adapter = new RowModelAdapter(this);
		for ( RssItem tmp: items ) {
			adapter.add(tmp);
		}
		ListView list = (ListView)findViewById(R.id.listView1);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				ListView listView = (ListView)parent;
				RssItem item = (RssItem)listView.getItemAtPosition(position);
				Toast.makeText(MainActivity.this, item.getUrl(), Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
				startActivity(intent);
			}
			
		});
	}

//	private String getData() {
//		String json = "";
//		BufferedReader br = null;
//		try {
//			InputStream in = getAssets().open("rss_news.json");
//			br = new BufferedReader(new InputStreamReader(in));
//			StringBuilder sb = new StringBuilder();
//			String line;
//			while ((line = br.readLine()) != null) {
//				Log.v("getData", line);
//				sb.append(line);
//			}
//			json = sb.toString();
//		} catch (Exception e) {
//			Log.e("Main", Log.getStackTraceString(e));
//		} finally {
//			try {
//				if (br != null) {
//					br.close();
//				}
//			} catch ( IOException e ) {
//				Log.e("Main", Log.getStackTraceString(e));
//			}
//		}
//		return json;
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
