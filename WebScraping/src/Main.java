import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	public static void main(String[] args) {
		String[] urls = new String[] { 
				"http://www.nationmaster.com/country-info/stats/Economy/GDP"};
		try {
			JSONObject json = new JSONObject();
			int k = 0;
			for (String url : urls) {
				Document doc = Jsoup.connect(url).get();
				String title = doc.title();
				Element table = doc.select("tbody").get(0);
				Elements rows = table.select("tr");
				JSONArray array = new JSONArray();
				
				for (int i = 0; i < rows.size(); i++) {
					Element row = rows.get(i);
					String country = row.getElementsByClass("full").get(0).text();
					String amount = row.getElementsByClass("amount").get(0).text();
					String date = row.getElementsByClass("date").get(0).text();
					JSONObject item = new JSONObject();
					item.put("country", country);
					item.put("amount", amount);
					item.put("date", date);
					array.put(item);
				}
				json.put(title, array);
				try {
					FileWriter fileWriter = new FileWriter("data" + k + ".json");
					fileWriter.write(json.toString());
					fileWriter.flush();
					fileWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(json.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
