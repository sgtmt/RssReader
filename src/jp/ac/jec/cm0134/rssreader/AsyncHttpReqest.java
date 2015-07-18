package jp.ac.jec.cm0134.rssreader;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Entity;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;

public class AsyncHttpReqest extends AsyncTask<Uri.Builder,Void,ArrayList<RssItem>>{
	private MainActivity mainActivity;
	public AsyncHttpReqest(MainActivity activity) {
		this.mainActivity = activity;
	}

	@Override
	protected ArrayList<RssItem> doInBackground(Uri.Builder... builder) {
		String resStr = "取得に失敗しました";
		
		String uri = builder[0].toString(); // リクエストのURI
		HttpClient httpClient = new DefaultHttpClient();
		HttpUriRequest httpRequest = new HttpGet(uri);
		
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				resStr = EntityUtils.toString(httpEntity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<RssItem> ary = JsonHelper.parseJson(resStr);
		return ary;
	}
	@Override
	protected void onPostExecute(ArrayList<RssItem> result) {
		mainActivity.setListView(result);
	}

}
