package jp.ac.jec.cm0134.rssreader;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonHelper {
	public static ArrayList<RssItem> parseJson(String strJson) {
		ArrayList<RssItem> list = new ArrayList<RssItem>();
		try {
			JSONObject json = new JSONObject(strJson);
			JSONObject responseData = json.getJSONObject("responseData");
			JSONObject feed = responseData.getJSONObject("feed");
			JSONArray entries = feed.getJSONArray("entries");
			for ( int i = 0; i < entries.length(); i++ ) {
				JSONObject entry = entries.getJSONObject(i);
				list.add(parseToItem(entry));
			}
		} catch ( Exception e ) {
			Log.e("JsonHelper", e.getMessage(), e);
		}
		return list;
	}

	private static RssItem parseToItem(JSONObject json) throws JSONException {
		RssItem tmp = new RssItem();
		tmp.setTitle( json.getString("title") );
		tmp.setUrl( json.getString("link") );
		return tmp;
	}

}
