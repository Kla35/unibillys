import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {
	JSONObject json;
	
	public JsonReader(String isbn) throws IOException, JSONException {
	    this.json = readJsonFromUrl("https://www.googleapis.com/books/v1/volumes?q=isbn:"+isbn);
	    System.out.println("ISBN : " + isbn);
	    //System.out.println(json.toString());
	  }

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	 }
	
	public boolean find() {
		int nblivre = -1;
		boolean response = false;
		try{
			nblivre = this.json.getInt("totalItems");
		} catch(Exception e) {
			
		}
		
		if(nblivre == 1) {
			response = true;
		}
		return response;
	}
	
	public String getTitle() {
		String str = "";
		if(this.find()) {
			try {
				JSONArray json_items = json.getJSONArray("items");
				JSONObject json_item_0 = json_items.getJSONObject(0);
				JSONObject json_volumeInfo = json_item_0.getJSONObject("volumeInfo");
				str = json_volumeInfo.getString("title");
			}catch(Exception e) {
				System.out.println(e);
			}
			
		}
		return str;
	}
	
	public String getAuthor() {
		String str = "";
		if(this.find()) {
			try {
				JSONArray json_items = json.getJSONArray("items");
				JSONObject json_item_0 = json_items.getJSONObject(0);
				JSONObject json_volumeInfo = json_item_0.getJSONObject("volumeInfo");
				JSONArray json_authors = json_volumeInfo.getJSONArray("authors");
				str = json_authors.getString(0);	
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		return str;
	}
	
	public String getEditor() {
		String str = "";
		if(this.find()) {
			try {
				JSONArray json_items = json.getJSONArray("items");
				JSONObject json_item_0 = json_items.getJSONObject(0);
				JSONObject json_volumeInfo = json_item_0.getJSONObject("volumeInfo");
				str = json_volumeInfo.getString("publisher");
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		return str;
	}
	
	public String getDescription() {
		String str = "";
		if(this.find()) {
			try {
				JSONArray json_items = json.getJSONArray("items");
				JSONObject json_item_0 = json_items.getJSONObject(0);
				JSONObject json_volumeInfo = json_item_0.getJSONObject("volumeInfo");
				str = json_volumeInfo.getString("description");
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		return str;
	}
}